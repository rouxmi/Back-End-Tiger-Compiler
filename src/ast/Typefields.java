package ast;

public class Typefields implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast typefield;
    public Ast typefieldsbis;


    public Typefields(Ast typefield, Ast typefieldsbis){
        this.typefield = typefield;
        this.typefieldsbis = typefieldsbis;
    }
}

