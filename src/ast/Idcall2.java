package ast;

public class Idcall2 implements Ast{
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
    public int ligne;

    public int colonne;

    public Idcall2(String id, int ligne, int colonne){
        this.id=id;
        this.ligne=ligne;
        this.colonne=colonne;
    }
}