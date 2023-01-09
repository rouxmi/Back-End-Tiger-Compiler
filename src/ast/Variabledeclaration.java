package ast;

public class Variabledeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public String id;
    public Ast bis;
    public int ligne;

    public int colonne;
    


    public Variabledeclaration(String id, Ast bis, int ligne, int colonne){
        this.id = id;
        this.bis = bis;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}