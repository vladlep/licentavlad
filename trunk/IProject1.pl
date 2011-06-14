:-module(mod1,[myClass1/2,load1/1,calcNrAtrib1/2,clearDatabase1/0,calcNrMet1/2,
	       calcNrInterf1/2,calcNrExtends1/2,methodsOfClass1/3,nrOfCycles1/2,
	       nrOfIf1/2,nrOperators1/3,callT1/3,isInterface1/1,isStatic1/1,
	       paramMet1/2,param1/3]).

:-include('./utilities.pl').
:-include('./commonClasses.pl').

load1(Prj):- load(Prj).

myClass1(ClassId,Name):- myClass(ClassId,Name).

calcNrAtrib1(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .

clearDatabase1:-clearDatabase.

calcNrMet1(IdClasa,Nr):-calcNrMet(IdClasa,Nr).

calcNrInterf1(IdClasa,Nr):-calcNrInterf(IdClasa,Nr).

calcNrExtends1(IdClasa,Nr):-calcNrExtends(IdClasa,Nr).

methodsOfClass1(IdClasa,IdMethod,Name):-methodsOfClass(IdClasa,IdMethod,Name).

nrOfCycles1(MethodId,Nr):-nrOfCycles(MethodId,Nr).

nrOfIf1(MethodId,Nr):-nrOfIf(MethodId,Nr).

nrOperators1(IdMethod,Operator,Nr):-nrOperators(IdMethod,Operator,Nr).

callT1(MethodId1,CalledClassId1,CalledMetId1):-
	callDep(MethodId1,CalledClassId1,CalledMetId1).
isInterface1(ClassId):-isInterface(ClassId).



isStatic1(MetId):-isStatic(MetId).

paramMet1(MethodId1,ListParam1):-paramMet(MethodId1,ListParam1).

param1(MethodId1,ClsId1,IdParam1):-param(MethodId1,ClsId1,IdParam1).









