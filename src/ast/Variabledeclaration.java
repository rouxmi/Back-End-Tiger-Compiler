package ast;

public class Variabledeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast id;
    public Ast bis;
    


    public Variabledeclaration(Ast id, Ast bis){
        this.id = id;
        this.bis = bis;
    }
}