package ast;

public class AccesVar implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast right;
    public String id;
    public Ast left;

    public AccesVar(String id,Ast right){
        this.id=id;
        this.right= right;
        this.left=null;
    }

    public AccesVar(Ast left, Ast right){
        this.left=left;
        this.right=right;
        this.id=null;
    }
}
