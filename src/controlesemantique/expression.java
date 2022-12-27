package controlesemantique;


import java.util.Stack;

import ast.*;
import exception.DecException;
import exception.ExprException;
import tds.Table;

public class expression {

    //verifie si les deux expressions sont du type int
    /*
     * param:
     * -tree Ast noeud d'expression
     * -type type de l'expression attendu
     */
    public static boolean checktypeOp(Ast tree,Stack<Table> pile,Table tds) throws Exception{
        String name =tree.getClass().getName().replace('\n', '\0');
        if(name.equals("ast.Mul")){
            if(checktype(((Mul)tree).left,"int",pile,tds) && checktype(((Mul)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Div")){
            if(checktype(((Div)tree).left,"int",pile,tds) && checktype(((Div)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Plus")){
            if(checktype(((Plus)tree).left,"int",pile,tds) && checktype(((Plus)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Minus")){
            if(checktype(((Minus)tree).left,"int",pile,tds) && checktype(((Minus)tree).right,"int",pile,tds)){
                return true;
            }
        }
        throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int");
        
    }
    
    public static boolean checktype(Ast tree, String string,Stack<Table> pile,Table tds) throws Exception {
        if (tree == null) {
            return false;
        }
        String name =tree.getClass().getName().replace('\n', '\0');
        if((name.equals("ast.Egal2") || name.equals("ast.Dif") || name.equals("ast.Expr1") || name.equals("ast.Expr0")) && string.equals("bool")){
            return checktypeEgal(tree,pile,tds);
        }else if(name.equals("ast.Mul") || name.equals("ast.Div") || name.equals("ast.Plus") || name.equals("ast.Minus")){
            return checktypeOp(tree,pile,tds);
        }else if(name.equals("ast.In") && string.equals("int")){
            return true;
        }else if(name.equals("ast.Strin") && string.equals("string")){
            return true;
        }else if((name.equals("ast.Sup") || name.equals("ast.Inf") || name.equals("ast.Supeg") || name.equals("ast.Infeg")) && string.equals("bool")){
            return checkTypeSupInf(tree,pile,tds);
        }else if(name.equals("ast.Not") && string.equals("bool")){
            return checktype((Ast)((Exprnegation)tree).expr,"bool",pile,tds);
        }else if(name.equals("ast.Idcall2")){
            if(!Declaration.checkVardeclared(((Idcall2)tree).id,pile,tds)){
                throw new DecException("Variable non déclarée : "+((Idcall2)tree).id);
            }else{
                Table tdsactuel = new Table(tds.getId());
                tdsactuel=tdsactuel.joinTDS(pile);
                if(string.equals(tdsactuel.getVarType(((Idcall2)tree).id).getType())){
                    return true;
                }
                else{
                    throw new ExprException("Type de la variable "+((Idcall2)tree).id+" incorrect dans l'expression: "+tree.toString()+"(attendu : "+string+")");
                }
            }
        }else if(name.equals("ast.Croexpr") && string.equals("int")){
            return checktype(((Croexpr)tree).expr,"int",pile,tds);
        }else if(name.equals("ast.AccesVar")){
            Table tdsactuel = new Table(tds.getId());
            tdsactuel=tdsactuel.joinTDS(pile);
            String type = tdsactuel.getVarType(((AccesVar)tree).id).getType();
            if(tdsactuel.getVarType(type)!=null){
                type = tdsactuel.getVarType(type).getElementtype().get(0).replace(',',' ').trim();
            }
            if (type.equals(string)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            System.out.println(name);
            throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : "+string+")");
        }
    }

    private static boolean checkTypeSupInf(Ast tree,Stack<Table> pile,Table tds) throws Exception {
        String name =tree.getClass().getName().replace('\n', '\0');
        if(name.equals("ast.Sup")){
            if(checktype(((Sup)tree).left,"int",pile,tds) && checktype(((Sup)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Inf")){
            if(checktype(((Inf)tree).left,"int",pile,tds) && checktype(((Inf)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Supeg")){
            if(checktype(((Supeg)tree).left,"int",pile,tds) && checktype(((Supeg)tree).right,"int",pile,tds)){
                return true;
            }
        }else if(name.equals("ast.Infeg")){
            if(checktype(((Infeg)tree).left,"int",pile,tds) && checktype(((Infeg)tree).right,"int",pile,tds)){
                return true;
            }
        }
        throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int"+")");
    }
        

    //verifie si les deux expressions sont du meme type int ou string
    /*
     * param:
     * -tree Ast noeud d'expression
     * -type type de l'expression attendu
     */
    public static boolean checktypeEgal(Ast tree,Stack<Table> pile,Table tds) throws Exception{
        String name =tree.getClass().getName().replace('\n', '\0');
        if(name.equals("ast.Egal2")){
            if(checktype(((Egal2)tree).left,"int",pile,tds) && checktype(((Egal2)tree).right,"int",pile,tds)){
                return true;
            }
            else if(checktype(((Egal2)tree).left,"string",pile,tds) && checktype(((Egal2)tree).right,"string",pile,tds)){
                return true;
            }
            else if(checktype(((Egal2)tree).left,"bool",pile,tds) && checktype(((Egal2)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int ou string des deux côtés)");
            }
        }else if(name.equals("ast.Dif")){
            if(checktype(((Dif)tree).left,"int",pile,tds) && checktype(((Dif)tree).right,"int",pile,tds)){
                return true;
            }
            else if(checktype(((Dif)tree).left,"string",pile,tds) && checktype(((Dif)tree).right,"string",pile,tds)){
                return true;
            }
            else if(checktype(((Dif)tree).left,"bool",pile,tds) && checktype(((Dif)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int ou string des deux côtés)");
            }
        }else if(name.equals("ast.Expr1")){
            if(checktype(((Expr1)tree).left,"bool",pile,tds) && checktype(((Expr1)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int ou string des deux côtés");
            }
        }else if(name.equals("ast.Expr0")){
            if(checktype(((Expr0)tree).left,"bool",pile,tds) && checktype(((Expr0)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int ou string des deux côtés");
            }
        }
        else{
            throw new ExprException("Type incorrect dans l'expression : "+tree.toString()+"(attendu : int ou string des deux côtés");
        }
    }
    
    
}
