package tds;

public class VarType{
    public String identifiant;
    public String nature;
    public String type;
    public int deplacement;
    public int tableID;
    public boolean isLocal;



    public VarType(String identifiant, String type){
        this.identifiant= identifiant;
        this.type = type;
        this.deplacement=0;
        this.isLocal=true;
        this.nature = "variable/type";
        this.tableID= -2;

    }
    public String toString(){
        return "| "+ String.format("%-5s", this.isLocal) + " | " + String.format("%-13s", this.identifiant) + " | " + String.format("%-7s", this.type) 
        + " | "  + String.format("%-8s", this.tableID) + " | " + String.format("%-5s", this.deplacement);
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

    public boolean isLocal(){
        return this.isLocal;
    }

}
