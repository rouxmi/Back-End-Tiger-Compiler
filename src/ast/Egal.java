package ast;

public class Egal implements Ast{

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String eg;
    public Ast expr;

    public Egal(String eg, Ast expr){
        this.eg = eg;
        this.expr = expr;
    }
}