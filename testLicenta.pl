%test the countVariable
testCount :-
	consult('C:/Users/vll/vlad/licentavlad/licenta.pl'),
	count([],Nr), write(Nr),
	count([1,23,4],Nr1), write(Nr1).
testCalcNrAtrib :-
	module(modul1),
	consult('C:/Users/vll/vlad/licentavlad/test1.pl'),
	consult('C:/Users/vll/vlad/licentavlad/licenta.pl'),
	calcNrAtrib(modul1,10003,Result),
	write(Result).

testLoad:-
	consult('C:/Users/vll/vlad/licentavlad/licenta.pl'),
	load('test1').
