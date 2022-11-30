package ast;

public class Egal implements Ast{

    // Utile 
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;


    public Egal(Ast left, Ast right) {
        this.left = left;
        this.right = right;
    }
    



}
