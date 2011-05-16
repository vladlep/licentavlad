:-module(mod1,[myClass1/2,load1/1,calcNrAtrib1/2,clearDatabase1/0,calcNrMet1/2,
	       calcNrInterf1/2,calcNrExtends1/2,methodsOfClass1/2,nrOfWhile1/2,nrOfIf1/2,
	       nrOperators1/3]).

:-include('F:/serios/faculta/licenta/licentavlad/common.pl').

load1(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),

	load(Prj).
myClass1(ClassId,Name):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),
%	listing(myClass),
	myClass(ClassId,Name).

calcNrAtrib1(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .

clearDatabase1:-clearDatabase.

calcNrMet1(IdClasa,Nr):-calcNrMet(IdClasa,Nr).

calcNrInterf1(IdClasa,Nr):-calcNrInterf(IdClasa,Nr).

calcNrExtends1(IdClasa,Nr):-calcNrExtends(IdClasa,Nr).

methodsOfClass1(IdClasa,IdMethod):-methodsOfClass(IdClasa,IdMethod).

nrOfWhile1(MethodId,Nr):-nrOfWhile(MethodId,Nr).

nrOfIf1(MethodId,Nr):-nrOfIf(MethodId,Nr).

nrOperators1(IdMethod,Operator,Nr):-nrOperators(IdMethod,Operator,Nr).
