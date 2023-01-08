package controlesemantique;

import ast.*;
import exception.ForException;

public class BoucleFor {
    public static boolean CheckBorneMinInfBorneMax(Ast min, Ast max) throws Exception {
        String nameMin =min.getClass().getName().replace('\n', '\0');
        String nameMax =max.getClass().getName().replace('\n', '\0');
        if(nameMin.equals("ast.In") && nameMax.equals("ast.In")){
            int minInt = ((In)min).in;
            int maxInt = ((In)max).in;
            if(minInt<=maxInt){
                return true;
            }
        }
        throw new ForException("Boucle For : Borne min supérieure à borne max : attendu une borne min inférieure à une borne max");
    }
}
