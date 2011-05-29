:-module(mod2,[myClass2/2,load2/1,calcNrAtrib2/2,clearDatabase2/0,calcNrMet2/2,
	       calcNrInterf2/2,calcNrExtends2/2,methodsOfClass2/3,nrOfCycles2/2,
	       nrOfIf2/2, nrOperators2/3,callT2/3,isInterface2/1,isStatic2/1,
	      paramMet2/2,param2/3]).
:-include('./utilities.pl').
:-include('./commonClasses.pl').
load2(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),
	load(Prj).
myClass2(ClassId,Name):-
%	load('../commonClasses.pl'),
	myClass(ClassId,Name).

calcNrAtrib2(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .


calcNrMet2(IdClasa,Nr):-calcNrMet(IdClasa,Nr).

calcNrInterf2(IdClasa,Nr):-calcNrInterf(IdClasa,Nr).

calcNrExtends2(IdClasa,Nr):-calcNrExtends(IdClasa,Nr).

methodsOfClass2(IdClasa,IdMethod,Name):-methodsOfClass(IdClasa,IdMethod,Name).

nrOfCycles2(MethodId,Nr):-nrOfCycles(MethodId,Nr).

nrOfIf2(MethodId,Nr):-nrOfIf(MethodId,Nr).

nrOperators2(IdMethod,Operator,Nr):-nrOperators(IdMethod,Operator,Nr).

callT2(MethodId2,CalledClassId2,CalledMetId2):-
	callDep(MethodId2,CalledClassId2,CalledMetId2).

isInterface2(ClassId):-isInterface(ClassId).

isStatic2(MetId):-isStatic(MetId).

paramMet2(MethodId2,ListParam2):-paramMet(MethodId2,ListParam2).

param2(MethodId2,ClsId2,IdParam2):-param(MethodId2,ClsId2,IdParam2).



clearDatabase2:-clearDatabase.


