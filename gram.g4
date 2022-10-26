grammar gram;

program : expr;

expr : STRING-CONSTANT
    |INTEGER-CONSTANT
    |'nil'
    |lvalue
    |'-'expr
    |expr BINARY-OPERATOR expr
    |lvalue ':=' expr
    |ID '(' (expr-list)? ')'
    |'(' (expr-seq)? ')'
    |type-id '{' (field-list)? '}'
    |type-id '[' expr ']' 'of' expr
    |'if' expr 'then' expr
    |'if' expr 'then' expr 'else' expr
    |'while' expr 'do' expr
    |'for' id ':=' expr 'to' expr 'do' expr
    |'break'
    |'let' declaration-list 'in' (expr-seq)? 'end'
    |commentaire

commentaire : '/*' (STRING-CONSTANT|ESPACE)* '*/'

expr-seq : expr
    |expr-seq ';' expr

expr-list : expr
    |expr-list ',' expr

field-list : id '=' expr
    |field-list ',' id '=' expr

lvalue : id
    |lvalue '.' id
    |lvalue '[' expr ']'

declaration-list : declaration
    |declaration-list declaration

declaration : type-declaration
    |variable-declaration
    |function-declaration

type-declaration : 'type' type-id = type

type : type-id 
    |'{' (type-fields)? '}'
    |'array of' type-id

type-fields : type-field
    |type-fields ',' type-field

type-field : id ':' type-id

type-id : id

variable-declaration : 'var' id ':=' expr
    | 'var' id ':' type-id ':=' expr


function-declaration : 'function' id '(' (type-fields)? ')' '=' expr
    |'function' id '(' (type-fields)? ')' ':' type-id '=' expr

//token

INTEGER-CONSTANT : ('0'..'9')+;
STRING-CONSTANT : 






