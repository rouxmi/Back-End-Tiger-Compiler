package controlesemantique;


import java.util.Stack;

import org.antlr.v4.parse.ANTLRParser.parserRule_return;

import ast.*;
import tds.Table;

public class Expression {

    //verifie si les deux expressions sont du type int
    /*
     * param:
     * -tree Ast noeud d'expression
     * -type type de l'expression attendu
     */
    public static boolean checktypeOp(Ast tree,Stack<Table> pile,Table tds){
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
        System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression : "+name+"(attendu : int\u001B[0m");
        return false;
    }
    
    public static boolean checktype(Ast tree, String typeAttendu,Stack<Table> pile,Table tds) {
        if (tree == null) {
            return false;
        }
        String name =tree.getClass().getName().replace('\n', '\0');
        if((name.equals("ast.Egal2") || name.equals("ast.Dif") || name.equals("ast.Expr1") || name.equals("ast.Expr0")) && typeAttendu.equals("bool")){
            return checktypeEgal(tree,pile,tds);
        }else if(name.equals("ast.Mul") || name.equals("ast.Div") || name.equals("ast.Plus") || name.equals("ast.Minus")){
            return checktypeOp(tree,pile,tds);
        }else if(name.equals("ast.In") && typeAttendu.equals("int")){
            return true;
        }else if(name.equals("ast.Strin") && typeAttendu.equals("string")){
            return true;
        }else if((name.equals("ast.Sup") || name.equals("ast.Inf") || name.equals("ast.Supeg") || name.equals("ast.Infeg")) && typeAttendu.equals("bool")){
            return checkTypeSupInf(tree,pile,tds);
        }else if(name.equals("ast.Not") && typeAttendu.equals("bool")){
            return checktype((Ast)((Exprnegation)tree).expr,"bool",pile,tds);
        }else if(name.equals("ast.Idcall2")){
            if(!Declaration.checkVardeclared(((Idcall2)tree).id,pile,tds)){
                System.err.println("\u001B[91mDeclarationException dans "+tds.nom+" : Variable non déclarée : "+((Idcall2)tree).id+"\u001B[0m");
                return false;
            }else{
                Table tdsactuel = new Table(tds.getId());
                tdsactuel=tdsactuel.joinTDS(pile);
                if(typeAttendu.equals(tdsactuel.getVar(((Idcall2)tree).id).getType())){
                    return true;
                }
                else{
                    System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type de la variable "+((Idcall2)tree).id+" incorrect dans l'expression: "+name+"(attendu : "+typeAttendu+")\u001B[0m");
                }
            }
        }else if(name.equals("ast.Croexpr") && typeAttendu.equals("int")){
            return checktype(((Croexpr)tree).expr,"int",pile,tds);
        }else if(name.equals("ast.AccesVar")){
            Table tdsactuel = new Table(tds.getId());
            tdsactuel=tdsactuel.joinTDS(pile);
            String type = tdsactuel.getVarType(((AccesVar)tree).id).getType();
            if(tdsactuel.getVarType(type)!=null){
                type = tdsactuel.getVarType(type).getElementtype().get(0).replace(',',' ').trim();
            }
            if (type.equals(typeAttendu)){
                return true;
            }
            else{
                return false;
            }
        }
        else if(name.equals("ast.IfThen")){
            if(checktype(((IfThen)tree).left,typeAttendu,pile,tds)){
                return true;
            }
            if(((IfThen)tree).right!=null){
                if(checktype(((IfThen)tree).right,typeAttendu,pile,tds)){
                    return true;
                }
            }
        }else if(name.equals("ast.Typeswithfieldlist")){
            if(Declaration.checkVardeclared(((Typeidid)((Typeswithfieldlist)tree).typeid).id,pile,tds)){
                if(typeAttendu.equals(tds.getVarType(((Typeidid)((Typeswithfieldlist)tree).typeid).id).getIdentifiant()) && checktypefield((Fieldlist)((Typeswithfieldlist)tree).fieldlist, tds.getVarType(((Typeidid)((Typeswithfieldlist)tree).typeid).id).getIdentifiant(), pile, tds)){
                    return true;
                }
            }
        }
        else if(name.equals("ast.Pointid")){
            String id=((Pointid)tree).id;
            String type = (((Pointid)tree).fils);
            Table tdsactuel = new Table(tds.getId());
            tdsactuel=tdsactuel.joinTDS(pile);
            if(Declaration.checkVardeclared(type,pile,tds)){
                if(replaceType(tds.getVarType(id).getType(),tdsactuel,type).equals(typeAttendu)){
                    return true;
                }
            }

        }
        else{
            System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : "+typeAttendu+")\u001B[0m");
            return false;
        }
        return false;
    }



    private static boolean checkTypeSupInf(Ast tree,Stack<Table> pile,Table tds){
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
        System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int"+")\u001B[0m");
        return false;
    }
        



    //verifie si les deux expressions sont du meme type int ou string
    /*
     * param:
     * -tree Ast noeud d'expression
     * -type type de l'expression attendu
     */
    public static boolean checktypeEgal(Ast tree,Stack<Table> pile,Table tds){
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
                System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int ou string des deux côtés)\u001B[0m");
                return false;
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
                System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int ou string des deux côtés)\u001B[0m");
                return false;
            }
        }else if(name.equals("ast.Expr1")){
            if(checktype(((Expr1)tree).left,"bool",pile,tds) && checktype(((Expr1)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int ou string des deux côtés)\u001B[0m");
                return false;
            }
        }else if(name.equals("ast.Expr0")){
            if(checktype(((Expr0)tree).left,"bool",pile,tds) && checktype(((Expr0)tree).right,"bool",pile,tds)){
                return true;
            }
            else{
                System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int ou string des deux côtés)\u001B[0m");
                return false;
            }
        }
        else{
            System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : int ou string des deux côtés)\u001B[0m");
            return false;
        }
    }

    public static boolean checktypeDptEgal(Ast tree, Table tds, Stack<Table> pile){
        String leftType = getType(((Dptegal)tree).left, tds, pile);
        String rightType = getType(((Dptegal)tree).right, tds, pile);
        if(leftType.equals(rightType)){
            return true;
        }
        else{
            System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression (attendu : "+leftType+" dans le membre droit de l'expression)\u001B[0m");
            return false;
        }
    }


    public static String getType(Ast tree, Table tds, Stack<Table> pile){
        String name =tree.getClass().getName().replace('\n', '\0');
        Table tdsActuelle = new Table(tds.getId());
        tdsActuelle =tdsActuelle.joinTDS(pile);
        if(name=="ast.Pointid"){
            Ast left = ((Pointid)tree).left;
            String fils = ((Pointid)tree).fils;
            String leftType = getType(left, tds, pile);
            return tdsActuelle.getVarType(leftType+"."+fils).getType();
        }
        else if(name=="ast.Idcall2"){
            return tdsActuelle.getVar(((Idcall2)tree).id).getType();
        }
        else if(name=="ast.AccesVar"){
            if(Declaration.checkVardeclared(((AccesVar)tree).id, pile, tds)){
                
                String type = tdsActuelle.getVarType(((AccesVar)tree).id).getType();
                if(tdsActuelle.getVarType(type)!=null){
                    type = tdsActuelle.getVarType(type).getElementtype().get(0).replace(',',' ').trim();
                }
                return type;
            }
            else{
                return "";
            }
        }
        else if(name=="ast.In"){
            return "int";
        }
        else if(name=="ast.Strin"){
            return "string";
        }
        else if(name=="ast.Exprseq"){
            Ast exp = ((Exprseq)tree).expr.get(0);
            String type = "";
            if(exp.getClass().getName().replace('\n', '\0')== "ast.Typeswithfieldlist"){
                type = ((Typeidid)((Typeswithfieldlist)((Exprseq)tree).expr.get(0)).typeid).id;
            }else if(exp.getClass().getName().replace('\n', '\0')== "ast.Typeswithof"){
                type = ((Typeidid)((Typeswithof)((Exprseq)tree).expr.get(0)).typeid).id;
            }
            return type;
        }

        return "";
    }



    public static boolean checktypefield(Fieldlist tree,String nametype,Stack<Table> pile,Table tds) {
        String name =tree.getClass().getName().replace('\n', '\0');
        Table tableactuel = new Table(tds.getId());
        tableactuel = tableactuel.joinTDS(pile);
        int j=0;
        for (int i = 0; i < tree.field.size(); i++) {
            Field field =(Field) tree.field.get(i);
            if(name.equals("ast.Fieldlist")){
                if(checktype(field.expr,tableactuel.getVarType(nametype+"."+field.id).getType(),pile,tds)){
                    j++;
                }
                else{
                    System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression Field: "+name+"(attendu : "+tableactuel.getVarType(nametype+"."+field.id).getType()+")\u001B[0m");
                    return false;
                }
            }
            else{
                System.err.println("\u001B[91mExpressionException dans "+tds.nom+" : Type incorrect dans l'expression Field: "+name+"(attendu : "+tableactuel.getVarType(nametype+"."+field.id).getType()+")\u001B[0m");
                return false;
            }
        }
        if(j==tree.field.size()){
            return true;
        }
        return false;
        
    }

    public static String replaceType(String type,Table tableactuel, String name){
        if(type.equals("int") || type.equals("string")){
            return type;
        }
        else{
            String text = tableactuel.getVarType(type).getType();
            if(text.equals("Array of")){
                return tableactuel.getVarType(type).getElementtype().get(0).replace(',',' ').trim();
            }
            else if(text.equals("TypeList")){
                return tableactuel.getVarType(name).getType();
            }
            else{
                return text;
            }

        }
            
    }
        
}
    
    
