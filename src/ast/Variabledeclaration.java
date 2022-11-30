package ast;

public class Variabledeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String id;
    public Ast bis;
    


    public Variabledeclaration(String id, Ast bis){
        this.id = id;
        this.bis = bis;
    }
}