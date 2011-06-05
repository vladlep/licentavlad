%loose profile
projectDelta(loose,
	     [
	      1 % MaxDeltaClasses- diferenta maxima intre nr de clase din primul si cel de-al doilea proiect

	     ]).
classDelta(loose, % profil larg, care genereaza mai multe match-uri	   [
	   [
	    5  % delta atributes
	   ,0.6% procent atributes

	   ,3  % delta methods

	   ,1  % delta Interfaces
	   ,0.5%ProcentMatchHigh-min procent of method matches for classe to be high
	   ,1  %MaxNumberOfUnmathcingMethods  - dif. max intre match si nr clase din fiecare proiect
	       % pentru a declara o clasa medium
	   ,0.5%ProcentMinOfMatchingMethods - mi procent pt a declara o clasa med.
	   ]).

methodDelta(loose,
	    [
	      2 %delta if
	      ]).

%-----------------------------------------%

% tight profile. Projects have to be closer mathces
