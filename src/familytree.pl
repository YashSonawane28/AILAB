male(tanhaji).
male(karbhari).
male(baburao).
male(shantaram).
male(sanjay).
male(vijay).
male(rajendra).
male(balkrishna).
male(kailas).
male(sagar).
male(diptej).
male(shivam).
male(yash).
male(amol).
male(rahul).
male(abhijeet).
female(kusum).
female(aasrabai).
female(shobha).
female(neeta).
female(nirmala).
female(yogita).
female(savita).
female(sunita).
female(vaishali).
female(neha).
female(apeksha).
female(harshada).
female(tanvi).
female(akanksha).

husband(karbhari,kusum).
husband(baburao,aasrabai).
husband(shantaram,shobha).
husband(sanjay,neeta).
husband(vijay,nirmala).
husband(rajendra,yogita).
husband(balkrishna, savita).
husband(kailas, sunita).

parent(tanhaji, karbhari).
parent(tanhaji, baburao).
parent(karbhari, shantaram).
parent(karbhari, sanjay).
parent(kusum, shantaram).
parent(kusum, sanjay).
parent(shantaram, vaishali).
parent(shantaram, sagar).
parent(shantaram, neha).
parent(shobha, vaishali).
parent(shobha, sagar).
parent(shobha, neha).
parent(sanjay, diptej).
parent(sanjay, apeksha).
parent(neeta, diptej).
parent(neeta, apeksha).
parent(baburao, vijay).
parent(baburao, rajendra).
parent(baburao, savita).
parent(baburao, sunita).
parent(aasrabai, vijay).
parent(aasrabai, rajendra).
parent(aasrabai, savita).
parent(aasrabai, sunita).
parent(vijay, shivam).
parent(vijay, harshada).
parent(nirmala, shivam).
parent(nirmala, harshada).
parent(rajendra, yash).
parent(rajendra, tanvi).
parent(yogita, yash).
parent(yogita, tanvi).
parent(savita, amol).
parent(savita, rahul).
parent(balkrishna, amol).
parent(balkrishna, rahul).
parent(sunita, abhijeet).
parent(sunita, akanksha).
parent(kailas, abhijeet).
parent(kailas, akanksha).

wife(X, Y) :- husband(Y, X).

father(X, Y) :- male(X), parent(X, Y).
mother(X, Y) :- female(X), parent(X, Y).


father_in_law(X, Y) :- male(X), parent(X, Z), husband(Z, Y); male(X), parent(X, Z), wife(Z, Y).
mother_in_law(X, Y) :- female(X), parent(X, Z), husband(Z, Y); male(X), parent(X, Z), wife(Z, Y).


son(X, Y) :- male(X), parent(Y, X).
daughter(X, Y) :- female(X), parent(Y, X).


son_in_law(X, Y) :- male(X), parent(Y, Z), wife(Z,X).
daughter_in_law(X, Y) :- female(X), parent(Y, Z), wife(X, Z).


grandfather(X, Z) :- father(X, Y), parent(Y, Z).
grandmother(X, Z) :- mother(X, Y), parent(Y, Z).

grandnephew(X,Y) :- grandfather(Z, X), sibling(Z, Y), male(X);nephew(Z, Y), son(X, Z); neice(Z, Y), son(X, Z).
grandneice(X,Y) :- grandfather(Z, X), sibling(Z, Y), female(X); nephew(Z, Y), daughter(X, Z); neice(Z, Y), daughter(X, Z).


grandson(X, Z) :- son(X, Y), parent(Z, Y).
granddaughter(X, Z) :- daughter(X, Y), parent(Z, Y).


great_grand_son(X, Z) :- son(X, Y), grandfather(Z, Y).
great_grand_daughter(X, Z) :- daughter(X, Y), grandfather(Z, Y).


sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.


cousin(X, Y) :- parent(Z, X), parent(W, Y), sibling(Z, W).


