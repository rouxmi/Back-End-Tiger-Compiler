package ast;

import java.util.ArrayList;

public class Fieldlist implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public ArrayList<Ast> field;

    public Fieldlist(ArrayList<Ast> field){
        this.field = field;
    }

}