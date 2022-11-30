package ast;

public class Field implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast expr;
    public String id;

    public Field(Ast expr, String id){
        this.expr = expr;
        this.id = id;
    }
}