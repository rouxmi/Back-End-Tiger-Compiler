package ast;

public class Arrof  implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public Arrof(Ast type, int ligne, int colonne) {
        this.type = type;
        this.ligne = ligne;
        this.colonne = colonne;
    }
        
    public Ast type;
    public int ligne;

    public int colonne;
    
        
}
    

