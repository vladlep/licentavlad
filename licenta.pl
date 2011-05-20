%Structure of mathc : Id1, Name 1, Id2, name2
:-dynamic match/4. % structure that stores the matching/copied classes.
:-dynamic methodMatch/4. %structure that stores the matching/copied methods.

%----------------------help functions ---------------------------------%

%return the number of elements form a list. The result is attached to
%the variable NrOfElements
count([],0).
count([_|Tail],NrOfElements) :- count(Tail,Left), NrOfElements is Left+1.

%returns the absolute diffrence between Nr1 and Nr2.
delta(Nr1,Nr2,Delta):-(Nr1>=Nr2,Delta is Nr1-Nr2);
		      (Nr2>Nr1,Delta is Nr2-Nr1).

%returns the percent between Nr1 and Nr2. intre 0 si 1.
procent(0,0,1.0):-!.
procent(Nr1,Nr2,Procent):-(Nr1=<Nr2,Procent is Nr1/Nr2);
		      (Nr2<Nr1, Procent is Nr2/Nr1).


%------------------- Main logic functions -----------------------------%
%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if classes match.
%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project
run:- RootDir = 'F:/serios/faculta/licenta/licentavlad/',
	atom_concat(RootDir,'IProject1.pl',Path1),
	atom_concat(RootDir,'IProject2.pl',Path2),
	use_module(Path1),
	use_module(Path2),

%	load1('webserver1.qlf'),
%	load2('webserver2.qlf'),

	load1('passc2Copy.qlf'),
	load2('passc2.qlf'),
	findall(Name1-Name2,generateAllMatchingClasses(Name1,Name2),Result),
	listing(match),
	count(Result,Nr),
	write(Nr),
	retractall(match(_,_,_,_)).


%generates combination of classes and compares them.
%If they are copied, they are asserted as match classes.

generateAllMatchingClasses(Name1,Name2):-

	myClass1(Id1,Name1),
	myClass2(Id2,Name2),
	not(match(Id1,_,_,_)),
	not(match(_,_,Id2,_)),
	compare2Classes(Id1,Id2),
	assert(match(Id1,Name1,Id2,Name2)).



compare2Classes(Id1,Id2):-
	compareClassLevel(Id1,Id2),
	compareMethodLevel(Id1,Id2).

compareClassLevel(ClassID1,ClassID2):-
	compareNrAtrib(ClassID1,ClassID2),
	compareNrMet(ClassID1,ClassID2),
	compareNrInterf(ClassID1,ClassID2),
	compareNrSuperClass(ClassID1,ClassID2)
	.

%match the methods from a class with the ones from the second class.
compareMethodLevel(ClassId1,ClassId2):-
	findall(_,generateAllMatchingMethods(ClassId1,ClassId2),_),
	findall(Id,methodMatch(Id,_,_,_),Result),
	count(Result,Nr),
	calcNrMet1(ClassId1,NrMet1),
	calcNrMet2(ClassId2,NrMet2),
	Mean is  ((NrMet1+NrMet2)/2),
	%write(Result),
	listing(methodMatch),
	retractall(methodMatch(_,_,_,_)),
	delta(Mean,Nr,Delta),
	MaxNrOfUnmatchingMethods = 1,
	Delta<MaxNrOfUnmatchingMethods,
	writef("\nRetracted all \n").

generateAllMatchingMethods(ClassId1,ClassId2):-
	methodsOfClass1(ClassId1,MethodId1,Name1),
	methodsOfClass2(ClassId2,MethodId2,Name2),
	not(methodMatch(MethodId1,_,_,_)),
    	not(methodMatch(_,_,MethodId2,_)),
	methodMetrics(MethodId1,MethodId2),
	callDependencies(MethodId1,MethodId2),
	assert(methodMatch(MethodId1,Name1,MethodId2,Name2)).



%the methics that are applied to check if methods match.
methodMetrics(MethodId1,MethodId2):-
	whileFilter(MethodId1,MethodId2),
	ifFilter(MethodId1,MethodId2),
	operatorsFilter(MethodId1,MethodId2)
	.

callDependencies(MethodId1,MethodId2):-
	findall(MethodId1,callT1(MethodId1,_,_),ListMet1),
	count(ListMet1,NrCalledMet1),
	findall(MethodId2,callT2(MethodId2,_,_),ListMet2),
	count(ListMet2,NrCalledMet2),

	NrCalledMet1 = NrCalledMet2,

	findall(MethodId1,compareAllCallT(MethodId1,MethodId2),MatchingCalls),
	count(MatchingCalls,Nr),

	writef("\nFor method Id : "),
	write(MethodId1),
	writef(" and Id2 "),
	write(MethodId2),
	writef(" Matching callT:   : "),
	write(Nr),

	MatchingCalls =  NrCalledMet1.

compareAllCallT(MethodId1,MethodId2):-
	callT1(MethodId1,CalledClassId1,CalledMetId1),
	callT2(MethodId2,ClassId2,CalledMetId2),
	compareClassLevel(CalledClassId1,ClassId2),
	methodMetrics(CalledMetId1,CalledMetId2).

% --------------------------------Filters--------------------------------%


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of atributes is similar.
compareNrAtrib(Class1,Class2):-
	calcNrAtrib1(Class1,Nr1),
	calcNrAtrib2(Class2,Nr2),

	((MaxDifference = 5,
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference,!);

	( MinProcent = 0.6,
	procent(Nr1,Nr2,Procent),
	MinProcent =<Procent
	)).


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of methods is similar.
compareNrMet(ClassID1,ClassID2):-
	calcNrMet1(ClassID1,Nr1),
	calcNrMet2(ClassID2,Nr2),

	MaxDifference = 3,
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference.


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of interfaces is similar.
compareNrInterf(ClassID1,ClassID2):-
	calcNrInterf1(ClassID1,Nr1),
	calcNrInterf2(ClassID2,Nr2),

	MaxDifference = 1,
	delta(Nr1,Nr2,Difference),
	Difference =< MaxDifference.


%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of superclasses is equal.
%a class extends the object class and can extend 1 more. Nr = 1 or 2
compareNrSuperClass(ClassID1,ClassID2):-
	calcNrExtends1(ClassID1,Nr1),
	calcNrExtends2(ClassID2,Nr2),

	Nr1 = Nr2
%	write(ClassID1),
%	writef(" Nr1  : "),
%	write(Nr1),
%	writef("\n"),
%	write(ClassID2),
%	writef("Nr2  : "),
%	write(Nr2),
%	writef("\n ")
	.


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
	NrMul1=NrMul2

%	write(MethodId1),
%	writef(" Nr1  : "),
%	write(Nr1),
%	writef("\n"),
%	write(MethodId2),
%	writef("Nr2  : "),
%	write(Nr2),
%	writef("\n ")

	.

ifFilter(MethodId1,MethodId2):-
	nrOfIf1(MethodId1,Nr1),
	nrOfIf2(MethodId2,Nr2),
	MaxDifference = 1,
	delta(Nr1,Nr2,Delta),
	Delta =< MaxDifference
	.

%method level comparizon. Verify that same number of whiles/for/do while
%are present in candidate methods.
whileFilter(MethodId1,MethodId2):-
	nrOfCycles1(MethodId1,Nr1),
	nrOfCycles2(MethodId2,Nr2),

%	write(MethodId1),
%	writef(" Nr1  : "),
%	write(Nr1),
%	writef("\n"),
%	write(MethodId2),
%	writef("Nr2  : "),
%	write(Nr2),
%	writef("\n "),

	Nr1=Nr2.





