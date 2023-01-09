package ast;

public class Typedeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public Ast type;
    public Ast typeid;
    public int ligne;

    public int colonne;

    public Typedeclaration (Ast typeid, Ast type, int ligne, int colonne){
        this.typeid = typeid;
        this.type = type;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}

