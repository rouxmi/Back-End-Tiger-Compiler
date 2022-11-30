package ast;

public class Typefield implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String id;
    public Ast type;

    public Typefield(String id, Ast type){
        this.id = id;
        this.type = type;
    }
}

