package ast;

public class Nil implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public String ni;
    public int ligne;

    public int colonne;

    public Nil(String ni, int ligne, int colonne) {
        this.ni=ni;
        this.ligne=ligne;
        this.colonne=colonne;
    }

}
