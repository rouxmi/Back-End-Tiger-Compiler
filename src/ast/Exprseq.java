package ast;

public class Exprseq implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast expr;
    public Ast exprseqbis;

    public Exprseq(Ast expr, Ast exprseqbis){
        this.expr = expr;
        this.exprseqbis = exprseqbis;
    }

}