uncle(X, Y) :- parent(Z, Y), sibling(X, Z), male(X); wife(Z, X), parent(W, Y), sibling(Z, W).
aunt(X, Y) :- cousin(Z, Y), mother(X, Z), female(X); father(Z, Y), sibling(X, Z), female(X).


second_uncle(X, Y) :- great_uncle(Z, Y), male(X), parent(Z, X).
second_aunt(X, Y) :- great_uncle(Z, Y), female(X), parent(Z, X); second_uncle(Z, Y), wife(X, Z).

nephew(X, Y) :- male(X), parent(Z, X), sibling(Z, Y); uncle(Z, X), husband(Z, Y),male(X); uncle(Y, X), male(X).
neice(X, Y) :- female(X), parent(Z, X), sibling(Z, Y); uncle(Z, X), husband(Z, Y),female(X); uncle(Y, X), female(X).

second_nephew(X, Y) :- father(Z,X), cousin(Z,Y), male(Y); aunt_in_law(Z, Y), grandson(X, Z); aunt(Z, Y), grandson(X, Z).
second_neice(X, Y) :- second_uncle(Y, X), female(X).

neice_in_law(X, Y) :- female(X), nephew(Z, Y), husband(Z, X);aunt_in_law(Z, Y), granddaughter(X, Z).

great_uncle(X, Y) :- uncle(X, Z), parent(Z, Y).


great_aunt(X, Y) :- aunt(X, Z), parent(Z, Y).


second_cousin(X, Y) :- grandfather(Z, X), grandfather(W,Y),sibling(W, Z).

great_grandfather(X, Z) :- father(X, Y), grandfather(Y, Z).

cousin_in_law(X, Y) :- cousin(X, Z), husband(Z, Y); cousin(Y, Z), wife(X, Z); cousin(X, Z), wife(Z, Y); wife(Z, X), cousin(Z, Y).

brother_in_law(X, Y) :- sibling(X, Z), husband(Z, Y), male(X); sibling(Y, Z), husband(X, Z); sister_in_law(Z,Y), husband(X, Z).

sister_in_law(X, Y) :- sibling(X, Z),  husband(Z, Y), female(X) ; husband(Z, X), sibling(Z, Y), female(X); father_in_law(Z, X), father_in_law(Z, Y),female(X); sibling(X, Z), wife(Z, Y),female(X).
uncle_in_law(X,Y) :- uncle(X, Z),husband(Z, Y).
aunt_in_law(X,Y) :- aunt(X, Z),husband(Z, Y).


grandfather_in_law(X, Y) :- grandfather(X, Z), husband(Z, Y).
granddaughter_in_law(X, Y) :- grandfather_in_law(Y, X).


find_relationship(X, Y) :-
    husband(X, Y), write(X), write(' is the husband of '), write(Y), nl.
find_relationship(X, Y) :-
    wife(X, Y), write(X), write(' is the wife of '), write(Y), nl.
find_relationship(X, Y) :-
    father(X, Y), write(X), write(' is the father of '), write(Y), nl.
find_relationship(X, Y) :-
    mother(X, Y), write(X), write(' is the mother of '), write(Y), nl.
find_relationship(X, Y) :-
    father_in_law(X, Y), write(X), write(' is the father in law of '), write(Y), nl.
find_relationship(X, Y) :-
    mother_in_law(X, Y), write(X), write(' is the mother in law of '), write(Y), nl.
find_relationship(X, Y) :-
    son(X, Y), write(X), write(' is the son of '), write(Y), nl.
find_relationship(X, Y) :-
    daughter(X, Y), write(X), write(' is the daughter of '), write(Y), nl.
find_relationship(X, Y) :-
    grandfather(X, Y), write(X), write(' is the grandfather of '), write(Y), nl.
find_relationship(X, Y) :-
    grandson(X, Y), write(X), write(' is the grandson of '), write(Y), nl.
find_relationship(X, Y) :-
    granddaughter(X, Y), write(X), write(' is the granddaughter of '), write(Y), nl.
