package ast;

public class Typegal implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public Ast typeid;
    public String eg;
    public Ast expr;
    public int ligne;

    public int colonne;

    public Typegal( Ast typeid, String eg, Ast expr, int ligne, int colonne){
        this.typeid = typeid;
        this.eg = eg;
        this.expr = expr;
        this.ligne = ligne;
        this.colonne = colonne;
    }

}
