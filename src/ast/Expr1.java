package ast;

public class Expr1 implements Ast{

    // Utile
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public Ast left;
    public Ast right;
    public int ligne;

    public int colonne;


    public Expr1(Ast left, Ast right, int ligne, int colonne) {
        this.left = left;
        this.right = right;
        this.ligne = ligne;
        this.colonne = colonne;
    }


}
