%-----------------------------------------%
%1 = loose profile
classDelta(1,[
	      5 % delta atributes
	   ,0.6 % procent atributes

	     ,2 % delta methods
	   ,0.7 % procent methods
	      ,1 % delta Interfaces
	  ]).

methodDelta(1,[
	      2 %delta if
	       ,2
	      ]).

%-----------------------------------------%

% tight profile. Projects have to be closer mathces
classDelta(2,[
	      3 % delta atributes
	   ,0.8 % procent atributes

	     ,1 % delta methods
	   ,0.8 % procent methods
	      ,0 % delta Interfaces
	  ]).

methodDelta(2,[]).
