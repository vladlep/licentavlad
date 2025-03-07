%Structure of mathc : Id1, Name 1, Id2, name2
:-include('profiles.pl').
:-dynamic match/5. % structure that stores the matching/copied classes.
:-dynamic methodMatch/5. %structure that stores the matching/copied methods.
:-dynamic partialMatch/2. % stores classes that match at meth and class level
:-dynamic partialMetMatch/2.
:-dynamic projectMatch/6.
:-dynamic profile/1.
:-dynamic signMatch/2.

%----------------------help functions ---------------------------------%

%return the number of elements form a list. The result is attached to
%the variable NrOfElements
count([],0).
count([_|Tail],NrOfElements) :- count(Tail,Left), NrOfElements is Left+1.

%returns the absolute diffrence between Nr1 and Nr2.
delta(Nr1,Nr2,Delta):-(Nr1>=Nr2,Delta is Nr1-Nr2,!);
		      (Nr2>Nr1,Delta is Nr2-Nr1).

%returns the percent between Nr1 and Nr2. intre 0 si 1.
procent(0,0,1.0):-!.
procent(Nr1,Nr2,Procent):-(Nr1=<Nr2,Procent is Nr1/Nr2,!);
		      (Nr2<Nr1, Procent is Nr2/Nr1).
%calculate the list with unique values from a given list.
uniqueCalc([],List,List).
uniqueCalc([Head|Tail],UList,Res):-
	(

	member(Head,UList),
	uniqueCalc(Tail,UList,Res),!);
	(
	not(member(Head,UList)),
	NewList= [Head|UList],
	uniqueCalc(Tail,NewList,Res),!)
	.
uniqueList(List,ResultedList):-
	uniqueCalc(List,[],ResultedList).

%------------------- Test functions -----------------------------%
testAll:-runAll('./factbase/',loose).
testAllT:-runAll('./factbase/blackboard/',tight).
testRun2:-run('./factbase/webserver1.qlf','./factbase/webserver2.qlf',loose),	retractall(projectMatch(_,_,_,_,_,_,_)).
testRun:-run('./factbase/georgescu.qlf','./factbase/ionescu.qlf',loose),	retractall(projectMatch(_,_,_,_,_,_,_)).
testDir:-runFromDir('./factbase/blackboard/',loose).

%------------------- Main logic functions -----------------------------%
runAll(Directory,Profile):-findall(_,runFromDir(Directory,Profile),_),
	listing(projectMatch),
	retractall(projectMatch(_,_,_,_,_,_)).

testDirC(Project1,Project2):-
	Directory = './factbase/blackboard/',
	directory_files(Directory,ListFiles),
	combine2(ListFiles,Proj1,Proj2),
	atom_concat(Directory,Proj1,Project1),
	atom_concat(Directory,Proj2,Project2).

runFromDir(Directory,Profile):-directory_files(Directory,ListFiles),
	combine2(ListFiles,Proj1,Proj2),
	atom_concat(Directory,Proj1,Project1),
	atom_concat(Directory,Proj2,Project2),
	run(Project1,Project2,Profile).

qlfExtension(Name):-file_name_extension(_, ".qlf", Name).

combine2(List,Proj1,Proj2):-
	member(Proj1,List),
	qlfExtension(Proj1),
	member(Proj2,List),
	qlfExtension(Proj2),

	nth1(Index1,List,Proj1),
	nth1(Index2,List,Proj2),
	Index1 <Index2.

