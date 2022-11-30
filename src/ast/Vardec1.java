package ast;

public class Vardec1 implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;
    public Ast typeid;

    public Vardec1(Ast typeid, Ast expr) {
        this.expr = expr;
        this.typeid = typeid;
    }
}
