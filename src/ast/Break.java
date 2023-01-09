package ast;

public class Break implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    public String coup;
    public int ligne;

    public int colonne;

    public Break(String coup, int ligne, int colonne){
        this.coup=coup;
        this.ligne=ligne;
        this.colonne=colonne;
    }

}
