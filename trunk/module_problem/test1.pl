
%tetstez prima varianta. Observ ca e gresita!
test1:-
	module(modul1),
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module1.pl'),
	findall(R1,fileS(_,_,R1),Result),
	write(Result),
	module(modul2),
	consult('C:/Users/vll/vlad/LICENTA/licentavlad/module2.pl'),
	findall(R2,fileS(_,_,R2),Res2),
	write(Res2),
	retractall(fileS).

















