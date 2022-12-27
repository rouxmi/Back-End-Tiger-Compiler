package controlesemantique;

import java.util.ArrayList;
import java.util.Stack;

import ast.Appelfunc;
import ast.Exprlist;
import exception.FonctionException;
import tds.Table;
import tds.VarType;

public class fonction {

    //verifie que le nombre de parametres est correct pour une fonction donnée grace à la tds
    /*
     * param:
     * -tree Ast noeud d'appel de fonction
     * -tdsactuel jonctions des tables de symboles aucquels l'on peut accéder à l'endroit de l'appel.
     */
    public static void checknombreparametres(Appelfunc tree,Stack<Table> pile, Table tds) throws Exception{
        Table tdsactuel = new Table(tds.getId());
        tdsactuel = tdsactuel.joinTDS(pile);
        int nbrparametre = tdsactuel.getProcFonc(tree.id).getArguments().size();
        //pas de paramètres
        if (tree.right == null){
            if(nbrparametre != 0){
                throw new FonctionException("Nombre de paramètres incorrect dans: "+tree.id+"(attendu : "+nbrparametre+" trouvé : 0)");
            }
            else {
                return;
            }
        }
        //des paramètres
        int nbrparametre2 = 0;
        for(int i=0;i<((Exprlist)tree.right).expr.size();i++){
            nbrparametre2++;
        }
        if(nbrparametre-nbrparametre2 != 0){
            throw new FonctionException("Nombre de paramètres incorrect dans: "+tree.id+"(attendu : "+nbrparametre+" trouvé : "+nbrparametre2+")");
        }


    }

    //verifie que le type des parametres est correct pour une fonction donnée grace à la tds
    /*
     * param:
     * -tree Ast noeud d'appel de fonction
     * -tdsactuel jonctions des tables de symboles aucquels l'on peut accéder à l'endroit de l'appel.
    */
    public static void checktypeparametres(Appelfunc tree,Stack<Table> pile, Table tds) throws Exception{
        Table tdsactuel = new Table(tds.getId());
        tdsactuel = tdsactuel.joinTDS(pile);
        ArrayList<VarType> listarg = tdsactuel.getProcFonc(tree.id).getArguments();
        //pas de paramètres
        if (tree.right == null){
            return;
        }
        //des paramètres
        int nbrparametre = listarg.size();
        for(int i=0;i<nbrparametre;i++){
            if(!expression.checktype(((Exprlist)tree.right).expr.get(i),(listarg.get(i).getType()),pile,tds)){
                throw new FonctionException("Type de paramètre incorrect dans: "+tree.id+" "+(i+1)+"ème paramètre (attendu : "+listarg.get(i).getType()+" trouvé : "+((Exprlist)tree.right).expr.get(i)+")");
            }
        }
    }
}
