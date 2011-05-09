:-dynamic match/2.
%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if classes match.
compareClassLevel(ClassID1,ClassID2):-
	compareNrAtrib(ClassID1,ClassID2).

%ClassID1 - in param. The id of the class from the first prj
%ClassID2 - in param. The id of the class from the second prj
%return : true if the number of atributes is similar.
compareNrAtrib(Class1,Class2):-
	calcNrAtrib1(Class1,Nr1),
	calcNrAtrib1(Class2,Nr1),
%	write(Nr1),
%	write(Nr2),
	Dif = Nr - Nr2 ,
	Dif > 0, Dif <2,
	ClassID1 = AllCls

	.
%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project

compare2Prj(Prj1,Prj2) :-
	load(Prj1),
	load(Prj2),
	compareClassLevel(Prj1,ClassID1,Prj2,ClassID2),
	writef("clase ce satisfac cond :"),
	write(ClassID1),
	writef(" "),
	write(ClassID2),
	writef(" ").

run:- RootDir = 'F:/serios/faculta/licenta/licentavlad/',
	atom_concat(RootDir,'IProject1.pl',Path1),
	atom_concat(RootDir,'IProject2.pl',Path2),
	use_module(Path1),
	use_module(Path2),
	load1('polimorfism.pl'),
	load2('test2.pl'),
	myClass1(Id1)	,
	myClass2(Id2),
	compareClassLevel(Id1,Id2),
	write(Id1),
	writef(" - "),
	write(Id2),
	writef("  \n")
	.


main:- compare2Prj('test1.pl','test1.pl').







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






