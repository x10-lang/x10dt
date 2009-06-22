%options fp=PatternX10Parser
%options package=org.eclipse.imp.x10dt.formatter.parser
%options variables=nt

%options list
%options la=6
%options conflicts
%options softkeywords

%options template=btParserTemplate.gi
%options parent_saved,automatic_ast=toplevel,visitor=preorder,ast_directory=./ast,ast_type=ASTNode
%options import_terminals="Pattern-X10Lexer.gi"

%Notice
/.
// This is an experimental product.
./
%End

%Globals
    /.
    // this is a test
     import org.eclipse.imp.parser.*;
    ./
   
%End 

%Import
    x10.g
%End


%Define
    --
    -- Definition of macros used in the parser template
    --
    $ast_class /.IAst./
    $additional_interfaces /. , IParser ./
%End

%Start
   Pattern
%End

%Rules 
  Pattern ::= Statement
            | Expression
 
%End
