package ast;

public class Appelfunc implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast right;
    public String id;

    public Appelfunc(String id,Ast right){
        this.id=id;
        this.right= right;
        if (right == null){
            System.out.print("oui\n");
        }
        
    }
}
