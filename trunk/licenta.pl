% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.


%return the number of elements form a list. The result is attached to
%the variable NrOfElements
count([],0).
count([_|Tail],NrOfElements) :- count(Tail,Left), NrOfElements is Left+1.

% calculez care e nr de atribute pentru o clasa dintr-un modul
calcNrAtrib(Modul,IdClasa,Nr) :-
	module(Modul),
	findall(X,fieldT(X,IdClasa,_,_,_),Result),
	count(Result,Nr).



% calculez care e nr de metode pentru o clasa dintr-un modul



%incarca un proiect a carui nume e trimis ca parametru intr-un modul cu
%acelasi nume.
%Prj - in param . Numele proiectului.
load(Prj):-
	module(Prj),
	atom_concat('c:/users/vll/vlad/licentavlad/',Prj,Result),
	consult(Result).


%FIXME ... NU e buna. Trebuie rafinata. Sa ma uit in prj de anul trecut
myClass(Prj1, AllCls) :-
	module(Prj1),
	classT(AllCls,_,Nume,_),
	AllCls >17311,
	write(Nume).
myClasses(Prj).
testMyClass(ID):-
	Prj1 = 'stockMarket.pl',
	load(Prj1),
	myClass(Prj1,ID).

%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project
%ClassID1 - out param. The id of the class from the first prj that
%matches  the class with the ClassID2 from the second prj
%ClassID2 - out param. The id of the class from the second prj that
%matches  the class with the ClassID1 from the first prj
comapareClassLevel(Prj1,ClassID1,Prj2,ClassID2):-
	myClass(Prj1,AllCls),
	calcNrAtrib(Prj1,AllCls,Nr),
	write(Nr),
	myClass(Prj2,Cls2),
	calcNrAtrib(Prj2,Cls2,Nr2),
	write(Nr2),
	Dif = Nr - Nr2 ,
	Dif > 0, Dif <2,
	ClassID1 = AllCls,
	ClassID2 = Cls2.
testCompare2(R):-
	findall(Id1,comapareClassLevel('test1.pl',Id1,'stockMarket.pl',_),R).

%The main function that compares 2 project.
%Prj1 - in param. Name of first project
%Prj2 - in param. Name of second project

compare2Prj(Prj1,Prj2) :-
	load(Prj1),
	load(Prj2),
	comapareClassLevel(Prj1,ClassID1,Prj2,ClassID2),
	writef("clase ce satisfac cond :"),
	write(ClassID1),
	write(ClassID2).






%old function. Should be deleted in the end. Now used for inspiration.

%nod contine metode : id unic generat de mine, id important din fisier
%nume metoda + lista metode
:- dynamic nod/3.
addToCallList(Term,List,Result) :- not(member(Term,List)), Result = [Term|List].
run(X):-
	consult('C:/Users/vll/vlad/licentavlad/test1.pl'),
	%writef('afsadf'),
	CallList= [],
	callT(X,_,Z,_,_,_,_),
	addToCallList(X,CallList,Result1),
	write(Result1),

	methodT(Z,_,B,_,_,_,_),
	assert(nod(Z,B,[])),

	writef(B).


main(Z):- open('I:/vlad/serios/faculta/licenta/licentavlad/test1.pl', read, Z), set_output(Z), listing,
set_output(screen), close(Z).

loadFile(File):- consult(File).



clearDatabase:-
	retractall(classT(_,_,_,_)),
	retractall(blockT(_,_,_,_)),
	retractall(callT(_,_,_,_,_,_,_)),
	retractall(methodT(_,_,_,_,_,_,_)),
	retractall(constructorT(_,_,_,_,_)),
        retractall(fieldT(_,_,_,_,_)),
        retractall(literalT(_,_,_,_,_)),
        retractall(extendsT(_,_)),
        retractall(implementsT(_,_)),
        retractall(externT(_)),
        retractall(interfaceT(_)),
        retractall(modifierT(_,_)),
        retractall(compilationUnitT(_,_,_,_,_)),
        retractall(globalIds(_,_,_,_)),
        retractall(globalIds(_,_)),
        retractall(globalIds(_,_,_)),
        retractall(ri_globalIds(_,_,_)),
	retractall(paramT(_,_,_,_)).



calcTree(File,Tree):- loadFile(File),
	callT(Tree,_,_,_,_,_,_).


testCalc(Tree):- calcTree('C:/Users/vll/vlad/licentavlad/test1.pl',Tree).




















