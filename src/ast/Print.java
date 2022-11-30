package ast;

public class Print implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast fils;

    public Print( Ast fils){
        this.fils=fils;
    }
}