%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project
run(Proj1,Proj2,Profile):-
	retractall(profile(_)),
	assert(profile(Profile)),
	profile(Profile),
	projectDelta(Profile,[MaxNumberDeltaClasses|_]),
	use_module('./IProject1.pl'),
	use_module('./IProject2.pl'),
	load1(Proj1),
	load2(Proj2),

	findall(Id1,myClass1(Id1,_),ClsPrj1),
	count(ClsPrj1,NrCls1),

	findall(Id2,myClass2(Id2,_),ClsPrj2),
	count(ClsPrj2,NrCls2),

	delta(NrCls1,NrCls2,DeltaClasses),
	DeltaClasses =< MaxNumberDeltaClasses,

	findall(_,generateAllMatchingClasses,_),

	write(Proj1 - Proj2),
	writef("\n"),
	listing(match),
	findall(Id,match(Id,_,_,_,high),HighMatchList),
	count(HighMatchList,NrHighMatches),
	findall(Id,match(Id,_,_,_,medium),MedMatchList),
	count(MedMatchList,NrMedMatches),
	findall(Id,match(Id,_,_,_,low),LowMatchList),
	count(LowMatchList,NrLowMatches),

	writef("\nHigh matching classes "),
	write(NrHighMatches),
	writef(" of "),
	write(NrCls1),
	writef("\n"),

	retractall(match(_,_,_,_,_)),
	clearDatabase1,
	clearDatabase2,

	TotalMatches is NrHighMatches+ NrMedMatches +NrLowMatches,
	delta(TotalMatches,NrCls1,NrUnmatched),
	projectDelta(Profile,[_,MinTotalMatches,MinHighMatches,MinMedMatches,
			      MaxNrUnmatched]),
	TotalMatches>= MinTotalMatches,
	NrHighMatches>=MinHighMatches,
	NrMedMatches >= MinMedMatches,
	NrUnmatched =<MaxNrUnmatched,
	assert(projectMatch(Proj1,Proj2,high-NrHighMatches,medium-NrMedMatches,low-NrLowMatches,unmatched-NrUnmatched)).


%generates combination of classes and compares them.
%If they are copied, they are asserted as match classes.

generateAllMatchingClasses:-

	myClass1(Id1,Name1),
	myClass2(Id2,Name2),

	%if one of them already as a very probable match -> don't try anymore
	not(match(Id1,_,_,_,high)),
	not(match(_,_,Id2,_,high)),
	compare2Classes(Id1,Name1,Id2,Name2),

	%assert that there is a big chance they are matches
	retractall(match(Id1,_,_,_,_)),
	retractall(match(_,_,Id2,_,_)),
	assert(match(Id1,Name1,Id2,Name2,high)).


assertClassMatchLow(Id1,Name1,Id2,Name2):-
	(
	not(match(Id1,_,_,_,medium)),
	not(match(_,_,Id2,_,medium)),
	not(match(Id1,_,_,_,low)),
	not(match(_,_,Id2,_,low)),
	assert(match(Id1,Name1,Id2,Name2,low)),!
	) ;1=1

	.
compare2Classes(Id1,Name1,Id2,Name2):-
	compareClassLevel(Id1,Id2),
	assertClassMatchLow(Id1,Name1,Id2,Name2),

	compareMethodLevel(Id1,Name1,Id2,Name2) .

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if classes match.
compareClassLevel(ClassID1,ClassID2):-
	areInterfaces(ClassID1,ClassID2),
	compareNrAtrib(ClassID1,ClassID2),
	compareNrMet(ClassID1,ClassID2),
	compareNrInterf(ClassID1,ClassID2),
	compareNrSuperClass(ClassID1,ClassID2).

	%match the methods from a class with the ones from the second class.
compareMethodLevel(ClassId1,Name1,ClassId2,Name2):-
	findall(_,generateAllMatchingMethods(ClassId1,ClassId2),_),
	findall(Id,methodMatch(Id,_,_,_,high),ResultHigh),
	count(ResultHigh,NrOfMatchesHigh),

	findall(Id,methodMatch(Id,_,_,_,low),ResultLow),
	count(ResultLow,NrOfMatchesLow),

	NrOfMatches is NrOfMatchesHigh + NrOfMatchesLow,

	calcNrMet1(ClassId1,NrMet1),
	calcNrMet2(ClassId2,NrMet2),
