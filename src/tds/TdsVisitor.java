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
import controlesemantique.Fonction;
import controlesemantique.AccesListe;
import controlesemantique.BoucleFor;
import controlesemantique.BreakCodeInutile;
import controlesemantique.Declaration;
import controlesemantique.Expression;
import controlesemantique.Division;
import controlesemantique.IfWhileInutile;
import controlesemantique.SimplificationCalcul;
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
import ast.Fieldlist ;
import ast.Field ;
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
    public Stack<Exception> exceptions = new Stack<Exception>();
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
    private boolean inutile;


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

    public Table getTds() {
        return tds;
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
            VarType var = new VarType(varid, "int", "Var");
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

        //Controle Semantique
        Expression.checktype(affect.left, "bool", this.tdsStack, this.tds);
        IfWhileInutile.warningIfInutile("while",affect.left,this.tds);
        affect.left.accept(this);
        int i=1;
        for(int j=1;j<=this.tds.fonctions.size();j++){
            if(this.tds.getProcFonc("While block "+j)!=null){
                i++;
            }
        }
        ProcFonc whileblock = new ProcFonc("While block "+i, "While",new ArrayList<VarType>());
        whileblock.setUsed();
        this.addProcFonc(whileblock);

        this.addFils("While block "+i);
        affect.right.accept(this);
        if(this.inutile){
            BreakCodeInutile.checkBreakDo(affect.right,"While");
        }
        this.inutile=false;
        this.closeFils();

        return nodeIdentifier;
    }

    @Override
    public String visit(For affect) {
        String nodeIdentifier = this.nextState();

        //Controles Semantiques
        Expression.checktype(affect.min, "int", this.tdsStack, this.tds);
        Expression.checktype(affect.max, "int", this.tdsStack, this.tds);
        BoucleFor.CheckBorneMinInfBorneMax(affect.min, affect.max, this.tds);
        BoucleFor.CheckBorneMinNotBorneMax(affect.min, affect.max, this.tds);
        
        // peut être à ajouter
        ArrayList<VarType> args = new ArrayList<VarType>();
        varDec=false;
        VarType var = new VarType(affect.id, "int", "Var");
        
        args.add(var);
        int i=1;
        for(int j=1;j<=this.tds.fonctions.size();j++){
            if(this.tds.getProcFonc("Boucle for "+j)!=null){
                i++;
            }
        }
        ProcFonc forboucle = new ProcFonc("Boucle for "+i, "for", args);
        forboucle.setUsed();
        this.addProcFonc(forboucle);
        this.addFils("Boucle for "+i);
        var.setUsed(true);
        this.addVarType(var);
        affect.min.accept(this);
        affect.max.accept(this);
        affect.regle.accept(this);
        if(this.inutile){
            BreakCodeInutile.checkBreakDo(affect.regle,"For");
        }
        this.inutile=false;
        this.closeFils();

        return nodeIdentifier;
    }

    @Override
    public String visit(Break affect) {
        String nodeIdentifier = this.nextState();
        Table tdsActuelle = new Table(tds.getId());
        tdsActuelle= tdsActuelle.joinTDS(tdsStack);
        BreakCodeInutile.checkBreakWellPlaced(tdsActuelle,tds.nom);
        this.inutile=true;
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
        for (Ast ast : affect.expr){
            ast.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Exprlist affect) {
        String nodeIdentifier = this.nextState();
        for (Ast ast : affect.expr){
            ast.accept(this);
        }
        return nodeIdentifier;
    }


    @Override
    public String visit(Fieldlist affect) {
        String nodeIdentifier = this.nextState();
        for (Ast ast : affect.field){
            ast.accept(this);
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
    public String visit(Appelfunc affect){
        String nodeIdentifier = this.nextState();

        

        //Controles Semantiques
        if (Declaration.checkFuncdeclared(affect.id, this.tdsStack, this.tds)){
            Fonction.checknombreparametres(affect,this.tdsStack,this.tds);
            Fonction.checktypeparametres(affect, this.tdsStack, this.tds);
            Fonction.checkdeclaration(affect, this.tdsStack, this.tds);
            tds.setUsed(tdsStack, affect.id, "Fonction");
        }

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
        String point = ".";
        String fils = affect.fils;
        String id;
        if(affect.id != null){
            id = affect.id;
        }
        else{
            id = Expression.getType(affect.left, tds, tdsStack);
        }
        String type = id + point + fils;
        Table tableActuelle = new Table(tds.getId());
        tableActuelle=tableActuelle.joinTDS(tdsStack);
        if (tableActuelle.getVarType(type) != null){
            tableActuelle.getVarType(type).setUsed(true);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Croexpr affect) {
        String nodeIdentifier = this.nextState();
        affect.expr.accept(this);

        //Controle semantique
        Expression.checktype(affect.expr, "int", this.tdsStack, this.tds);

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
        for (Ast ast : affect.typefield){
            ast.accept(this);
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
        }
        if (funcdec && !elementtypedec && !tailledec){
            if(args==null){
                args= new ArrayList<VarType>();
            }
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
        tds.setUsed(tdsStack,((Typeidid)affect.typeid).id, "Type");
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
        
        //Controle semantique
        if (Expression.checktype(affect.left, "bool", this.tdsStack, this.tds)){
            IfWhileInutile.warningIfInutile("if",affect.left,this.tds);
        }
        affect.left.accept(this);
        int i=1;
        for(int j=1;j<=this.tds.fonctions.size();j++){
            if(this.tds.getProcFonc("Then block "+j)!=null){
                i++;
            }
        }
        ProcFonc then = new ProcFonc("Then block "+i, "Then",new ArrayList<VarType>());
        then.setUsed();
        this.addProcFonc(then);
        this.addFils("Then block "+i);
        affect.center.accept(this);
    
        this.inutile=false;
        this.closeFils();
        if(affect.right != null){
            int k=1;
            for(int j=1;j<=this.tds.fonctions.size();j++){
                if(this.tds.getProcFonc("Else block "+j)!=null){
                    i++;
                }
            }
            ProcFonc Else = new ProcFonc("Else block "+k, "Else",new ArrayList<VarType>());
            Else.setUsed();
            this.addProcFonc(Else);
            this.addFils("Else block "+k);
            affect.right.accept(this);
            this.closeFils();
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
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole différent",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole différent",affect.right);
        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Inf affect) {
        String nodeIdentifier = this.nextState();
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole inférieur",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole inférieur",affect.right);
        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Sup affect) {
        String nodeIdentifier = this.nextState();
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole supérieur",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole supérieur",affect.right);
        affect.left.accept(this);
        affect.right.accept(this);

        return nodeIdentifier;
    }

    @Override
    public String visit(Infeg affect) {
        String nodeIdentifier = this.nextState();
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole inférieur ou égal",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole inférier égal",affect.right);
        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Supeg affect) {
        String nodeIdentifier = this.nextState();
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole supérieur ou égal ",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole supérieur ou égal",affect.right);
        affect.left.accept(this);
        affect.right.accept(this);


        return nodeIdentifier;
    }

    @Override
    public String visit(Plus affect) {
        String nodeIdentifier = this.nextState();
        //SimplificationCalcul.warningSimplification(affect);
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

        //Controle semantique
        Division.warningDivision( this.tdsStack, this.tds,affect.right);


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
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole égal",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole égal",affect.right);
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
        tds.setUsed(tdsStack,((Typeidid)affect.typeid).id, "Type");
        affect.expr2.accept(this);
        return nodeIdentifier;
    }

    

    @Override
    public String visit(Typeswithfieldlist affect) {
        String nodeIdentifier = this.nextState();
        affect.typeid.accept(this);
        tds.setUsed(tdsStack,((Typeidid)affect.typeid).id, "Type");
        Table tableActuelle = new Table(this.tds.getId());
        tableActuelle=tableActuelle.joinTDS(tdsStack);
        if(affect.fieldlist != null && Declaration.checkVardeclared(((Typeidid)affect.typeid).id,this.tdsStack,this.tds)){
            Expression.checktypefield((Fieldlist)affect.fieldlist, tableActuelle.getVarType(((Typeidid)affect.typeid).id).getIdentifiant(), this.tdsStack, this.tds);   
        }
        if(affect.fieldlist != null){
            affect.fieldlist.accept(this);
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Idcall2 affect) {

        tds.setUsed(this.tdsStack, affect.id, "Var");

        String nodeIdentifier = this.nextState();
        if (tailledec){
            tailletype+=affect.id;
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(Dptegal affect) {
        String nodeIdentifier = this.nextState();

        Expression.checktypeDptEgal(affect, this.tds, this.tdsStack);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote gauche du symbole :=",affect.left);
        SimplificationCalcul.warningSimplification(this.tds.nom,"cote droit du symbole :=",affect.right);
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
        tds.setUsed(this.tdsStack, affect.id, "Var");
        String nodeIdentifier = this.nextState();
        Declaration.checkVardeclared(affect.id, this.tdsStack, this.tds);
        AccesListe.warningAccesListe(affect,this.tdsStack, this.tds);
        if (tailledec){
            tailletype=affect.id;
        }
        

        if (affect.right != null){
            affect.right.accept(this);

        }
        return nodeIdentifier;

    }
}