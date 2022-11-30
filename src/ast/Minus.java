package ast;

public class Minus implements Ast{

    // Utile 
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;


    public Minus(Ast left, Ast right) {
        this.left = left;
        this.right = right;
    }

}