%Structure of mathc : Id1, Name 1, Id2, name2
:-dynamic match/4. % structure that stores the matching/copied classes.

%returns the absolute diffrence between Nr1 and Nr2.
delta(Nr1,Nr2,Delta):-(Nr1>=Nr2,Delta is Nr1-Nr2);
		      (Nr2>Nr1,Delta is Nr2-Nr1).

%returns the percent between Nr1 and Nr2. intre 0 si 1.
procent(0,0,1.0):-!.
procent(Nr1,Nr2,Procent):-(Nr1=<Nr2,Procent is Nr1/Nr2);
		      (Nr2<Nr1, Procent is Nr2/Nr1).

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if classes match.
compare2Classes(Id1,Id2):-
	compareClassLevel(Id1,Id2).

compareClassLevel(ClassID1,ClassID2):-
	compareNrAtrib(ClassID1,ClassID2),
	compareNrMet(ClassID1,ClassID2),
	compareNrInterf(ClassID1,ClassID2),
	compareNrSuperClass(ClassID1,ClassID2)
	.



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



%generates combination of classes and compares them.
%If they are copied, they are asserted as match classes.

generateAllMatchingClasses(Name1,Name2):-

	myClass1(Id1,Name1),
	myClass2(Id2,Name2),
	not(match(Id1,_,_,_)),
	not(match(_,_,Id2,_)),
	compare2Classes(Id1,Id2),
	assert(match(Id1,Name1,Id2,Name2)).

%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project
run:- RootDir = 'F:/serios/faculta/licenta/licentavlad/',
	atom_concat(RootDir,'IProject1.pl',Path1),
	atom_concat(RootDir,'IProject2.pl',Path2),
	use_module(Path1),
	use_module(Path2),
	load1('passc2Copy.qlf'),
	load2('passc2.qlf'),
	findall(Name1-Name2,generateAllMatchingClasses(Name1,Name2),Result),
	listing(match),
	write(Result),
	retractall(match(_,_,_,_)).




%old functions. Should be deleted in the end. Now used for inspiration.

%nod contine metode : id unic generat de mine, id important din fisier
%nume metoda + lista metode
:- dynamic nod/3.
old_addToCallList(Term,List,Result) :- not(member(Term,List)),
	Result = [Term|List].


old_main(Z):- open('F:/serios/faculta/licenta/licentavlad/test1.pl', read, Z), set_output(Z), listing,
set_output(screen), close(Z).






