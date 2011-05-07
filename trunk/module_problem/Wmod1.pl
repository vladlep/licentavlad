:-module(mod1,[wcount/1]).


wcount(Nr) :-
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module_problem/common.pl'),
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module_problem/module1.pl'),
	findall(R1,fileS(_,_,R1),Result),
	calc(Result,Nr).

