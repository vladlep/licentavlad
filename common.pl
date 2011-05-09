%+PUBLIC
%incarca un proiect a carui nume e trimis ca parametru intr-un modul cu
%acelasi nume.
%Prj - in param . Numele proiectului.
load(Prj):-
	%atom_concat('c:/users/vll/vlad/LICENTA/licentavlad/factbse/',Prj,Result),
	atom_concat('F:/serios/faculta/licenta/licentavlad/factbase/',Prj,Result),
	consult(Result).


%-PRIVATE
%FIXME ... NU e buna. Trebuie rafinata. Sa ma uit in prj de anul trecut
myClass(AllCls) :-
	classT(AllCls,Cu,_,_),
	compilationUnitT(Cu,_,Fid,_,_),
	fileS(Fid,Src,_),
	sourceFolderS(Src,Pid,_),
	projectS(Pid,_,_,_,_)
%	write(N)
	.
%-PRIVATE
%return the number of elements form a list. The result is attached to
%the variable NrOfElements
count([],0).
count([_|Tail],NrOfElements) :- count(Tail,Left), NrOfElements is Left+1.

%+PUBLIC
% calculez care e nr de atribute pentru o clasa dintr-un modul
calcNrAtrib(IdClasa,Nr) :-
	findall(X,fieldT(X,IdClasa,_,_,_),Result),
	count(Result,Nr).


% calculez care e nr de metode pentru o clasa dintr-un modul

%+PUBLIC
%remove all entrie from the database
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



