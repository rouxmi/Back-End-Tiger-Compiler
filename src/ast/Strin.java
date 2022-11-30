package ast;

public class Strin  implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public String strin;

    public Strin(String strin){
        this.strin=strin.replace(String.valueOf('"'),String.valueOf("\\"+'"'));
    }
}
