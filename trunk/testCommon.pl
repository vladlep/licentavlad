testMyClass(ID):-
	Prj1 = 'test1.pl',
	load(Prj1),
	myClass(Prj1,ID),
	write(ID),
	writef("  ").

testCountMyClass(Nr):-
	findall(ID,testMyClass(ID),Result),
	count(Result,Nr).

testMyClass2(ID):-

	Prj1 = 'stockMarket.pl',
	load(Prj1),
	myClass(_,ID).
