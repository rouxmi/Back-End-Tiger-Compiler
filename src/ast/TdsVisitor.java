package ast;


import java.io.FileOutputStream;
import java.io.IOException;
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

public class TdsVisitor implements AstVisitor<String> {
    private Stack<Table> tdsStack = new Stack<Table>();
    private Table tds;
    public TdsVisitor() {
        tds = new Table(-1);
        tdsStack.push(tds);
    }

    public void afficher() {
        tds.afficher();
    }
    
    public void addFils() {
        Table tdsFils = new Table(tds.getId());
        tdsStack.push(tdsFils);
        tds.addFils(tdsFils);
        tds = tdsFils;
    }

    public void closeFils() {
        tdsStack.pop();
        tds = tdsStack.peek();
    }

    public void addVarType(VarType varType) {
        tds.addVarType(varType);
    }

}