%	listing(methodMatch),
	retractall(methodMatch(_,_,_,_,_)),

	profile(Profile),
	classDelta(Profile,[_,_,_,_,_,MaxNrOfUnmatchingMethods,MinProcent]),

	delta(NrOfMatches,NrMet1,Delta1),
	Delta1=<MaxNrOfUnmatchingMethods,

	delta(NrOfMatches,NrMet2,Delta2),
	Delta2<MaxNrOfUnmatchingMethods,


	procent(NrMet1,NrMet2,Procent),
	Procent> MinProcent,

	%daca a trecut de compararile astea =>clasa e mediu

	procent(NrOfMatchesHigh,NrOfMatches,ProcentMatchHigh),
	not(
	    assertClassMedium(ClassId1,Name1,ClassId2,Name2,ProcentMatchHigh)
	   )
	.
assertClassMedium(ClassId1,Name1,ClassId2,Name2,ProcentMatch):-
	profile(Profile),
	classDelta(Profile,[_,_,_,_,ProcentMatchHigh,_,_]),
	ProcentMatch <ProcentMatchHigh, % nu e high, ramane mediu
	retractall(match(ClassId1,_,_,_,_)),
	retractall(match(_,_,ClassId2,_,_)),
	assert(match(ClassId1,Name1,ClassId2,Name2,medium))
	.

assertMetMatch(MethodId1,Name1,MethodId2,Name2):-
	(not(methodMatch(MethodId1,_,_,_,low)),
    	not(methodMatch(_,_,MethodId2,_,low)),
	assert(methodMatch(MethodId1,Name1,MethodId2,Name2,low)),!
	); 1 = 1.

generateAllMatchingMethods(ClassId1,ClassId2):-
	methodsOfClass1(ClassId1,MethodId1,Name1),
	methodsOfClass2(ClassId2,MethodId2,Name2),
	not(methodMatch(MethodId1,_,_,_,high)),
    	not(methodMatch(_,_,MethodId2,_,high)),

	methodMetrics(MethodId1,MethodId2),
	assertMetMatch(MethodId1,Name1,MethodId2,Name2),

	callDependencies(MethodId1,MethodId2),
	retractall(methodMatch(MethodId1,_,_,_,low)),
    	retractall(methodMatch(_,_,MethodId2,_,low)),
	assert(methodMatch(MethodId1,Name1,MethodId2,Name2,high)).



%the methics that are applied to check if methods match.
methodMetrics(MethodId1,MethodId2):-
	whileFilter(MethodId1,MethodId2),
	operatorsFilter(MethodId1,MethodId2),
	ifFilter(MethodId1,MethodId2),
	areStatic(MethodId1,MethodId2),
	compareSigniture(MethodId1,MethodId2)
	.

callDependencies(MethodId1,MethodId2):-
	findall(CalledMet1,callT1(MethodId1,_,CalledMet1),ListMet1),
	uniqueList(ListMet1,UniqueList1),
	count(UniqueList1,NrCalledMet1),

	findall(CalledMet2,callT2(MethodId2,_,CalledMet2),ListMet2),
	uniqueList(ListMet2,UniqueList2),
	count(UniqueList2,NrCalledMet2),

	NrCalledMet1 = NrCalledMet2,

	findall(MethodId1,compareAllCallT(MethodId1,MethodId2),MatchingCalls),
	count(MatchingCalls,Nr),

%	listing(partialMatch),
%	listing(partialMetMatch),

	retractall(partialMatch(_,_)),
	retractall(partialMetMatch(_,_)),

	Nr =  NrCalledMet1.

compareAllCallT(MethodId1,MethodId2):-
	callT1(MethodId1,CalledClassId1,CalledMetId1),
	callT2(MethodId2,ClassId2,CalledMetId2),
	not(partialMetMatch(CalledMetId1,_)),
	not(partialMetMatch(_,CalledMetId2)),

	methodMetrics(CalledMetId1,CalledMetId2),
	(   (

	not(partialMatch(CalledClassId1,_)),
    	not(partialMatch(_,ClassId2)),

	compareClassLevel(CalledClassId1,ClassId2),
	assert(partialMetMatch(CalledMetId1,CalledMetId2)),
	assert(partialMatch(CalledClassId1,ClassId2))
	    );
	(
	partialMatch(CalledClassId1,ClassId2),
	not(partialMetMatch(CalledMetId1,CalledMetId2)),
	 assert(partialMetMatch(CalledMetId1,CalledMetId2)) )
).


