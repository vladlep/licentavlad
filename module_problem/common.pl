calc([],0).
calc([_|Tail],NrOfElements) :- calc(Tail,Left), NrOfElements is Left+1.
