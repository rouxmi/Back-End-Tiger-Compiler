package ast;

public class Arrof  implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Arrof(Ast type) {
        this.type = type;
    }
        
    public Ast type;
    
        
}
    

