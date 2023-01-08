package ast;

public class Pointid implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String id;
    public String fils;
    public Ast left;

    public Pointid (String id, String fils){
        this.id = id;
        this.fils = fils;
        this.left = null;
    }

    public Pointid (Ast left, String fils){
        this.left = left;
        this.fils = fils;
        this.id = null;
    }
}