% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.


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

loadFile(Root,File):-
%	consult('C:/Users/vll/vlad/licentavlad/test1.pl'),Root =1, File
%	=2.
	consult(Root+ File).

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



calcTree(Root,File,Tree):- loadFile(Root,File),
%	clearDatabase,
	classT(Tree,_,_,_,_,_,_).


testCalc(Tree):- calcTree('C:/Users/vll/vlad/licentavlad/','test1.pl',Tree).
