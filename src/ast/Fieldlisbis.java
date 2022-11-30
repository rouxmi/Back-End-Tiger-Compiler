package ast;

public class Fieldlisbis implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast field;
    public Ast fieldlisbis;

    public Fieldlisbis(Ast field, Ast fieldlisbis){
        this.field = field;
        this.fieldlisbis = fieldlisbis;
    }
}