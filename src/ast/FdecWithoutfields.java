package ast;

public class FdecWithoutfields implements Ast{
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
   public Ast functiondeclaration;
   public int ligne;

   public int colonne;

    public FdecWithoutfields(String id, Ast functiondeclaration, int ligne, int colonne){
        this.id = id;
        this.functiondeclaration = functiondeclaration;
        this.ligne = ligne;
        this.colonne = colonne;

    }

}