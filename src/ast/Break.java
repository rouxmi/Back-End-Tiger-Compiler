package ast;

public class Break implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public String coup;

    public Break(String coup){
        this.coup=coup;
    }

}
