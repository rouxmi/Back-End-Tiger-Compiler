package ast;

public class For implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public String id;
    public Ast min;
    public Ast max;
    public Ast regle;

    public For(String id, Ast min, Ast max, Ast regle){
        this.id=id;
        this.min=min;
        this.max=max;
        this.regle=regle;
    }
}
