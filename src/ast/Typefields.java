package ast;

import java.util.ArrayList;

public class Typefields implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public ArrayList<Ast> typefield;



    public Typefields(ArrayList<Ast> list) {
        this.typefield = list;
    }
}

