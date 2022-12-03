package ast;

public class Symbole {
    String identifiant;
    String nature; //var, procedure, fonctions ...
    String type;  // int, null ...
    int deplacement;

    //si procedure ou fonction
    int nbr_param;
    String type_param;

    //si array
    int debut;
    int fin;
    String type_elt;

    //constructeur pour les variables et types
    public Symbole(String identifiant, String nature, String type, int deplacement){
        this.identifiant = identifiant;
        this.nature = nature;
        this.type = type;
        this.deplacement = deplacement;
    }

    //constructeur pour les procedures et fonctions
    public Symbole(String identifiant, String nature, String type, int deplacement, int nbr_param, String type_param){
        this.identifiant = identifiant;
        this.nature = nature;
        this.type = type;
        this.deplacement = deplacement;
        this.nbr_param = nbr_param;
        this.type_param = type_param;
    }

    //constructeur si array
    public Symbole(String identifiant, String nature, String type, int deplacement, int debut, int fin, String type_elt){
        this.identifiant = identifiant;
        this.nature = nature;
        this.type = type;
        this.deplacement = deplacement;
        this.debut = debut;
        this.fin = fin;
        this.type_elt = type_elt;
    }

}
