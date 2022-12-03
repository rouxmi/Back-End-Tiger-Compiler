package ast;

import parser.ParsertigerBaseVisitor;


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
                    noeudTemporaire = new Egal2(noeudTemporaire,right);
                    break;
                case "<>":
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
				case ":=":
					noeudTemporaire = new Dptegal(noeudTemporaire, right);
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
	@Override public Ast visitNeg(Parsertiger.NegContext ctx) { 
		return ctx.getChild(0).accept(this);
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
	@Override public Ast visitExprSeq(Parsertiger.ExprSeqContext ctx) { 
		int nbrfils =ctx.getChildCount();
		if(nbrfils==3){
			return ctx.getChild(1).accept(this);
		}
		else{
			return null;
		}	
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprC(Parsertiger.ExprCContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIfThen(Parsertiger.IfThenContext ctx) { 
		Ast left= ctx.getChild(1).accept(this);
		Ast center= ctx.getChild(3).accept(this);
		Ast right= ctx.getChild(4).accept(this);
		return new IfThen(left, center, right);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitWhile(Parsertiger.WhileContext ctx) { 
		Ast left= ctx.getChild(1).accept(this);
		Ast right= ctx.getChild(3).accept(this);
		return new While(left, right);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFor(Parsertiger.ForContext ctx) { 
		String id = ctx.getChild(1).getText();
		Ast min =ctx.getChild(3).accept(this);
		Ast max= ctx.getChild(5).accept(this);
		Ast regle =ctx.getChild(7).accept(this);
		return new For(id, min, max, regle);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitBreak(Parsertiger.BreakContext ctx) { 
		
		return new Break(ctx.getChild(0).getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitLet(Parsertiger.LetContext ctx) { 
		Ast var=null;
		int valeurin=0;
		for (int i=0;i<ctx.getChildCount();i++){
			if(ctx.getChild(i).getText().equals("in")){
				valeurin=i;
			}
		}

		if(ctx.getChildCount()-1-valeurin==2){
			var= ctx.getChild(valeurin+1).accept(this);

		}
		Let lefts = new Let(var);
		for(int i=0;i<valeurin;i++){
			lefts.add_Ast(ctx.getChild(i).accept(this));
		}
		return lefts;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPrt(Parsertiger.PrtContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTyp(Parsertiger.TypContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPrint(Parsertiger.PrintContext ctx) {

		Ast fils = ctx.getChild(2).accept(this);
		return new Print(fils);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitElse(Parsertiger.ElseContext ctx) { 
		return ctx.getChild(1).accept(this);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypeDec(Parsertiger.TypeDecContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVarDec(Parsertiger.VarDecContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFuncDec(Parsertiger.FuncDecContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFunctio(Parsertiger.FunctioContext ctx) {
		int cmp= ctx.getChildCount();
		if(cmp==3){
			return ctx.getChild(1).accept(this);
		}else return null;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFunctiondeclaration(Parsertiger.FunctiondeclarationContext ctx) { 
		String id = ctx.getChild(1).getText();
		int cmp = ctx.getChildCount();
		if(cmp==6){
			Ast typefields = ctx.getChild(3).accept(this);
			Ast functiondeclaration = ctx.getChild(5).accept(this);
			return new Functiondeclaration(id, typefields, functiondeclaration);
		}
		else{
			Ast functiondeclaration = ctx.getChild(4).accept(this);
			return new FdecWithoutfields(id, functiondeclaration);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitEgal(Parsertiger.EgalContext ctx) { 
		String eg = ctx.getChild(0).getText();
		Ast expr = ctx.getChild(1).accept(this);
		return new Egal(eg, expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypegal(Parsertiger.TypegalContext ctx) {
		Ast typeid = ctx.getChild(1).accept(this);
		String eg = ctx.getChild(2).getText();
		Ast expr = ctx.getChild(3).accept(this);
		return new Typegal(typeid, eg, expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprnegation(Parsertiger.ExprnegationContext ctx) { 
		String neg = ctx.getChild(0).getText();
		Ast expr = ctx.getChild(1).accept(this);
		return new Exprnegation(neg, expr);
		
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprseq(Parsertiger.ExprseqContext ctx) { 
		if (ctx.getChild(1).getChild(0)!=null){
			Ast expr = ctx.getChild(0).accept(this);
			Ast exprseqbis = ctx.getChild(1).accept(this);
			return new Exprseq(expr, exprseqbis);
		} else return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPointexpr(Parsertiger.PointexprContext ctx) { 
		return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprlist(Parsertiger.ExprlistContext ctx) { 
		if (ctx.getChild(1).getChild(0)!=null){
			Ast expr = ctx.getChild(0).accept(this);
			Ast exprlistbis = ctx.getChild(1).accept(this);
			return new Exprlist(expr, exprlistbis);
		}
		else return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExprlisbis(Parsertiger.ExprlisbisContext ctx) { 
		if (ctx.getChild(2).getChild(0)!=null){
			Ast expr= ctx.getChild(1).accept(this);
			Ast exprlisbis = ctx.getChild(2).accept(this);
			return new Exprlisbis(expr, exprlisbis);
		}
		else{return ctx.getChild(2).accept(this);}

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFieldlist(Parsertiger.FieldlistContext ctx) { 
		if (ctx.getChild(3).getChild(0)!=null){
			Ast field = ctx.getChild(0).accept(this);
			Ast fieldlistbis = ctx.getChild(1).accept(this);
			return new Fieldlist(field, fieldlistbis);
		}
		else return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitField(Parsertiger.FieldContext ctx) { 
		String id = ctx.getChild(0).getText();
		Ast expr = ctx.getChild(2).accept(this);
		return new Field(expr, id);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFieldlisbis(Parsertiger.FieldlisbisContext ctx) { 
		if (ctx.getChild(2).getChild(0)!=null){
			Ast field = ctx.getChild(1).accept(this);
			Ast fieldlisbis = ctx.getChild(2).accept(this);
			return new Fieldlisbis(field, fieldlisbis);
		}
		else{return ctx.getChild(2).accept(this);}
		
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitIdcall(Parsertiger.IdcallContext ctx) {
		String id= ctx.getChild(0).getText();
		if(ctx.getChild(1).getChild(0)!=null){
			Ast fils= ctx.getChild(1).accept(this);
			return new Idcall(id, fils);
		}
		else {
			return new Idcall2(id);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPointid(Parsertiger.PointidContext ctx) { 
		String id = ctx.getChild(1).getText();
		Ast lvaluebis = ctx.getChild(2).accept(this);
		return new Pointid(id, lvaluebis);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */ 
	@Override public Ast visitCroexpr(Parsertiger.CroexprContext ctx) { 	
		if (ctx.getChild(3).getChild(0)!=null){
			Ast expr= ctx.getChild(1).accept(this);
			Ast lvaluebis = ctx.getChild(3).accept(this);
			return new Croexpr(expr, lvaluebis);
		}
		else {
			return ctx.getChild(1).accept(this);}
		
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypes(Parsertiger.TypesContext ctx) { 
		int cmp = ctx.getChildCount();
		Ast typeid = ctx.getChild(0).accept(this);
		if(cmp==6){
			Ast expr1 = ctx.getChild(2).accept(this);
			String of = ctx.getChild(4).getText();
			Ast expr2 = ctx.getChild(5).accept(this);
			return new Typeswithof(of,typeid, expr1, expr2);
		}else if (cmp==4){
			Ast fieldlist = ctx.getChild(2).accept(this);
			return new Typeswithfieldlist(typeid, fieldlist);
		}else{
			return typeid;
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypedeclaration(Parsertiger.TypedeclarationContext ctx) { 
		Ast typeid = ctx.getChild(1).accept(this);
		Ast type = ctx.getChild(3).accept(this);
		return new Typedeclaration(typeid, type);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypetypeid(Parsertiger.TypetypeidContext ctx) { 
		return ctx.getChild(0).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypecro(Parsertiger.TypecroContext ctx) { 
		int cmp= ctx.getChildCount();
		if(cmp==3){
			return ctx.getChild(1).accept(this);
		}
		else{
			return null;
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitArrof(Parsertiger.ArrofContext ctx) { 
		return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitTypefields(Parsertiger.TypefieldsContext ctx) { 
		if (ctx.getChild(1).getChild(0)!=null){
			Ast typefield = ctx.getChild(0).accept(this);
			Ast typefieldsbis = ctx.getChild(1).accept(this);
			return new Typefields(typefield, typefieldsbis);
		}else return ctx.getChild(0).accept(this);
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
		String id = ctx.getChild(1).toString();
		Ast bis = ctx.getChild(2).accept(this);
		return new Variabledeclaration(id,bis);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVardec1(Parsertiger.Vardec1Context ctx) { 
		Ast typeid = ctx.getChild(1).accept(this);
		Ast expr = ctx.getChild(3).accept(this);
		return new Vardec1(typeid, expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitVardec2(Parsertiger.Vardec2Context ctx) { 
		return ctx.getChild(1).accept(this);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
}
