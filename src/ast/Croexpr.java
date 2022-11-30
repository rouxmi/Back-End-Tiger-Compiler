package ast;

public class Croexpr implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast expr;
    public Ast lvaluebis;

    public Croexpr (Ast expr, Ast lvaluebis){
        this.expr = expr;
        this.lvaluebis = lvaluebis;
    }
}