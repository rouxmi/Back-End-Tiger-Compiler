package ast;

public class Dptegal implements Ast{

    // Utile 
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;


    public Dptegal(Ast left, Ast right) {
        this.left = left;
        this.right = right;
    }
    



}
