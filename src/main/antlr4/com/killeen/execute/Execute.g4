grammar Execute;

//sample commands:
//#EXECUTE genericProcess<string1.string2>
//#EXECUTE genericProcess<name of some process>
//#EXECUTE scenarioName

execute: 
    executeKeyword genericProcess
    | executeKeyword scenarioName  
    ;

executeKeyword:
    '#EXECUTE'
    ;
scenarioName: 
    STRING
    ;

genericProcess:
    'genericProcess' '<' STRING  '>'
    ;

STRING:
    [a-zA-Z][a-zA-Z0-9.]* // Match any letters or numbers starting with a letter. '.' also allowed
    ;
WS  :   [ \t\n\r]+ -> skip ;

