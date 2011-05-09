:-module(mod2,[myClass2/1,load2/1,calcNrAtrib2/2]).
:-include('F:/serios/faculta/licenta/licentavlad/common.pl').

load2(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),
	load(Prj).
myClass2(ClassId):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),

	myClass(ClassId).

calcNrAtrib2(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .
