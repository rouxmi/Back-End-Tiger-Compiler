package ast;

public class Typegal implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast typeid;
    public String eg;
    public Ast expr;

    public Typegal( Ast typeid, String eg, Ast expr){
        this.typeid = typeid;
        this.eg = eg;
        this.expr = expr;
    }

}
