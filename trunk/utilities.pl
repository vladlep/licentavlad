%+PUBLIC
%incarca un proiect a carui nume e trimis ca parametru intr-un modul cu
%acelasi nume.
%Prj - in param . Numele proiectului.
load(Prj):-
	%atom_concat('c:/users/vll/vlad/LICENTA/licentavlad/factbse/',Prj,Result),
	atom_concat('./factbase/',Prj,Result),
	consult(Result).


%+PUBLIC
%Returnes the IDs of the classes from a project.
myClass(AllCls,Name) :-
	classT(AllCls,Cu,Name,_),
	compilationUnitT(Cu,_,Fid,_,_),
	fileS(Fid,Src,Path),
	sourceFolderS(Src,Pid,_),
	projectS(Pid,_,_,_,_),
	findall(PartPath,common(Path,PartPath),Result),
	count(Result,Nr),
	Nr=0.

common(Path,Nr):-commonClass(PartOfPath),
	( atom_concat(Nr,PartOfPath,Path);
 	  atom_concat(PartOfPath,Nr,Path) ).


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
methodsOfClass(IdClasa,IdMethod,Name):-methodT(IdMethod,IdClasa,Name,_,_,_,_).

%+PUBLIC
%calculez cate while/for/do while-uri  sunt intr-o metoda
nrOfCycles(MethodId,Nr):-findall(MethodId,whileT(_,_,MethodId,_,_),While),
	count(While,NrWhile),
	findall(MethodId,doWhileT(_,_,MethodId,_,_),DoWhile),
	count(DoWhile,NrDoWhile),
	findall(MethodId,forT(_,_,MethodId,_,_,_,_),For),
	count(For,NrFor),
	Nr is (NrWhile + NrDoWhile + NrFor)
	.

%+PUBLIC
%calculez cate if-uri sunt intr-o metoda
nrOfIf(MethodId,Nr):-findall(MethodId,ifT(_,_,MethodId,_,_,_),Result),
	count(Result,Nr).

%+PUBLIC
%calculez cate while-uri sunt intr-o metoda
nrOperators(IdMethod,Operator,Nr):-findall(IdMethod,operationT(_,_,IdMethod,_,Operator,_),Result),
	count(Result,Nr).

%+PUBLIC
%returnes all the calles that are made from a method
callDep(MethodId,CalledClassId,CalledMetId):-
	callT(_,_,MethodId,_,_,_,CalledMetId),
	methodT(CalledMetId,CalledClassId,_,_,_,_,_),
	myClass(CalledClassId,_).

%+PUBLIC
%returnes true if class is an interface.
isInterface(ClassId):-
	interfaceT(ClassId).
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











