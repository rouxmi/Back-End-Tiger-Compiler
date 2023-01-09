package ast;

public class Exprnegation implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public String neg;
    public Ast expr;
    public int ligne;

    public int colonne;

    public Exprnegation(String neg, Ast expr, int ligne, int colonne){
        this.neg = neg;
        this.expr = expr;
        this.ligne = ligne;
        this.colonne = colonne;
    }

}
