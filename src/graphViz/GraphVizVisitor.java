package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;

 import ast.Program ;
 import ast.Expr0 ;
 import ast.Expr1 ;
 import ast.Expr2 ;
 import ast.Expr3 ;
 import ast.Expr4 ;
 import ast.Idcal ;
 import ast.Strin ;
 import ast.In ;
 import ast.Nil ;
 import ast.Neg ;
 import ast.TypDec ;
 import ast.ExprSeq ;
 import ast.ExprC ;
 import ast.IfThen ;
 import ast.While ;
 import ast.For ;
 import ast.Break ;
 import ast.Let ;
 import ast.Prt ;
 import ast.Typ ;
 import ast.Print ;
 import ast.Else ;
 import ast.Vide ;
 import ast.TypeDec ;
 import ast.VarDec ;
 import ast.FuncDec ;
 import ast.Functio ;
 import ast.Functiondeclaration ;
 import ast.Egal ;
 import ast.Typegal ;
 import ast.Exprnegation ;
 import ast.Exprseq ;
 import ast.Pointexpr ;
 import ast.Nul1 ;
 import ast.Exprlist ;
 import ast.Exprlisbis ;
 import ast.Nul2 ;
 import ast.Fieldlist ;
 import ast.Field ;
 import ast.Nul3 ;
 import ast.Fieldlisbis ;
 import ast.Idcall ;
 import ast.Pointid ;
 import ast.Croexpr ;
 import ast.Nul4 ;
 import ast.Types ;
 import ast.Typedeclaration ;
 import ast.Typetypeid ;
 import ast.Typecro ;
 import ast.Arrof ;
 import ast.Typefields ;
 import ast.Virgtypefield ;
 import ast.Nul5 ;
 import ast.Typefield ;
 import ast.Typepredefined ;
 import ast.Typeidid ;
 import ast.Predefined ;
 import ast.Variabledeclaration ;
 import ast.Vardec1 ;
 import ast.Vardec2 ;
 import ast.AstVisitor;

public class GraphVizVisitor implements AstVisitor<String> {

    private int state;
    private String nodeBuffer;
    private String linkBuffer;

    public GraphVizVisitor(){
        this.state = 0;
        this.nodeBuffer = "digraph \"ast\"{\n\n\tnodesep=1;\n\tranksep=1;\n\n";
        this.linkBuffer = "\n";
    }

    public void dumpGraph(String filepath) throws IOException{
            
        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.nodeBuffer + this.linkBuffer ;
        byte[] strToBytes = buffer.getBytes();

        output.write(strToBytes);

        output.close();

    }


    private String nextState(){
        int returnedState = this.state;
        this.state++;
        return "N"+ returnedState;
    }

    private void addTransition(String from,String dest){
        this.linkBuffer += String.format("\t%s -> %s; \n", from,dest);

    }

    private void addNode(String node,String label){
        this.nodeBuffer += String.format("\t%s [label=\"%s\", shape=\"box\"];\n", node,label);

    }

    @Override
    public String visit(Expr0 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Expr1 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Expr2 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Expr3 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Expr4 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Idcal affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Strin affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(In affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nil affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Neg affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(TypDec affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ExprSeq affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ExprC affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(While affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(For affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Break affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Let affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Prt affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typ affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Else affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Vide affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(TypeDec affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(VarDec affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FuncDec affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Functio affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Functiondeclaration affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Egal affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typegal affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Exprnegation affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Exprseq affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Pointexpr affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nul1 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Exprlist affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Exprlisbis affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nul2 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Fieldlist affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Field affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nul3 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Fieldlisbis affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Idcall affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Pointid affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Croexpr affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nul4 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Types affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typedeclaration affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typetypeid affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typecro affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Arrof affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typefields affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Virgtypefield affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Nul5 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typefield affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typepredefined affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Typeidid affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Predefined affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Variabledeclaration affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Vardec1 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Vardec2 affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Program affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IfThen affect) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(Print affect) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
