package ast;

public class Typegal implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public String dp;
    public Ast typeid;
    public String eg;
    public Ast expr;

    public Typegal(String dp, Ast typeid, String eg, Ast expr){
        this.dp = dp;
        this.typeid = typeid;
        this.eg = eg;
        this.expr = expr;
    }

}
