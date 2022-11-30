package ast;

public class Exprnegation implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String neg;
    public Ast expr;

    public Exprnegation(String neg, Ast expr){
        this.neg = neg;
        this.expr = expr;
    }

}
