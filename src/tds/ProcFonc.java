package tds;
import java.util.ArrayList;

public class ProcFonc{
    public String identifiant;
    public String nature;
    public String type;
    public int deplacement;
    public int tableID;
    public boolean isLocal;
    public ArrayList<VarType> arguments = new ArrayList<VarType>();

    public ProcFonc(String identifiant, String type, ArrayList<VarType> args ){
        this.identifiant=identifiant;
        this.type=type;
        this.arguments.addAll(args);
        this.isLocal=true;
        this.deplacement=0;
        this.nature = "procedure/fonction";
        this.tableID= -2;
    }

    public String toString(){
        String the_types = "";
        for (Object arg : this.arguments) {
            try {
                VarType var = (VarType) arg;
                the_types += var.getType() + ", ";
                continue;
            }
            catch (Exception e) {
            }
            try {
                ProcFonc func = (ProcFonc) arg;
                the_types += func.getType() + " procedure, ";
                continue;
            }
            catch (Exception e) {
            }
        }
        return "| " + String.format("%-5s", this.isLocal) + " | "+ String.format("%-13s", this.identifiant) + " | " + String.format("%-7s", this.type) + " | " 
        + String.format("%-7s", this.arguments.size()) + " | " + String.format("%-20s", the_types);
	}

    public String getIdentifiant(){
        return this.identifiant;
    }
    public void setIdentifiant(String identifiant){
        this.identifiant=identifiant;
    }

    public String getNature(){
        return this.nature;
    }
    public void setNature(String nature){
        this.nature=nature;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }

    public int getDeplacement(){
        return this.deplacement;
    }
    public void setDeplacement(int deplacement){
        this.deplacement=deplacement;
    }

    public int getTableID(){
        return this.tableID;
    }
    public void setTableID(int tableID){
        this.tableID=tableID;
    }

    public ArrayList<VarType> getArguments(){
        return this.arguments;
    }
    public void setArguments(VarType arg){
        this.arguments.add(arg);
    }

    public boolean isLocal(){
        return this.isLocal;
    }

}