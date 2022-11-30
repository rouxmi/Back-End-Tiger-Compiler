package ast;

public class Vardec1 implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;
    public Ast typeid;
    public Ast dp;


    public Vardec1(Ast dp, Ast typeid, Ast expr) {
        this.expr = expr;
        this.typeid = typeid;
        this.dp = dp;
    }
}
