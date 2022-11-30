package ast;

public class Typeidid  implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String id;

    public Typeidid(String id){
        this.id = id;
    }
}

