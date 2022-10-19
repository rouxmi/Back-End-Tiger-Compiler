grammar gram;

program : instruction ;

instruction : INT;

//token

INT : ('0'..'9')+;