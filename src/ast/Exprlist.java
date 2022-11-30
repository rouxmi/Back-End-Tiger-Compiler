package ast;

public class Exprlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast expr;
    public Ast exprlistbis;

    public Exprlist(Ast expr, Ast exprlistbis){
        this.expr = expr;
        this.exprlistbis = exprlistbis;
    }

}