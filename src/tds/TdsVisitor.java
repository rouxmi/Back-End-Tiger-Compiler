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
import ast.Idcall ;
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
    private String functype;
    private ArrayList<VarType> args;
    private boolean argsfunc;


    public TdsVisitor() {
        this.state=0;
        tds = new Table(-1);
        tdsStack.push(tds);
        funcdec=false;
        varDec=false;
        argsfunc=false;
        typedec=false;
        typefuncdec=false;
    }

    public void afficher() {
        while (!tdsStack.empty()){
            this.tds=tdsStack.pop();
            System.out.print(tds.getId());
        }
        this.tds.afficher();
    }
    
    public void addFils() {
        Table tdsFils = new Table(tds.getId());
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
            VarType var = new VarType(varid, "String");
            this.addVarType(var);
            varDec=false;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(In affect) {
        String nodeIdentifier = this.nextState();
        if (varDec){
            VarType var = new VarType(varid, "Int");
            this.addVarType(var);
            varDec=false;
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
        argsfunc=false;
        typefuncdec=false;
        args=new ArrayList<VarType>();
        if(affect.typefields != null){
            argsfunc=true;
            affect.typefields.accept(this);
        }
        ProcFonc func;
        if (argsfunc){
            func = new ProcFonc(funcid, functype, args);
        }
        else{
            func = new ProcFonc(funcid, functype , null);
        }

        this.addProcFonc(func);
        this.addFils();
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

        affect.exprlistbis.accept(this);

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
    public String visit(Idcall affect) {
        String nodeIdentifier = this.nextState();

        

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

        affect.typeid.accept(this);

        affect.type.accept(this);

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
        
        varid=affect.id;
        varDec=true;

        affect.type.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Typepredefined affect) {
        String nodeIdentifier = this.nextState();
        typedec=true;
        if (varDec && !funcdec){
            VarType var = new VarType(varid, affect.type);
            this.addVarType(var);
            varDec=false;
        }
        if (funcdec){
            args.add(new VarType(varid, affect.type));
        }
        if (typefuncdec){
            functype=affect.type;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Typeidid affect) {
        String nodeIdentifier = this.nextState();
        typedec=true;
        if (varDec && !funcdec){
            VarType var = new VarType(varid, affect.id);
            this.addVarType(var);
            varDec=false;
        }
        if (funcdec){
            args.add(new VarType(varid, affect.id));
        }
        if (typefuncdec){
            functype=affect.id;
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
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Minus affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Mul affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);



        return nodeIdentifier;
    }

    @Override
    public String visit(Div affect) {
        String nodeIdentifier = this.nextState();

        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(FdecWithoutfields affect) {
        String nodeIdentifier = this.nextState();
        affect.functiondeclaration.accept(this);
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
        affect.typeid.accept(this);
        affect.expr1.accept(this);
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

        affect.type.accept(this);


        return nodeIdentifier;
    }
}