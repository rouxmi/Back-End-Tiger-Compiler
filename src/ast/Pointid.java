package ast;

public class Pointid implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String id;
    public Ast lvaluebis;

    public Pointid (String id, Ast lvaluebis){
        this.id = id;
        this.lvaluebis = lvaluebis;
    }
}