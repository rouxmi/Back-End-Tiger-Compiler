package ast;
import java.util.ArrayList;

public class ProcFonc{
    public String identifiant;
    public String nature;
    public String type;
    public int deplacement;
    public int tableID;
    public boolean isLocal;
    public ArrayList<Argument> arguments = new ArrayList<Argument>();

    public ProcFonc(String identifiant, String type, ArrayList<Argument> args ){
        this.identifiant=identifiant;
        this.type=type;
        this.arguments.addAll(args);
        this.isLocal=true;
        this.deplacement=0;
        this.nature = "procedure/fonction";
        this.tableID= -2;
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

    public ArrayList<Argument> getArguments(){
        return this.arguments;
    }
    public void setArguments(Argument arg){
        this.arguments.add(arg);
    }

    public boolean isLocal(){
        return this.isLocal;
    }

}