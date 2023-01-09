package ast;

public class Field implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public Ast expr;
    public String id;
    public int ligne;

    public int colonne;

    public Field(Ast expr, String id, int ligne, int colonne){
        this.expr = expr;
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}