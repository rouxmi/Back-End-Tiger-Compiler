package ast;

public class In implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public int in;

    public In(int in){
        this.in=in;
    }

}