find_relationship(X, Y) :-
    great_grand_son(X, Y), write(X), write(' is the great grandson of '), write(Y), nl.
find_relationship(X, Y) :-
    great_grand_daughter(X, Y), write(X), write(' is the great granddaughter of '), write(Y), nl.
find_relationship(X, Y) :-
    sibling(X, Y), write(X), write(' is the sibling of '), write(Y), nl.
find_relationship(X, Y) :-
    father(Z, Y), mother(X, Z), write(X), write(' is the grandmother of '), write(Y), nl.
find_relationship(X, Y) :-
    great_uncle(X, Y), write(X), write(' is the great-uncle of '), write(Y), nl.
find_relationship(X, Y) :-
    great_aunt(X, Y), write(X), write(' is the great-aunt of '), write(Y), nl.
find_relationship(X, Y) :-
    uncle(X, Y), write(X), write(' is the uncle of '), write(Y), nl.
find_relationship(X, Y) :-
    aunt(X, Y), write(X), write(' is the aunt of '), write(Y), nl.
find_relationship(X, Y) :-
    second_uncle(X, Y), write(X), write(' is the second uncle of '), write(Y), nl.
find_relationship(X, Y) :-
    second_aunt(X, Y), write(X), write(' is the second aunt of '), write(Y), nl.
find_relationship(X, Y) :-
    nephew(X, Y), write(X), write(' is the nephew of '), write(Y), nl.
find_relationship(X, Y) :-
    neice(X, Y), write(X), write(' is the neice of '), write(Y), nl.
find_relationship(X, Y) :-
    second_nephew(X, Y), write(X), write(' is the second nephew of '), write(Y), nl.
find_relationship(X, Y) :-
    second_neice(X, Y), write(X), write(' is the second neice of '), write(Y), nl.
find_relationship(X, Y) :-
    cousin(X, Y), write(X), write(' is the cousin of '), write(Y), nl.
find_relationship(X, Y) :-
    second_cousin(X, Y), write(X), write(' is the second cousin of '), write(Y), nl.
find_relationship(X, Y) :-
    cousin_in_law(X, Y), write(X), write(' is the cousin in law of '), write(Y), nl.
find_relationship(X, Y) :-
    brother_in_law(X, Y), write(X), write(' is the brother in law of '), write(Y), nl.
find_relationship(X, Y) :-
    sister_in_law(X, Y), write(X), write(' is the sister in law of '), write(Y), nl.
find_relationship(X, Y) :-
    son_in_law(X, Y), write(X), write(' is the son in law of '), write(Y), nl.
find_relationship(X, Y) :-
    daughter_in_law(X, Y), write(X), write(' is the daughter in law of '), write(Y), nl.
find_relationship(X, Y) :-
    neice_in_law(X, Y), write(X), write(' is the neice in law of '), write(Y), nl.
find_relationship(X, Y) :-
    grandnephew(X, Y), write(X), write(' is the grandnephew of '), write(Y), nl.
find_relationship(X, Y) :-
    grandneice(X, Y), write(X), write(' is the grandneice of '), write(Y), nl.
find_relationship(X, Y) :-
    uncle_in_law(X, Y), write(X), write(' is the uncle in law of '), write(Y), nl.
find_relationship(X, Y) :-
    aunt_in_law(X, Y), write(X), write(' is the aunt in law of '), write(Y), nl.
find_relationship(X, Y) :-
    great_grandfather(X, Y), write(X), write(' is the great-grandfather of '), write(Y), nl.
find_relationship(X, Y) :-
    grandfather_in_law(X, Y), write(X), write(' is the grand father in law of '), write(Y), nl.
find_relationship(X, Y) :-
    granddaughter_in_law(X, Y), write(X), write(' is the grand daughter in law of '), write(Y), nl.
find_relationship(X, Y) :- write('Relationship not defined between '), write(X), write(' and '), write(Y), nl.