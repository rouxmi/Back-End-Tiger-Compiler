package tds;
import java.util.ArrayList;
import java.util.Stack;

import ast.Ast;

public class Table {
	public static int nbTable=-1;
	public int id;
	public ArrayList<VarType> variables = new ArrayList<VarType>();
	public ArrayList<ProcFonc> fonctions = new ArrayList<ProcFonc>();
	public int idpere;
    public ArrayList<Table> fils = new ArrayList<Table>();
    public int arg_depl;
    public int var_depl;
    public int arr_depl;
    public String nom;
	
	public Table(int pere,String nompere){
		nbTable++;
		this.id = nbTable;
        this.idpere = pere; 
        this.nom=nompere;
        arg_depl = -2;
        var_depl = 1;
	}

    public Table(int pere){
        this.id = pere;
        this.idpere = pere; 
	}
	
	public Table(ArrayList<VarType> var, ArrayList<ProcFonc> fct,int pere){
		nbTable++;
		this.id = nbTable;
		this.variables.clear();
		this.fonctions.clear();
		this.variables.addAll(var);
		this.fonctions.addAll(fct);
        this.idpere = pere;
        for (VarType the_var : this.variables) {
            the_var.setTableID(this.id);
            if (the_var.getDeplacement() == 0) {
                the_var.setDeplacement(var_depl);
                var_depl++;
            }
        }

        for (ProcFonc the_func : this.fonctions) {
            the_func.setTableID(this.id);
        }
	}
	public String toString(){
        int nbvar= 0;
        int nbfct=0;
        for(int k=0;k<this.variables.size();k++){
            if (!this.variables.get(k).getNature().equals("Type")){
			    nbvar++;
            }
		}
        for(int k=0;k<this.fonctions.size();k++){
            if (this.fonctions.get(k).getNature().equals("fonction")){
			    nbfct++;
            }
		}
		String val = "";
        if (this.idpere==-1){
            val = "\u001B[32m"+"| TABLE ID="+this.id+"\u001B[0m"+"\n"+"\u001B[32m"+
            "| Nom de la fonction="+this.nom+"\u001B[0m"+"\n"+"\u001B[31m" ;
        }
        else{
            if (this.variables.size()-nbvar>0 || this.fonctions.size()-nbfct>0 || nbvar>0 || nbfct>0){
                val = "\u001B[32m"+"| TABLE ID="+this.id+"\u001B[0m"+"\n"+"\u001B[32m"+
                "| Nom de la fonction="+this.nom+"\u001B[0m"+"\n"+"\u001B[32m"+
                "| ID du pere="+this.idpere+"\u001B[0m"+"\n"+"\u001B[31m" ;
            }
        }
        if (this.variables.size()-nbvar>0){
            //on ajoute les Types à l'affichage
            val += "| Types:"+"\u001B[0m"+"\n"+"\u001B[34m"+"| " + String.format("%-6s", "Nature") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-9s", "Type") + " | " 
            + String.format("%-12s", "Deplacement") + " | " + String.format("%-14s", "Est Utilisé")+ " | " + String.format("%-12s", "Dimensions") + " | " + String.format("%-15s", "Type des éléments") + "\u001B[0m" + "\n";
            for(int k=0;k<this.variables.size();k++){
                //System.out.println("");
                if (this.variables.get(k).getNature().equals("Type")){
                    val=val+this.variables.get(k).toString()+"\n";
                }
            }
        }
        if (nbvar>0){
            //on ajoute les variables à l'affichage
            val += "\u001B[31m" +"| Variables:"+"\u001B[0m"+"\n";
            val += "\u001B[34m"+"| " + String.format("%-6s", "Nature") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-9s", "Type") + " | " 
            + String.format("%-12s", "Deplacement") + " | " + String.format("%-14s", "Initialisation") + " | " + String.format("%-14s", "Est Utilisé")+ " | " + String.format("%-12s", "Dimensions") + "\u001B[0m" + "\n";
            for(int k=0;k<this.variables.size();k++){
                //System.out.println("");
                if (this.variables.get(k).getNature().equals("Var")){
                    val=val+this.variables.get(k).toString()+"\n";
                }
            }
        }
        
        if (this.fonctions.size()-nbfct>0){
            //on ajoute les procédures à l'affichage
            val += "\u001B[31m" +"| Procédures:"+"\u001B[0m"+"\n";
            val += "\u001B[34m"+"| " + String.format("%-10s", "Nature") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-9s", "Type") 
            + " | " + String.format("%-7s", "Nb Args") + " | " + String.format("%-14s", "Est Utilisé")  + "\u001B[0m" + "\n";
            for(int k=0;k<this.fonctions.size();k++){
                if (this.fonctions.get(k).getNature().equals("procedure")){
                    val=val+this.fonctions.get(k).toString()+"\n";
                }
            }
        }

        if (nbfct>0){
            //on ajoute les procédures à l'affichage
            val += "\u001B[31m" +"| Fonctions:"+"\u001B[0m"+"\n";
            val += "\u001B[34m"+"| " + String.format("%-10s", "Nature") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-9s", "Type") 
            + " | " + String.format("%-7s", "Nb Args") + " | " + String.format("%-20s", "Types Arguments") + " | " + String.format("%-14s", "Est Utilisé")+ "\u001B[0m" + "\n";
            for(int k=0;k<this.fonctions.size();k++){
                if (this.fonctions.get(k).getNature().equals("fonction")){
                    val=val+this.fonctions.get(k).toString()+"\n";
                }
            }
        }
		return val;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void addVarType(VarType var, Ast tree) {
		if(!checkAlreadyExist(var)){
            this.variables.add(var);
            var.setTableID(this.id);
            if (var.getDeplacement() == 0 && var.isLocal()) { // Si own, on a deja la variable ailleurs pas besoin de la mettre dans la pile
                var.setDeplacement(var_depl);
                var_depl++;
            
            }
        }
        else{
            System.err.println("\u001B[91m"+"Ligne "+tree.getLigne()+":"+tree.getColonne()+" : "+"DoubleDeclarationException : "+var.getNature()+" "+var.identifiant+" deja déclarée dans le même bloc\u001B[0m\n");
        }
    }

    public VarType getVar(String varName) {
        for (VarType myVar : variables) {
            if (myVar.getIdentifiant().equals(varName) && myVar.getNature()=="Var") {
                return myVar;
            }
        }
        return null;
    }

    public VarType getType(String varName) {
        for (VarType myVar : variables) {
            if (myVar.getIdentifiant().equals(varName) && myVar.getNature()=="Type") {
                return myVar;
            }
        }
        return null;
    }

    public void addArray(VarType var) {
		if(!checkAlreadyExist(var)){
            this.variables.add(var);
            var.setTableID(this.id);
            if (var.getDeplacement() == 0 && var.isLocal()) { // Si own, on a deja la variable ailleurs pas besoin de la mettre dans la pile
                var.setDeplacement(var_depl);
                arr_depl++;
            
            }
        }
    }
    
    public void addArg(VarType var) {
		if(!checkAlreadyExist(var)){
            this.variables.add(var);
            var.setTableID(this.id);
            if (var.getDeplacement() == 0 && var.isLocal()) {
                var.setDeplacement(arg_depl);
                arg_depl--;
            }
        }
	}
	
	public void addProcFonc(ProcFonc fct , Ast tree) {
		if(!checkAlreadyExist(fct)){
            this.fonctions.add(fct);
            fct.setTableID(this.id);
		}
        else{
            System.err.println("\u001B[91m"+"Ligne "+tree.getLigne()+":"+tree.getColonne()+" : "+"DoubleDeclarationException : "+fct.getNature()+" "+fct.identifiant+" deja déclarée dans le même bloc\u001B[0m\n");
        }
	}
	
	public void addFils(Table fils){
		this.fils.add(fils);
	}

	public boolean checkAlreadyExist(Object var){
		if(var instanceof  VarType){
			for(VarType el : variables){
                if(el.getIdentifiant().equals(((VarType) var).getIdentifiant()) && el.getNature().equals(((VarType) var).getNature())){
                    return true;
				}
			}
			return false;
		}else{
			for(ProcFonc el : fonctions){
				if(el.getIdentifiant().equals(((ProcFonc) var).getIdentifiant())){
					return true;
				}
			}
			return false;
		}
	}

	public int getIdpere() {
		return idpere;
    }
    
    public VarType getVarType(String varName) {
        for (VarType myVar : variables) {
            if (myVar.getIdentifiant().equals(varName)) {
                return myVar;
            }
        }
        return null;
    }

   

    public ProcFonc getProcFonc(String funcName) {
        for (ProcFonc myFunc : fonctions) {
            if (myFunc.getIdentifiant().equals(funcName)) {
                return myFunc;
            }
        }
        return null;
    }

    public void afficher(){
        System.out.println(this.toString());
        for(Table fils : this.fils){
            fils.afficher();
        }
    }

    public Table joinTDS(Stack<Table> pile) {
        Table newTDS = this;
        int id = this.idpere;
        while(id != -1) {
            Table tds = searchTDS(pile, id);
            for (VarType var : tds.variables) {
                if(!checkAlreadyExist(var)){
                    newTDS.variables.add(var);
                    var.setTableID(this.id);
                    if (var.getDeplacement() == 0 && var.isLocal()) { // Si own, on a deja la variable ailleurs pas besoin de la mettre dans la pile
                        var.setDeplacement(var_depl);
                        var_depl++;
                    }
                }
            }
            for (ProcFonc proc : tds.fonctions) {
                if(!checkAlreadyExist(proc)){
                    newTDS.fonctions.add(proc);
                    proc.setTableID(this.id);
                }
            }
            id=tds.getIdpere();

        }
        return newTDS;
    }

    public void setUsed(Stack<Table> pile, String varName, String nature) {
        //inverse the Stack
        Stack<Table> temp = new Stack<>();
        while(!pile.isEmpty()){
            temp.push(pile.pop());
        }
        for (Table tds : temp) {
            VarType var = tds.getVarType(varName);
            if (var != null && var.getNature().equals(nature)) {
                var.setUsed(true);
                while(!temp.isEmpty()){
                    pile.push(temp.pop());
                }
                return;
            }else{
                ProcFonc proc = tds.getProcFonc(varName);
                if (proc != null) {
                    proc.setUsed();
                    while(!temp.isEmpty()){
                        pile.push(temp.pop());
                    }
                    return;
                }
            }
            
        }
        while(!temp.isEmpty()){
            pile.push(temp.pop());
        }
    }

    public Table searchTDS(Stack<Table> pile, int id) {
        for (Table tds : pile) {
            if (tds.getId() == id) {
                return tds;
            }
        }
        return null;
    }

    public ArrayList<Table> getAllChildren(){
        ArrayList<Table> allChildren = new ArrayList<>();
        allChildren.add(this);
        for (Table fils : this.fils) {
            allChildren.addAll(fils.getAllChildren());
        }
        return allChildren;
    }

}
