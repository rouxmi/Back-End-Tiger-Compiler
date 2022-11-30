package ast;

public class FdecWithoutfields implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
   public String id;
   public Ast functiondeclaration;

    public FdecWithoutfields(String id, Ast functiondeclaration){
         this.id = id;
         this.functiondeclaration = functiondeclaration;
    }

}