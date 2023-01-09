package ast;

public class Typeidid  implements Ast{
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

    public Typeidid(String id, int ligne, int colonne){
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}

