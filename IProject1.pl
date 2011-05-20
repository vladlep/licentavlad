:-module(mod1,[myClass1/2,load1/1,calcNrAtrib1/2,clearDatabase1/0,calcNrMet1/2,
	       calcNrInterf1/2,calcNrExtends1/2,methodsOfClass1/3,nrOfCycles1/2,
	       nrOfIf1/2,nrOperators1/3,callT1/3]).

:-include('F:/serios/faculta/licenta/licentavlad/utilities.pl').
:-include('F:/serios/faculta/licenta/licentavlad/commonClasses.pl').

load1(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),

	load(Prj).
myClass1(ClassId,Name):-
%	load('../commonClasses.pl'),
	myClass(ClassId,Name).

calcNrAtrib1(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .

clearDatabase1:-clearDatabase.

calcNrMet1(IdClasa,Nr):-calcNrMet(IdClasa,Nr).

calcNrInterf1(IdClasa,Nr):-calcNrInterf(IdClasa,Nr).

calcNrExtends1(IdClasa,Nr):-calcNrExtends(IdClasa,Nr).

methodsOfClass1(IdClasa,IdMethod,Name):-methodsOfClass(IdClasa,IdMethod,Name).

nrOfCycles1(MethodId,Nr):-nrOfCycles(MethodId,Nr).

nrOfIf1(MethodId,Nr):-nrOfIf(MethodId,Nr).

nrOperators1(IdMethod,Operator,Nr):-nrOperators(IdMethod,Operator,Nr).

callT1(MethodId1,CalledClassId1,CalledMetId1):-callT(_,_,MethodId1,_,_,_,CalledMetId1),methodT(CalledMetId1,CalledClassId1,_,_,_,_,_).










