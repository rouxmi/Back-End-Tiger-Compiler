package ast;

public class Print implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public Ast fils;
    public int ligne;

    public int colonne;

    public Print( Ast fils, int ligne, int colonne){
        this.fils=fils;
        this.ligne=ligne;
        this.colonne=colonne;
    }
}
