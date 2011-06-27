:-module(mod2,[myClass2/2,load2/1,calcNrAtrib2/2,clearDatabase2/0,calcNrMet2/2,
	       calcNrInterf2/2,calcNrExtends2/2,methodsOfClass2/3,nrOfCycles2/2,
	       nrOfIf2/2, nrOperators2/3,callT2/3,isInterface2/1,isStatic2/1,
	      paramMet2/2,param2/3]).
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


