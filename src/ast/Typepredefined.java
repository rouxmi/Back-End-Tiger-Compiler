package ast;

public class Typepredefined  implements Ast{

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public String type;

    public Typepredefined(String type){
        this.type = type;
    }
}

