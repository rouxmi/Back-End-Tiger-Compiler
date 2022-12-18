package tds;


import java.util.ArrayList;
import java.util.Stack;

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
import ast.Ast;
import ast.AstVisitor;
import ast.Arrof;


public class TdsVisitor implements AstVisitor<String> {
    private Stack<Table> tdsStack = new Stack<Table>();
    private Table tds;
    private int state;
    private boolean varDec;
    private String varid;
    private boolean typedec;
    private boolean funcdec;
    private boolean typefuncdec;
    private String funcid;
    private boolean Dec;
    private String decvalue;
    private String functype;
    private ArrayList<VarType> args;
    private String Typetype; 
    private ArrayList<String> elementtype;
    private boolean elementtypedec;
    private String tailletype;
    private int cmptailletype;
    private boolean tailledec;


    public TdsVisitor() {
        this.state=0;
        tds = new Table(-1,"programme");
        tdsStack.push(tds);
        funcdec=false;
        varDec=false;
        typedec=false;
        typefuncdec=false;
        tailletype=null;
    }

    public void afficher() {
        while (!tdsStack.empty()){
            this.tds=tdsStack.pop();
        }
        this.tds.afficher();
    }
    
    public void addFils(String nompere) {
        Table tdsFils = new Table(tds.getId(),nompere);
        tdsStack.push(tdsFils);
        tds.addFils(tdsFils);
        tds = tdsFils;
    }

    public void addProcFonc(ProcFonc procfonc){
            tds.addProcFonc(procfonc);
    }

    private String nextState(){
        String returnedState = String.valueOf(this.state);
        this.state++;
        return returnedState;
    }

    public void closeFils() {
        tdsStack.pop();
        tds = tdsStack.peek();
    }

    public void addVarType(VarType varType) {
        tds.addVarType(varType);
    }


