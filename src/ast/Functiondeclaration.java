package ast;

public class Functiondeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
   public String id;
   public Ast typefields;
   public Ast functiondeclaration;

    public Functiondeclaration(String id, Ast typefields, Ast functiondeclaration){
        this.id = id;
        this.typefields = typefields;
        this.functiondeclaration = functiondeclaration;
    }
}
