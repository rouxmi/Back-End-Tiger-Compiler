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
        if (this.right == null){
            System.out.print(id+"\n");
            System.out.print("oui\n");
        }
        
    }
}
