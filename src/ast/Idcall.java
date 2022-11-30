package ast;

public class Idcall implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast right;
    public String id;

    public Idcall(String id,Ast right){
        this.id=id;
        this.right= right;
    }
}
