package ast;

import java.util.ArrayList;

public class Typefields implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public ArrayList<Ast> typefield;
    public int ligne;

    public int colonne;



    public Typefields(ArrayList<Ast> list, int ligne, int colonne){
        this.typefield = list;
        this.ligne = ligne;
        this.colonne = colonne;
    }
}

