package ast;

import parser.ParsertigerBaseVisitor;

import org.antlr.runtime.tree.ParseTree;

import parser.Parsertiger;

public class AstCreator extends ParsertigerBaseVisitor<Ast>{

    	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitProgram(Parsertiger.ProgramContext ctx) { 

		Ast expr = ctx.getChild(0).accept(this);

		return new Program(expr);
		
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr0(Parsertiger.Expr0Context ctx) { 
		
		Ast noeudTemporaire = ctx.getChild(0).accept(this);


        for (int i=0;2*i<ctx.getChildCount()-1;i++){
            
            
            Ast right = ctx.getChild(2*(i+1)).accept(this);

            
            noeudTemporaire = new Expr0(noeudTemporaire,right);

            }

        return noeudTemporaire;

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr1(Parsertiger.Expr1Context ctx) { 
		
		Ast noeudTemporaire = ctx.getChild(0).accept(this);


        for (int i=0;2*i<ctx.getChildCount()-1;i++){
            
            
            Ast right = ctx.getChild(2*(i+1)).accept(this);

            
            noeudTemporaire = new Expr1(noeudTemporaire,right);

            }

        return noeudTemporaire;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr2(Parsertiger.Expr2Context ctx) { 
		
		Ast noeudTemporaire = ctx.getChild(0).accept(this);


        for (int i=0;2*i<ctx.getChildCount()-1;i++){
            
            String operation = ctx.getChild(2*i+1).toString();
            Ast right = ctx.getChild(2*(i+1)).accept(this);

            switch (operation) {
                case "=":
                    noeudTemporaire = new Egal(noeudTemporaire,right);
                    break;
                case "!=":
                    noeudTemporaire = new Dif(noeudTemporaire,right);
                    break;
				case "<":
                    noeudTemporaire = new Inf(noeudTemporaire,right);
                    break;
				case ">":
                    noeudTemporaire = new Sup(noeudTemporaire,right);
                    break;
				case "<=":
                    noeudTemporaire = new Infeg(noeudTemporaire,right);
                    break;
				case ">=":
                    noeudTemporaire = new Supeg(noeudTemporaire,right);
                    break;
                default:
                    break;
            }
            }

        return noeudTemporaire;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr3(Parsertiger.Expr3Context ctx) { 
		Ast noeudTemporaire = ctx.getChild(0).accept(this);


        for (int i=0;2*i<ctx.getChildCount()-1;i++){
            
            String operation = ctx.getChild(2*i+1).toString();
            Ast right = ctx.getChild(2*(i+1)).accept(this);

            switch (operation) {
                case "-":
                    noeudTemporaire = new Minus(noeudTemporaire,right);
                    break;
                case "+":
                    noeudTemporaire = new Plus(noeudTemporaire,right);
                    break;
                default:
                    break;
            }


        }    

        return noeudTemporaire;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr4(Parsertiger.Expr4Context ctx) { 

		Ast noeudTemporaire = ctx.getChild(0).accept(this);


        for (int i=0;2*i<ctx.getChildCount()-1;i++){
            
            String operation = ctx.getChild(2*i+1).toString();
            Ast right = ctx.getChild(2*(i+1)).accept(this);

            switch (operation) {
                case "*":
                    noeudTemporaire = new Minus(noeudTemporaire,right);
                    break;
                case "/":
                    noeudTemporaire = new Plus(noeudTemporaire,right);
                    break;
                default:
                    break;
            }


        }    

        return noeudTemporaire;

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIdcal(Parsertiger.IdcalContext ctx) { return ctx.getChild(0).accept(this);
		
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitStrin(Parsertiger.StrinContext ctx) { 
	
		return new Strin(ctx.getChild(0).toString());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIn(Parsertiger.InContext ctx) { 
		return new In(Integer.parseInt(ctx.getChild(0).toString()));
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNil(Parsertiger.NilContext ctx) { 
		return new Nil(ctx.getChild(0).toString());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNeg(Parsertiger.NegContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypDec(Parsertiger.TypDecContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprSeq(Parsertiger.ExprSeqContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprC(Parsertiger.ExprCContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIfThen(Parsertiger.IfThenContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitWhile(Parsertiger.WhileContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFor(Parsertiger.ForContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitBreak(Parsertiger.BreakContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitLet(Parsertiger.LetContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPrt(Parsertiger.PrtContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTyp(Parsertiger.TypContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPrint(Parsertiger.PrintContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitElse(Parsertiger.ElseContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVide(Parsertiger.VideContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypeDec(Parsertiger.TypeDecContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVarDec(Parsertiger.VarDecContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFuncDec(Parsertiger.FuncDecContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFunctio(Parsertiger.FunctioContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFunctiondeclaration(Parsertiger.FunctiondeclarationContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitEgal(Parsertiger.EgalContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypegal(Parsertiger.TypegalContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprnegation(Parsertiger.ExprnegationContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprseq(Parsertiger.ExprseqContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPointexpr(Parsertiger.PointexprContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNul1(Parsertiger.Nul1Context ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprlist(Parsertiger.ExprlistContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprlisbis(Parsertiger.ExprlisbisContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNul2(Parsertiger.Nul2Context ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFieldlist(Parsertiger.FieldlistContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitField(Parsertiger.FieldContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNul3(Parsertiger.Nul3Context ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFieldlisbis(Parsertiger.FieldlisbisContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIdcall(Parsertiger.IdcallContext ctx) {
		 Ast id= ctx.getChild(0).accept(this);
		 String id1= id.toString();
		 Ast fils= ctx.getChild(1).accept(this);
		 return new Idcall(id1, fils);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPointid(Parsertiger.PointidContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitCroexpr(Parsertiger.CroexprContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNul4(Parsertiger.Nul4Context ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypes(Parsertiger.TypesContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypedeclaration(Parsertiger.TypedeclarationContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypetypeid(Parsertiger.TypetypeidContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypecro(Parsertiger.TypecroContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitArrof(Parsertiger.ArrofContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypefields(Parsertiger.TypefieldsContext ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVirgtypefield(Parsertiger.VirgtypefieldContext ctx) {
		return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitNul5(Parsertiger.Nul5Context ctx) { return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypefield(Parsertiger.TypefieldContext ctx) { 
		String id =  ctx.getChild(0).toString();
		Ast typeid = ctx.getChild(2).accept(this);
		return new Typefield(id, typeid);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypepredefined(Parsertiger.TypepredefinedContext ctx) { 
		String type =  ctx.getChild(0).toString();
		return new Typepredefined(type);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypeidid(Parsertiger.TypeididContext ctx) { 
		String id =  ctx.getChild(0).toString();
		return new Typeidid(id);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPredefined(Parsertiger.PredefinedContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVariabledeclaration(Parsertiger.VariabledeclarationContext ctx) { 
		Ast id = ctx.getChild(1).accept(this);
		Ast bis = ctx.getChild(3).accept(this);
		return new Variabledeclaration(id,bis);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVardec1(Parsertiger.Vardec1Context ctx) { 
		Ast dp = ctx.getChild(0).accept(this);
		Ast typeid = ctx.getChild(1).accept(this);
		Ast expr = ctx.getChild(3).accept(this);
		return new Vardec1(dp, typeid, expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVardec2(Parsertiger.Vardec2Context ctx) { 
		Ast expr = ctx.getChild(1).accept(this);
		return new Vardec2(expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
}
