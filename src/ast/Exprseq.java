package ast;

import java.util.ArrayList;

public class Exprseq implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public ArrayList<Ast> expr;

    public Exprseq(ArrayList<Ast> expr){
        this.expr = expr;
    }

}
