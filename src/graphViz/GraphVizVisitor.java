package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;

 import ast.Program ;
 import ast.Expr0 ;
 import ast.Expr1 ;
 import ast.Strin ;
import ast.Sup;
import ast.Supeg;
import ast.In ;
import ast.Inf;
import ast.Infeg;
import ast.Nil ;
import ast.Plus;
 import ast.IfThen ;
 import ast.While ;
 import ast.For ;
 import ast.Break ;
 import ast.Let ;
import ast.Minus;
import ast.Mul;
import ast.Print ;
 import ast.Functiondeclaration ;
 import ast.Egal ;
import ast.Egal2;
import ast.Typegal ;
 import ast.Exprnegation ;
 import ast.Exprseq ;
import ast.FdecWithoutfields;
import ast.Exprlist ;
 import ast.Exprlisbis ;
 import ast.Fieldlist ;
 import ast.Field ;
 import ast.Fieldlisbis ;
import ast.AccesVar;
import ast.Appelfunc ;
import ast.Idcall2;
import ast.Pointid ;
 import ast.Croexpr ;
import ast.Dif;
import ast.Div;
import ast.Dptegal;
import ast.Typedeclaration ;
 import ast.Typefields ;
 import ast.Typefield ;
 import ast.Typepredefined ;
import ast.Typeswithfieldlist;
import ast.Typeswithof;
import ast.Typeidid ;
 import ast.Variabledeclaration ;
 import ast.Vardec1 ;
 import ast.AstVisitor;
 import ast.Ast;
 import ast.Arrof;

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

        String buffer = this.nodeBuffer + this.linkBuffer + "}";
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
        this.nodeBuffer += String.format("\t%s [label=\"%s\", shape=\"box\", fillcolor=\"#fbb4ae\", style=filled];\n", node,label);

    }

    private void addNodeTerm(String node,String label){
        this.nodeBuffer += String.format("\t%s [label=\"%s\", shape=\"oval\", fillcolor=\"#ccebc5\", style=filled];\n", node,label);

    }

    @Override
    public String visit(Expr0 affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "|");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Expr1 affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "&");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Strin affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.strin);
        return nodeIdentifier;
    }

    @Override
    public String visit(In affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, String.valueOf(affect.in));
        return nodeIdentifier;
    }

    @Override
    public String visit(Nil affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.ni);
        return nodeIdentifier;
    }

    @Override
    public String visit(While affect) {
        String nodeIdentifier = this.nextState();
        String nodeIdentifier1 = this.nextState();
        String nodeIdentifier2 = this.nextState();

        this.addNode(nodeIdentifier1, "Condition Block");
        this.addNode(nodeIdentifier2, "Do Block");
        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "while");
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        this.addTransition(nodeIdentifier, nodeIdentifier2);
        this.addTransition(nodeIdentifier1, leftState);
        this.addTransition(nodeIdentifier2, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(For affect) {
        String nodeIdentifier = this.nextState();
        String nodeIdentifier1 = this.nextState();
        String nodeIdentifier2 = this.nextState();
        String nodeIdentifier3 = this.nextState();
        String nodeIdentifier4 = this.nextState();
        String nodeIdentifier5 = this.nextState();

        String min = affect.min.accept(this);
        String max = affect.max.accept(this);
        String regle = affect.regle.accept(this);

        this.addNode(nodeIdentifier, "for");
        
        
        this.addNode(nodeIdentifier5, "Var");
        this.addTransition(nodeIdentifier, nodeIdentifier5);
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier5, nodeIdentifier1);
        this.addNode(nodeIdentifier2, "Borne Min");
        this.addTransition(nodeIdentifier, nodeIdentifier2);
        this.addTransition(nodeIdentifier2, min);
        this.addNode(nodeIdentifier3, "Borne Max");
        this.addTransition(nodeIdentifier, nodeIdentifier3);
        this.addTransition(nodeIdentifier3, max);
        this.addNode(nodeIdentifier4, "Do Block");
        this.addTransition(nodeIdentifier, nodeIdentifier4);
        this.addTransition(nodeIdentifier4, regle);

        return nodeIdentifier;
    }

    @Override
    public String visit(Break affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.coup);
        return nodeIdentifier;
    }

    @Override
    public String visit(Let affect) {
        boolean dec=false;
        String nodeIdentifier = this.nextState();
        String nodeIdentifier1=nodeIdentifier;
        this.addNode(nodeIdentifier, "let");
        for(Ast ast : affect.lefts){
            if(ast!=null){
                if (!dec){
                    nodeIdentifier1 = this.nextState();
                    this.addNode(nodeIdentifier1,"Let Block");
                    this.addTransition(nodeIdentifier, nodeIdentifier1);
                    dec=true;
                }
                String state = ast.accept(this);
                this.addTransition(nodeIdentifier1, state);
            }
        }
        if(affect.right != null){
            String nodeIdentifier2 = this.nextState();
            this.addNode(nodeIdentifier2,"In Block");
            this.addTransition(nodeIdentifier, nodeIdentifier2);
            String state = affect.right.accept(this);
            this.addTransition(nodeIdentifier2, state);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Functiondeclaration affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "function");
        String functiondeclaration = affect.functiondeclaration.accept(this);
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        if(affect.typefields != null){
            String typefields = affect.typefields.accept(this);
            this.addTransition(nodeIdentifier, typefields);
        }
        this.addTransition(nodeIdentifier, functiondeclaration);
        return nodeIdentifier;
    }

    @Override
    public String visit(Egal affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "=");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        
        return nodeIdentifier;
    }

    @Override
    public String visit(Typegal affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "TypeEgal");
        String typeid = affect.typeid.accept(this);
        this.addTransition(nodeIdentifier, typeid);
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.eg);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprnegation affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Exprnegation");
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.neg);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprseq affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Exprseq");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        if(affect.exprseqbis != null){
            String exprseqbis = affect.exprseqbis.accept(this);
            this.addTransition(nodeIdentifier, exprseqbis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprlist affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Exprlist");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        if(affect.exprlistbis != null){
            String exprlistbis = affect.exprlistbis.accept(this);
            this.addTransition(nodeIdentifier, exprlistbis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprlisbis affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Exprlisbis");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        String exprlistbis = affect.exprlistbis.accept(this);
        this.addTransition(nodeIdentifier, exprlistbis);
        return nodeIdentifier;
    }

    @Override
    public String visit(Fieldlist affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Fieldlist");
        String field = affect.field.accept(this);
        this.addTransition(nodeIdentifier, field);
        if(affect.fieldlistbis != null){
            String fieldlistbis = affect.fieldlistbis.accept(this);
            this.addTransition(nodeIdentifier, fieldlistbis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Field affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Field");
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        return nodeIdentifier;
    }

    @Override
    public String visit(Fieldlisbis affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Fieldlisbis");
        String field = affect.field.accept(this);
        this.addTransition(nodeIdentifier, field);
        String fieldlisbis = affect.fieldlisbis.accept(this);
        this.addTransition(nodeIdentifier, fieldlisbis);
        return nodeIdentifier;
    }

    @Override
    public String visit(Appelfunc affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Appel fonction");
        String nodeIdentifier1 = this.nextState();
        String nodeIdentifier2 = this.nextState();
        
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addNode(nodeIdentifier2, "Id fonction");
        
        this.addTransition(nodeIdentifier, nodeIdentifier2);
        
        this.addTransition(nodeIdentifier2, nodeIdentifier1);
        System.out.println(affect.right);
        if (affect.right != null){
            String nodeIdentifier3 = this.nextState();
            this.addNode(nodeIdentifier3, "Parametres");
            this.addTransition(nodeIdentifier, nodeIdentifier3);
            String right = affect.right.accept(this);
            this.addTransition(nodeIdentifier3, right);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Pointid affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Pointid");
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        if(affect.lvaluebis != null){
            String lvaluebis = affect.lvaluebis.accept(this);
            this.addTransition(nodeIdentifier, lvaluebis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Croexpr affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Croexpr");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        if(affect.lvaluebis != null){
            String lvaluebis = affect.lvaluebis.accept(this);
            this.addTransition(nodeIdentifier, lvaluebis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typedeclaration affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Typedeclaration");
        String typeid = affect.typeid.accept(this);
        this.addTransition(nodeIdentifier, typeid);
        String type = affect.type.accept(this);
        this.addTransition(nodeIdentifier, type);
        return nodeIdentifier;
    }

    @Override
    public String visit(Typefields affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Typefields");
        String typefield = affect.typefield.accept(this);
        this.addTransition(nodeIdentifier, typefield);
        if(affect.typefieldsbis != null){
            String typefieldsbis = affect.typefieldsbis.accept(this);
            this.addTransition(nodeIdentifier, typefieldsbis);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typefield affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Typefield");
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String type = affect.type.accept(this);
        this.addTransition(nodeIdentifier, type);
        return nodeIdentifier;
    }

    @Override
    public String visit(Typepredefined affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.type);
        return nodeIdentifier;
    }

    @Override
    public String visit(Typeidid affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.id);
        return nodeIdentifier;
    }

    @Override
    public String visit(Variabledeclaration affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Variabledeclaration");
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String bis = affect.bis.accept(this);
        this.addTransition(nodeIdentifier, bis);
        return nodeIdentifier;
    }

    @Override
    public String visit(Vardec1 affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Vardec1");
        String expr = affect.expr.accept(this);
        this.addTransition(nodeIdentifier, expr);
        String typeid = affect.typeid.accept(this);
        this.addTransition(nodeIdentifier, typeid);
        return nodeIdentifier;
    }


    @Override
    public String visit(Program affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "program");
        String nodeIdentifier1 = affect.expression.accept(this);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        return nodeIdentifier;
    }

    @Override
    public String visit(IfThen affect) {
        String nodeIdentifier = this.nextState();
        String nodeIdentifier1 = this.nextState();
        String nodeIdentifier2 = this.nextState();
        this.addNode(nodeIdentifier, "ifThen");
        this.addNode(nodeIdentifier1, "If Block");
        this.addNode(nodeIdentifier2, "Then Block");
        String left = affect.left.accept(this);
        String center = affect.center.accept(this);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        this.addTransition(nodeIdentifier, nodeIdentifier2);
        this.addTransition(nodeIdentifier1, left);
        this.addTransition(nodeIdentifier2, center);
        if(affect.right != null){
            String nodeIdentifier3 = this.nextState();
            this.addNode(nodeIdentifier3,"Else Block");
            String right = affect.right.accept(this);
            this.addTransition(nodeIdentifier, nodeIdentifier3);
            this.addTransition(nodeIdentifier3, right);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Print affect) {
        String nodeIdentifier = this.nextState();
        String nodeIdentifier1 = affect.fils.accept(this);
        this.addNode(nodeIdentifier, "print");
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        return nodeIdentifier;
    }

    @Override
    public String visit(Dif affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "<>");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Inf affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "<");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Sup affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, ">");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Infeg affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "<=");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Supeg affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, ">=");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Plus affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "+");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Minus affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "-");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Mul affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "*");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Div affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "/");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(FdecWithoutfields affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "function");
        String functiondeclaration = affect.functiondeclaration.accept(this);
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        this.addTransition(nodeIdentifier, functiondeclaration);
        return nodeIdentifier;
    }

    @Override
    public String visit(Egal2 affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "=");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Typeswithof affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "types");
        String typeid = affect.typeid.accept(this);
        this.addTransition(nodeIdentifier, typeid);
        String expr1 = affect.expr1.accept(this);
        this.addTransition(nodeIdentifier, expr1);
        String nodeIdentifier1 = this.nextState();
        this.addNodeTerm(nodeIdentifier1, affect.of);
        this.addTransition(nodeIdentifier, nodeIdentifier1);
        String expr2 = affect.expr2.accept(this);
        this.addTransition(nodeIdentifier, expr2);
        return nodeIdentifier;
    }

    @Override
    public String visit(Typeswithfieldlist affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "types");
        String typeid = affect.typeid.accept(this);
        this.addTransition(nodeIdentifier, typeid);
        if(affect.fieldlist != null){
            String fieldlist = affect.fieldlist.accept(this);
            this.addTransition(nodeIdentifier, fieldlist);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Idcall2 affect) {
        String nodeIdentifier = this.nextState();
        this.addNodeTerm(nodeIdentifier, affect.id);
        return nodeIdentifier;
    }

    @Override
    public String visit(Dptegal affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, ":=");
        
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Arrof affect) {
        String nodeIdentifier = this.nextState();

        String type = affect.type.accept(this);

        this.addNode(nodeIdentifier, "Arrayof");
        
        this.addTransition(nodeIdentifier, type);

        return nodeIdentifier;
    }

    @Override
    public String visit(AccesVar affect) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Acces Var");
        String nodeIdentifier1 = this.nextState();
        String nodeIdentifier2 = this.nextState();
        
        this.addNodeTerm(nodeIdentifier1, affect.id);
        this.addNode(nodeIdentifier2, "Var");
        
        this.addTransition(nodeIdentifier, nodeIdentifier2);
        
        this.addTransition(nodeIdentifier2, nodeIdentifier1);
        if (affect.right != null){
            String nodeIdentifier3 = this.nextState();
            this.addNode(nodeIdentifier3, "Valeur");
            this.addTransition(nodeIdentifier, nodeIdentifier3);
            String right = affect.right.accept(this);
            this.addTransition(nodeIdentifier3, right);
        }
        return nodeIdentifier;
    }
    
}
