package ast;

public class AccesVar implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast right;
    public String id;

    public AccesVar(String id,Ast right){
        this.id=id;
        this.right= right;
    }
}
