package controlesemantique;

import java.util.ArrayList;

import tds.Table;

public class Utilisation {

    public static void CheckUtilisation(Table tds) {
        ArrayList<Table> tables = tds.getAllChildren();
        for(int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            for(int j = 0; j < t.variables.size(); j++) {
                if(t.variables.get(j).isUsed() == false) {
                    System.err.println("\033[0;33m"+"Ligne "+t.variables.get(j).getLigneDeclaration()+":"+t.variables.get(j).getColonneDeclaration()+" : "+"UtilisationWarning dans "+t.nom+" : "+t.variables.get(j).getNature()+" "+t.variables.get(j).identifiant +" non utilisé(e)\u001B[0m\n");
                }
            }
            for(int j = 0; j < t.fonctions.size(); j++) {
                if(t.fonctions.get(j).isUsed() == false) {
                    System.err.println("\033[0;33m"+"Ligne "+t.variables.get(j).getLigneDeclaration()+":"+t.variables.get(j).getColonneDeclaration()+" : "+"UtilisationWarning dans "+t.nom+" : Fonction "+t.fonctions.get(j).identifiant +" non utilisée\u001B[0m\n");}
            }
        }
            
    }
    
}
