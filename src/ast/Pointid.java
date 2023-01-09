package ast;

public class Pointid implements Ast{
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
    public String fils;
    public Ast left;
    public int ligne;

    public int colonne;

    public Pointid (String id, String fils, int ligne, int colonne){
        this.id = id;
        this.fils = fils;
        this.left = null;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public Pointid (Ast left, String fils, int ligne, int colonne){
        this.left = left;
        this.fils = fils;
        this.id = null;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}