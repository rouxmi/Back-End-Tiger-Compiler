package ast;

public class Expr2 implements Ast{

    // Utile
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;


    public Expr2(Ast left, Ast right) {
        this.left = left;
        this.right = right;
    }

}
