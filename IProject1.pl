:-module(mod1,[myClass1/1,load1/1,calcNrAtrib1/2]).
:-include('F:/serios/faculta/licenta/licentavlad/common.pl').

load1(Prj):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),

	load(Prj).
myClass1(ClassId):-
%	consult('F:/serios/faculta/licenta/licentavlad/common.pl'),
%	listing(myClass),
	myClass(ClassId).

calcNrAtrib1(IdClasa,Nr):- calcNrAtrib(IdClasa,Nr) .
