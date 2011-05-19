:-module(mod2,[myClass2/2,load2/1,calcNrAtrib2/2,clearDatabase2/0,calcNrMet2/2,
	       calcNrInterf2/2,calcNrExtends2/2,methodsOfClass2/2,nrOfWhile2/2,nrOfIf2/2,
	       nrOperators2/3]).
:-include('F:/serios/faculta/licenta/licentavlad/common.pl').

load2(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),
	load(Prj).
myClass2(ClassId,Name):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),

	myClass(ClassId,Name).

calcNrAtrib2(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .


calcNrMet2(IdClasa,Nr):-calcNrMet(IdClasa,Nr).

calcNrInterf2(IdClasa,Nr):-calcNrInterf(IdClasa,Nr).

calcNrExtends2(IdClasa,Nr):-calcNrExtends(IdClasa,Nr).

methodsOfClass2(IdClasa,IdMethod):-methodsOfClass(IdClasa,IdMethod).

nrOfWhile2(MethodId,Nr):-nrOfWhile(MethodId,Nr).

nrOfIf2(MethodId,Nr):-nrOfIf(MethodId,Nr).

nrOperators2(IdMethod,Operator,Nr):-nrOperators(IdMethod,Operator,Nr).

clearDatabase2:-clearDatabase.

