package ast;

public class Idcall2 implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String id;

    public Idcall2(String id){
        this.id=id;
    }
}