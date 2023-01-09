package ast;
import java.util.ArrayList;

public class Let implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public ArrayList<Ast> lefts;
    public Ast right;
    public int ligne;

    public int colonne;
    
    public Let(Ast right, int ligne, int colonne){
        this.lefts= new ArrayList<>();
        this.right=right;
        this.ligne=ligne;
        this.colonne=colonne;
    }

    public void add_Ast(Ast left){
        this.lefts.add(left);
    }
}
