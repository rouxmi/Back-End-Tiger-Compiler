package ast;

public class In implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public int in;
    public int ligne;

    public int colonne;

    public In(int in, int ligne, int colonne){
        this.in=in;
        this.ligne=ligne;
        this.colonne=colonne;
    }

}
