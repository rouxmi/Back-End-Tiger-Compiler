package ast;

public class IfThen implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast center;
    public Ast right;
    
    public IfThen(Ast left, Ast center, Ast right){
        this.left=left;
        this.center=center;
        this.right = right;
    }
}