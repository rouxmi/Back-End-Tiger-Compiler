package controlesemantique;
import java.util.Stack;

import ast.*;
import exception.ExprException;
import tds.Table;

public class Division {
    //verifie que lorsqu'il y a une division, le diviseur est différent de 0
    public static boolean checkDiviseur(Stack<Table> pile, Table tds, Ast tree) throws Exception{
        /*Table tdsactuel = new Table(tds.getId());
        tdsactuel = tdsactuel.joinTDS(pile); */
        String name =tree.getClass().getName().replace('\n', '\0');
        if(name.equals("ast.Div")){
            String diviseur = ((Div)tree).right.toString();
            if(diviseur!="0"){
                return true;
            }

        }
        throw new ExprException("Division par 0 :"+name+" attendu un diviseur différent de 0");

    }
}
