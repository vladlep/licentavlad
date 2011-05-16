%Structure of mathc : Id1, Name 1, Id2, name2
:-dynamic match/4. % structure that stores the matching/copied classes.

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if classes match.
compare2Classes(Id1,Id2):-
	compareClassLevel(Id1,Id2).

compareClassLevel(ClassID1,ClassID2):-
	compareNrAtrib(ClassID1,ClassID2).

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of atributes is similar.
compareNrAtrib(Class1,Class2):-
	calcNrAtrib1(Class1,Nr1),
	calcNrAtrib1(Class2,Nr1) .
%	write(Nr1),
%	write(Nr2),
%	Dif = Nr - Nr2 ,
%	Dif > 0, Dif <2,
%	Class1 = AllCls


%generates combination of classes and compares them.
%If they are copied, they are asserted as match classes.

generateAllMatchingClasses(Name1,Name2):-

	myClass1(Id1,Name1),
	myClass2(Id2,Name2),
	not(match(Id1,_,_,_)),
	not(match(_,_,Id2,_)),
%	compare2Classes(Id1,Id2),
	assert(match(Id1,Name1,Id2,Name2)).

%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project
run:- RootDir = 'F:/serios/faculta/licenta/licentavlad/',
	atom_concat(RootDir,'IProject1.pl',Path1),
	atom_concat(RootDir,'IProject2.pl',Path2),
	use_module(Path1),
	use_module(Path2),
	load1('webserver1.qlf'),
	load2('webserver2.qlf'),
	findall(Name1-Name2,generateAllMatchingClasses(Name1,Name2),Result),
	listing(match),
	write(Result),
	retractall(match(_,_,_,_)).


%old function. Should be deleted in the end. Now used for inspiration.

%nod contine metode : id unic generat de mine, id important din fisier
%nume metoda + lista metode
:- dynamic nod/3.
old_addToCallList(Term,List,Result) :- not(member(Term,List)),
	Result = [Term|List].
old_run(X):-
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/test1.pl'),
	%writef('afsadf'),
	CallList= [],
	callT(X,_,Z,_,_,_,_),
	addToCallList(X,CallList,Result1),
	write(Result1),

	methodT(Z,_,B,_,_,_,_),
	assert(nod(Z,B,[])),

	writef(B).


old_main(Z):- open('F:/serios/faculta/licenta/licentavlad/test1.pl', read, Z), set_output(Z), listing,
set_output(screen), close(Z).