compareSigniture(MethodId1,MethodId2):-
	paramMet1(MethodId1,ListParam1),
	paramMet2(MethodId2,ListParam2),
	count(ListParam1,NrParam1),
	count(ListParam2,NrParam2),

	NrParam1 = NrParam2,

	findall(_,paramCompare(MethodId1,MethodId2),_),
	findall(Id,signMatch(Id,_),ResultList),
	count(ResultList,NrMatch),
	retractall(signMatch(_,_)),

	NrMatch = NrParam1
	.

paramCompare(MethodId1,MethodId2):-
	param1(MethodId1,ClsId1,IdParam1),
	param2(MethodId2,ClsId2,IdParam2),
	not(signMatch(IdParam1,_)),
    	not(signMatch(_,IdParam2)),
	compareClassLevel(ClsId1,ClsId2),
	assert(signMatch(IdParam1,IdParam2)).



% --------------------------------Filters--------------------------------%

areInterfaces(ClassID1,ClassID2):-
	(   isInterface1(ClassID1),
	isInterface2(ClassID2));

	(   not(isInterface1(ClassID1)),
	    not(isInterface2(ClassID2))).

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of atributes is similar.
compareNrAtrib(Class1,Class2):-
	profile(Profile),
	classDelta(Profile,[MaxDifference,MinProcent,_,_,_,_,_]),
	calcNrAtrib1(Class1,Nr1),
	calcNrAtrib2(Class2,Nr2),

	((
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference),

	(
	procent(Nr1,Nr2,Procent),
	MinProcent =<Procent
	)).


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of methods is similar.
compareNrMet(ClassID1,ClassID2):-
	profile(Profile),
	classDelta(Profile,[_,_,MaxDifference,_,_,_,_]),
	calcNrMet1(ClassID1,Nr1),
	calcNrMet2(ClassID2,Nr2),
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference.


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of interfaces is similar.
compareNrInterf(ClassID1,ClassID2):-
	profile(Profile),
	classDelta(Profile,[_,_,_,MaxDifference,_,_,_]),
	calcNrInterf1(ClassID1,Nr1),
	calcNrInterf2(ClassID2,Nr2),
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference.


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of superclasses is equal.
%a class extends the object class and can extend 1 more. Nr = 1 or 2
compareNrSuperClass(ClassID1,ClassID2):-
	calcNrExtends1(ClassID1,Nr1),
	calcNrExtends2(ClassID2,Nr2),

	Nr1 = Nr2.


%method level comparing. Nr of -, / ,* should be equal between
%candidate methods
operatorsFilter(MethodId1,MethodId2):-
	nrOperators1(MethodId1,-,NrSub1),
	nrOperators2(MethodId2,-,NrSub2),
	NrSub1=NrSub2,

	nrOperators1(MethodId1,/,NrDiv1),
	nrOperators2(MethodId2,/,NrDiv2),
	NrDiv1=NrDiv2,

	nrOperators1(MethodId1,*,NrMul1),
	nrOperators2(MethodId2,*,NrMul2),
	NrMul1=NrMul2.

ifFilter(MethodId1,MethodId2):-
	profile(Profile),
	methodDelta(Profile,[MaxDifference]),
	nrOfIf1(MethodId1,Nr1),
	nrOfIf2(MethodId2,Nr2),
	delta(Nr1,Nr2,Delta),
	Delta =< MaxDifference
	.

%method level comparizon. Verify that same number of whiles/for/do while
%are present in candidate methods.
whileFilter(MethodId1,MethodId2):-
	nrOfCycles1(MethodId1,Nr1),
	nrOfCycles2(MethodId2,Nr2),
	Nr1=Nr2.



areStatic(MetId1,MetId2):-
		(   isStatic1(MetId1),
	isStatic2(MetId2));

	(   not(isStatic1(MetId1)),
	    not(isStatic2(MetId2))).


