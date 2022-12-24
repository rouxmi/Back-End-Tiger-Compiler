package ast;

import java.util.ArrayList;

public class Exprlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public ArrayList<Ast> expr;

    public Exprlist(ArrayList<Ast> expr){
        this.expr = expr;
    }

}
