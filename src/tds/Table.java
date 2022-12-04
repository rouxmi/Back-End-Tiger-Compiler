package tds;
import java.util.ArrayList;

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
	
	public Table(int pere){
		nbTable++;
		this.id = nbTable;
        this.idpere = pere; 
        arg_depl = -2;
        var_depl = 1;
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
                if (the_var.getType().equals("real")) {
                    var_depl++;
                }
                var_depl++;
            }
        }

        for (ProcFonc the_func : this.fonctions) {
            the_func.setTableID(this.id);
        }
	}
	public String toString(){
		String val = "";
		val = "\u001B[32m"+"| TABLE ID="+this.id+"\u001B[0m"+"\n"+"\u001B[32m"+
				"| ID du pere="+this.idpere+"\u001B[0m"+"\n"+"\u001B[31m" +"| Variables:"+"\u001B[0m"+"\n";
		//on ajoute les variables à l'affichage
        val += "\u001B[34m"+"| " + String.format("%-5s", "LOCAL") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-7s", "Type") + " | " 
         + String.format("%-8s", "tableID") + " | " + String.format("%-5s", "Deplacement") + "\u001B[0m" + "\n";
		for(int k=0;k<this.variables.size();k++){
			//System.out.println("");
			val=val+this.variables.get(k).toString()+"\n";
		}
		//on ajoute les fonctions à l'affichage
		val += "\u001B[31m" +"| Fonctions:"+"\u001B[0m"+"\n";
        val += "\u001B[34m"+"| " + String.format("%-5s", "LOCAL") + " | " + String.format("%-13s", "Name") + " | " + String.format("%-7s", "Type") 
        + " | " + String.format("%-7s", "Nb Args") + " | " + String.format("%-20s", "Types Arguments") + "\u001B[0m" + "\n";
		for(int k=0;k<this.fonctions.size();k++){
			val=val+this.fonctions.get(k).toString()+"\n";
		}
		return val;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void addVarType(VarType var) {
		if(!checkAlreadyExist(var)){
            this.variables.add(var);
            var.setTableID(this.id);
            if (var.getDeplacement() == 0 && var.isLocal()) { // Si own, on a deja la variable ailleurs pas besoin de la mettre dans la pile
                var.setDeplacement(var_depl);
                if (var.getType().equals("real")) {
                    var_depl++;
                }
                var_depl++;
            
            }
        }
    }

    public void addArray(VarType var) {
		if(!checkAlreadyExist(var)){
            this.variables.add(var);
            var.setTableID(this.id);
            if (var.getDeplacement() == 0 && var.isLocal()) { // Si own, on a deja la variable ailleurs pas besoin de la mettre dans la pile
                var.setDeplacement(var_depl);
                if (var.getType().equals("real")) {
                    arr_depl++;
                }
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
                if (var.getType().equals("real")) {
                    arg_depl--;
                }
                arg_depl--;
            }
        }
	}
	
	public void addProcFonc(ProcFonc fct ) {
		if(!checkAlreadyExist(fct)){
            this.fonctions.add(fct);
            fct.setTableID(this.id);
		}
	}
	
	public void addFils(Table fils){
		this.fils.add(fils);
	}

	private boolean checkAlreadyExist(Object var){
		if(var instanceof  VarType){
			for(VarType el : variables){
				if(el.getIdentifiant().equals(((VarType) var).getIdentifiant())){
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
}
