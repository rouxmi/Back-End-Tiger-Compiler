package ast;

public interface Ast {

    public int getLigne();

    public int getColonne();

    public <T> T accept(AstVisitor<T> visitor);

    public String toString();
    
}