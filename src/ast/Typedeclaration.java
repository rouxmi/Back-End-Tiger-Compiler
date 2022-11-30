package ast;

public class Typedeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast type;
    public Ast typeid;

    public Typedeclaration (Ast typeid, Ast type){
        this.typeid = typeid;
        this.type = type;
    }
}

