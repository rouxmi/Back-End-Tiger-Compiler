package ast;

public class Print implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String print;
    public Ast fils;

    public Print(String print, Ast fils){
        this.print=print;
        this.fils=fils;
    }
}
