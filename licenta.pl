% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.


%nod contine metode : id unic generat de mine, id important din fisier
%nume metoda + lista metode

:- dynamic nod/4.
run(X):-
	consult('I:/vlad/serios/faculta/licenta/licentavlad/test1.pl'),
	%writef('afsadf'),
	I is 11,
	callT(X,_,Z,_,_,_,_),
	write(Z),

	methodT(Z,_,B,_,_,_,_),
	assert(nod(I,Z,B,[])),

	writef(B).


main(Z):- open('I:/vlad/serios/faculta/licenta/licentavlad/test1.pl', read, Z), set_output(Z), listing,
set_output(screen), close(Z).


retr(X):- retractall(X).
