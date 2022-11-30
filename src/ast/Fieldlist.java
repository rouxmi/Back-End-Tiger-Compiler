package ast;

public class Fieldlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast field;
    public Ast fieldlistbis;

    public Fieldlist(Ast field, Ast fieldlistbis){
        this.field = field;
        this.fieldlistbis = fieldlistbis;
    }

}