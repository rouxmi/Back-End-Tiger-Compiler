package controlesemantique;

import tds.Table;
import tds.TdsVisitor;

public class Utilisation {

    public static void CheckUtilisation(TdsVisitor tds) {
        for(int i = 0; i < tds.getTdsStack().size(); i++) {
            Table t = tds.getTdsStack().get(i);
            for(int j = 0; j < t.variables.size(); j++) {
                if(t.variables.get(j).isUsed() == false) {
                    System.err.println("\u001B[33mUtilisationWarning dans "+t.nom+" : Variable "+t.variables.get(j).identifiant +" non utilisée\u001B[0m\n");
                }
            }
        }
            
    }
    
}
