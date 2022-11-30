package ast;

public class Typeswithof implements Ast{

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String of;
    public Ast typeid;
    public Ast expr1;
    public Ast expr2;

    public Typeswithof(String of, Ast typeid, Ast expr1, Ast expr2){
        this.of = of;
        this.typeid = typeid;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
}

