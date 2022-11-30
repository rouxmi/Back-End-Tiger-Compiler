package ast;

public class Vardec2 implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;


    public Vardec2(Ast expr) {
        this.expr = expr;
    }
}