package ast;

import java.util.ArrayList;

public class Exprlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public ArrayList<Ast> expr;
    public int ligne;

    public int colonne;

    public Exprlist(ArrayList<Ast> expr, int ligne, int colonne){
        this.expr = expr;
        this.ligne = ligne;
        this.colonne = colonne;
    }

}
