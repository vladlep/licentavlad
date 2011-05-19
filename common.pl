%+PUBLIC
%incarca un proiect a carui nume e trimis ca parametru intr-un modul cu
%acelasi nume.
%Prj - in param . Numele proiectului.
load(Prj):-
	%atom_concat('c:/users/vll/vlad/LICENTA/licentavlad/factbse/',Prj,Result),
	atom_concat('F:/serios/faculta/licenta/licentavlad/factbase/',Prj,Result),
	consult(Result).


%+PUBLIC
%Returnes the IDs of the classes from a project.
myClass(AllCls,Name) :-
	classT(AllCls,Cu,Name,_),
	compilationUnitT(Cu,_,Fid,_,_),
	fileS(Fid,Src,_),
	sourceFolderS(Src,Pid,_),
	projectS(Pid,_,_,_,_).

%+PUBLIC
%Returnes the number of classes from a project.
nrOfClass(Nr):-	findall(X,fileS(X,_,_),Result),
	count(Result,Nr).

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

%+PUBLIC
% calculez cate metode are o clasa dintr-un modul
calcNrMet(IdClasa,Nr):-findall(X,methodT(X,IdClasa,_,_,_,_,_),Result),
	count(Result,Nr).

%+PUBLIC
% calculez cate interfete implementeaza o clasa dintr-un modul.
calcNrInterf(IdClasa,Nr):-findall(IdClasa,implementsT(IdClasa,_),Result),
	count(Result,Nr).

%+PUBLIC
%calculez daca clasa extinde o superclasa
calcNrExtends(IdClasa,Nr):-findall(IdClasa,extendsT(IdClasa,_),Result),
	count(Result,Nr).

%	(extendsT(IdClasa,_),Nr=1,!); Nr=0.

%+PUBLIC
%returns the id of the methods from a class
methodsOfClass(IdClasa,IdMethod):-methodT(IdMethod,IdClasa,_,_,_,_,_).

%+PUBLIC
%calculez cate while-uri sunt intr-o metoda
nrOfWhile(MethodId,Nr):-findall(MethodId,whileT(_,_,MethodId,_,_),Result),
	count(Result,Nr).

%+PUBLIC
%calculez cate if-uri sunt intr-o metoda
nrOfIf(MethodId,Nr):-findall(MethodId,ifT(_,_,MethodId,_,_),Result),
	count(Result,Nr).

%+PUBLIC
%calculez cate while-uri sunt intr-o metoda
nrOperators(IdMethod,Operator,Nr):-findall(IdMethod,operationT(_,_,IdMethod,_,Operator,_),Result),
	count(Result,Nr).

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
