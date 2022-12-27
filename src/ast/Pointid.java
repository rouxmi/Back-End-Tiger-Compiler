package ast;

public class Pointid implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String id;
    public Ast fils;

    public Pointid (String id, Ast fils){
        this.id = id;
        this.fils = fils;
    }
}