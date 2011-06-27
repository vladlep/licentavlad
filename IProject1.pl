:-module(mod1,[myClass1/2,load1/1,calcNrAtrib1/2,clearDatabase1/0,calcNrMet1/2,
	       calcNrInterf1/2,calcNrExtends1/2,methodsOfClass1/3,nrOfCycles1/2,
	       nrOfIf1/2,nrOperators1/3,callT1/3,isInterface1/1,isStatic1/1,
	       paramMet1/2,param1/3]).

:-include('./utilities.pl').
:-include('./commonClasses.pl').

:-dynamic classT/4.
:-dynamic callT/7.
:-dynamic methodT/7.
:-dynamic projectS/5.
:-dynamic sourceFolderS/3.
:-dynamic compilationUnitT/5.
:-dynamic classT/4.
:-dynamic methodT/7.
:-dynamic fieldT/5.
:-dynamic paramT/4.
:-dynamic callT/7.
:-dynamic forT/7.
:-dynamic ifT/6.
:-dynamic operationT/6.
:-dynamic identT/5.
:-dynamic typeRefT/4.
:-dynamic whileT/5.
:-dynamic arrayTypeT/4.
:-dynamic extendsT/2.
:-dynamic implementsT/2.
:-dynamic modifierT/2.
:-dynamic interfaceT/1.

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









