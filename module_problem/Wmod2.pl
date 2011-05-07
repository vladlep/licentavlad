:-module(mod2,[wcount2/1]).

wcount2(Nr) :-
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module_problem/common.pl'),
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module_problem/module2.pl'),
	findall(R1,fileS(_,_,R1),Result),
	calc(Result,Nr).