    //fonction visite
    @Override
    public String visit(Expr0 affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Expr1 affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Strin affect) {
        String nodeIdentifier = this.nextState();
        if (varDec && !typedec){
            VarType var = new VarType(varid, "String", "Var");
            this.addVarType(var);
            varDec=false;
        }
        if (tailledec){
            tailletype+=affect.strin;
            
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(In affect) {
        String nodeIdentifier = this.nextState();
        if (varDec && !tailledec){
            VarType var = new VarType(varid, "Int", "Var");
            this.addVarType(var);
            varDec=false;
        }
        if (tailledec){
            tailletype+=String.valueOf(affect.in);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Nil affect) {
        String nodeIdentifier = this.nextState();
        return nodeIdentifier;
    }

    @Override
    public String visit(While affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(For affect) {
        String nodeIdentifier = this.nextState();

        affect.min.accept(this);
        affect.max.accept(this);
        affect.regle.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Break affect) {
        String nodeIdentifier = this.nextState();
        return nodeIdentifier;
    }

    @Override
    public String visit(Let affect) {
        String nodeIdentifier = this.nextState();
        for(Ast ast : affect.lefts){
            if(ast!=null){
                ast.accept(this);
            }
        }
        if(affect.right != null){
            affect.right.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Functiondeclaration affect) {
        String nodeIdentifier = this.nextState();
        funcid = affect.id;
        funcdec=true;
        typefuncdec=false;
        args=new ArrayList<VarType>();
        if(affect.typefields != null){
            affect.typefields.accept(this);
        }
        ProcFonc func = new ProcFonc(funcid, functype, args);
        this.addProcFonc(func);
        this.addFils(funcid);
        for (VarType var:args){
            this.addVarType(var);
        }
        funcdec=false;
        affect.functiondeclaration.accept(this);
        
        this.closeFils();
        return nodeIdentifier;
    }

    @Override
    public String visit(Egal affect) {
        String nodeIdentifier = this.nextState();
        affect.expr.accept(this);
        
        return nodeIdentifier;
    }

    @Override
    public String visit(Typegal affect) {
        String nodeIdentifier = this.nextState();
        typefuncdec=true;
        affect.typeid.accept(this);
        typefuncdec=false;
        affect.expr.accept(this);
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprnegation affect) {
        String nodeIdentifier = this.nextState();
        affect.expr.accept(this);
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprseq affect) {
        String nodeIdentifier = this.nextState();
        affect.expr.accept(this);
        if(affect.exprseqbis != null){
            affect.exprseqbis.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprlist affect) {
        String nodeIdentifier = this.nextState();

        affect.expr.accept(this);

        if(affect.exprlistbis != null){
            affect.exprlistbis.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprlisbis affect) {
        String nodeIdentifier = this.nextState();

        affect.expr.accept(this);
        if(affect.exprlistbis != null){
            affect.exprlistbis.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Fieldlist affect) {
        String nodeIdentifier = this.nextState();

        affect.field.accept(this);

        if(affect.fieldlistbis != null){
            affect.fieldlistbis.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Field affect) {
        String nodeIdentifier = this.nextState();

        

        affect.expr.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Fieldlisbis affect) {
        String nodeIdentifier = this.nextState();

        affect.field.accept(this);

        affect.fieldlisbis.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Appelfunc affect) {
        String nodeIdentifier = this.nextState();
        if (tailledec){
            tailletype=affect.id;
        }
        

        if (affect.right != null){
            affect.right.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Pointid affect) {
        String nodeIdentifier = this.nextState();

        

        if(affect.lvaluebis != null){
            affect.lvaluebis.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Croexpr affect) {
        String nodeIdentifier = this.nextState();
        affect.expr.accept(this);

        if(affect.lvaluebis != null){
            affect.lvaluebis.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typedeclaration affect) {
        String nodeIdentifier = this.nextState();
        typedec=true;
        affect.typeid.accept(this);
        typedec=false;
        Dec=true;
        Typetype="Type";
        cmptailletype=0;
        elementtype= new ArrayList<String>();
        affect.type.accept(this);
        Dec=false;
        if (cmptailletype>0){
            tailletype=String.valueOf(cmptailletype);
        }
        if (Typetype.equals("Type") || Typetype.equals("Array of")){
            VarType var = new VarType(varid, Typetype, "Type",elementtype);
            this.addVarType(var);
        }else{
            VarType var = new VarType(varid, Typetype, "Type",elementtype,tailletype);
            this.addVarType(var);
        }
        tailletype=null;
        return nodeIdentifier;
    }

    @Override
    public String visit(Typefields affect) {
        String nodeIdentifier = this.nextState();
        affect.typefield.accept(this);

        if(affect.typefieldsbis != null){
            affect.typefieldsbis.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typefield affect) {
        String nodeIdentifier = this.nextState();
        
        
        varDec=true;
        if (Dec){
            Typetype="TypeList";
            decvalue=varid+"."+affect.id;
            elementtypedec=true;
            cmptailletype++;
        }else{
            varid=affect.id;
        }

        affect.type.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Typepredefined affect) {
        String nodeIdentifier = this.nextState();
        if (varDec  && !funcdec && !Dec ){
            VarType var ;
            if (tailletype==null){
                var = new VarType(varid, affect.type, "Var");
            }else{

                var = new VarType(varid, affect.type, "Var",tailletype);
                tailletype=null;
            }
            this.addVarType(var);
            varDec=false;
        }
        if (varDec  && !funcdec && Dec){
            VarType var ;
            if (tailletype==null){
                var = new VarType(decvalue, affect.type, "Var");
                var.initialized=false;
            }else{
                var = new VarType(decvalue, affect.type, "Var",tailletype);
                tailletype=null;
            }
            this.addVarType(var);
            varDec=false;
        }
        if (funcdec && !tailledec && !elementtypedec){
            args.add(new VarType(varid, affect.type, "Var"));
        }
        if (typefuncdec && !tailledec && !elementtypedec){
            functype=affect.type;
        }
        if (Dec && !tailledec && !elementtypedec){
            elementtype.add(affect.type+",");
        }
        if (elementtypedec && !tailledec){
            elementtype.add(affect.type+",");
            elementtypedec=false;
        }
        if (tailledec){
            tailletype+=affect.type;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typeidid affect) {
        String nodeIdentifier = this.nextState();
        if (varDec && !Dec && !funcdec  ){
            VarType var ;
            if (tailletype==null){
                var = new VarType(varid, affect.id, "Var");
            }else{

                var = new VarType(varid, affect.id, "Var",tailletype);
                tailletype=null;
            }
            this.addVarType(var);
            varDec=false;
        }
        if (varDec && !funcdec && Dec  ){
            VarType var ;
            if (tailletype==null){
                var = new VarType(decvalue, affect.id, "Var");
            }else{
                var = new VarType(decvalue, affect.id, "Var",tailletype);
                tailletype=null;
            }
            this.addVarType(var);
            varDec=false;
            //System.out.println(Dec+" ,"+typedec+" ,"+elementtypedec+" ,"+varDec+","+funcdec+","+typefuncdec+","+elementtypedec+","+tailledec+"\n");
            //System.out.print(decvalue+"\n");
        }
        if (funcdec && !elementtypedec && !tailledec){
            args.add(new VarType(varid, affect.id, "Var"));
        }
        if (typefuncdec && !elementtypedec && !tailledec){
            functype=affect.id;
        }
        if (typedec && !elementtypedec && !tailledec){
            varid=affect.id;
        }
        if (Dec && !elementtypedec && !tailledec){
            elementtype.add(affect.id+",");
        }
        if (elementtypedec && !tailledec){
            elementtype.add(affect.id+",");
            elementtypedec=false;
        }
        if (tailledec){
            tailletype+=affect.id;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Variabledeclaration affect) {
        String nodeIdentifier = this.nextState();
        varid=affect.id;
        varDec=true;
        affect.bis.accept(this);
        return nodeIdentifier;
    }

    @Override
    public String visit(Vardec1 affect) {
        String nodeIdentifier = this.nextState();

        affect.expr.accept(this);
        
        affect.typeid.accept(this);
        return nodeIdentifier;
    }


    @Override
    public String visit(Program affect) {
        String nodeIdentifier = this.nextState();


        affect.expression.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(IfThen affect) {
        String nodeIdentifier = this.nextState();
        affect.left.accept(this);
        affect.center.accept(this);

        if(affect.right != null){
            affect.right.accept(this);

        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Print affect) {
        String nodeIdentifier = this.nextState();
        affect.fils.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Dif affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Inf affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Sup affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Infeg affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Supeg affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Plus affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        if (tailledec){
            tailletype+="+";
        }
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Minus affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        if (tailledec){
            tailletype+="-";
        }
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Mul affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        if (tailledec){
            tailletype+="*";
        }
        affect.right.accept(this);



        return nodeIdentifier;
    }

    @Override
    public String visit(Div affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        if (tailledec){
            tailletype+="/";
        }
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(FdecWithoutfields affect) {
        String nodeIdentifier = this.nextState();
        funcid = affect.id;
        funcdec=true;
        typefuncdec=false;
        ProcFonc func = new ProcFonc(funcid, functype , null);
        this.addProcFonc(func);
        this.addFils(funcid);

        affect.functiondeclaration.accept(this);
        
        this.closeFils();
        return nodeIdentifier;
    }

    @Override
    public String visit(Egal2 affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Typeswithof affect) {
        String nodeIdentifier = this.nextState();
        tailledec=true;
        tailletype="";
        affect.expr1.accept(this);
        tailledec=false;
        affect.typeid.accept(this);
        affect.expr2.accept(this);
        return nodeIdentifier;
    }

    @Override
    public String visit(Typeswithfieldlist affect) {
        String nodeIdentifier = this.nextState();
        affect.typeid.accept(this);
        if(affect.fieldlist != null){
            affect.fieldlist.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Idcall2 affect) {
        String nodeIdentifier = this.nextState();
        if (tailledec){
            tailletype+=affect.id;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Dptegal affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Arrof affect) {
        String nodeIdentifier = this.nextState();
        elementtypedec=true;
        affect.type.accept(this);
        elementtypedec=false;
        Typetype="Array of";

        return nodeIdentifier;
    }

    @Override
    public String visit(AccesVar affect) {
        String nodeIdentifier = this.nextState();
        if (tailledec){
            tailletype=affect.id;
        }
        

        if (affect.right != null){
            affect.right.accept(this);

        }
        return nodeIdentifier;

    }
}