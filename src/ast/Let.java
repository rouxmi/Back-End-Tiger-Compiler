package ast;
import java.util.ArrayList;

public class Let implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public ArrayList<Ast> lefts;
    public Ast right;
    
    public Let(Ast right){
        this.lefts= new ArrayList<>();
        this.right=right;
    }

    public void add_Ast(Ast left){
        this.lefts.add(left);
    }
}
