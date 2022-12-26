package controlesemantique;

import java.util.Stack;

import ast.Appelfunc;
import ast.Exprlist;
import exception.FonctionException;
import tds.Table;

public class fonction {

    //verifie que le nombre de parametres est correct pour une fonction donnée grace à la tds
    public static void checknombreparametres(Appelfunc tree,Stack<Table> pile, Table tds) throws Exception{
        Table tdsactuel = tds.joinTDS(pile);
        int nbrparametre = tdsactuel.getProcFonc(tree.id).getArguments().size();
        if (tree.right == null){
            if(nbrparametre != 0){
                throw new Exception("\u001B[91mFonctionException: Nombre de paramètres incorrect (attendu : "+nbrparametre+" trouvé : 0)\u001B[0m\n");
            }
            else {
                return;
            }
        }
        int nbrparametre2 = 0;
        for(int i=0;i<((Exprlist)tree.right).expr.size();i++){
            nbrparametre2++;
        }
        if(nbrparametre-nbrparametre2 != 0){
            throw new FonctionException("Nombre de paramètres incorrect attendu : "+nbrparametre+" trouvé : "+nbrparametre2);
        }


    }


}
