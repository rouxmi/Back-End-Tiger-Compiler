package controlesemantique;

import java.util.Stack;

import exception.DecException;
import tds.Table;

public class Declaration {
    
    //verifie si la variable est deja declaree
    /*
     * @param 
     * -name nom de la variable
     * -tableactuel Table table des symboles actuelle
     */
    public static boolean checkVardeclared(String name,Stack<Table> pile, Table tds) throws DecException{
        Table tdsactuel = new Table(tds.getId());
        tdsactuel = tdsactuel.joinTDS(pile);
        if(tdsactuel.getVarType(name) != null){
            return true;
        }
        throw new DecException("Variable non déclarée : "+name);
    }

    //verifie si la fonction est deja declaree
    /*
     * @param 
     * -name nom de la fonction
     * -tableactuel Table table des symboles actuelle
     */
    public static boolean checkFuncdeclared(String name,Stack<Table> pile, Table tds) throws DecException{
        Table tdsactuel = new Table(tds.getId());
        tdsactuel = tdsactuel.joinTDS(pile);
        if(tdsactuel.getProcFonc(name) != null){
            return true;
        }
        throw new DecException("Fonction non déclarée : "+name);
    }
        
}

