package ast;

public class Expr0 implements Ast{

    // Utile
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;


    public Expr0(Ast left, Ast right) {
        this.left = left;
        this.right = right;
    }

}
