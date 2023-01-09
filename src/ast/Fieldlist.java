package ast;

import java.util.ArrayList;

public class Fieldlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }
    
    public ArrayList<Ast> field;
    public int ligne;

    public int colonne;

    public Fieldlist(ArrayList<Ast> field, int ligne, int colonne){
        this.field = field;
        this.ligne = ligne;
        this.colonne = colonne;
    }

}