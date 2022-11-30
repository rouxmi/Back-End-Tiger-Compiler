package ast;

public class Typeswithfieldlist implements Ast{

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast typeid;
    public Ast fieldlist;

    public Typeswithfieldlist(Ast typeid, Ast fieldlist){
        this.typeid = typeid;
        this.fieldlist = fieldlist;
    }
   
}
