package Model;

import java.util.ArrayList;

public class Joueur {
    private Case c;
    private String nom;
    private ArrayList<Objet> inventaire;


    public Joueur(Case c,String nom){
        this.c = c;
        this.nom = nom;
        this.inventaire = new ArrayList<Objet>();
    }

    public String toString(){
        if(this.inventaire.size() == 0)
            return "( Nom : " + this.nom + ", Case : " + this.c.toString() + ")";
        String render = "( Nom : " + this.nom + ", Case : " + this.c.toString() + "Inventaire : (";
        int size = this.inventaire.size();
        for(int i = 0; i<size; i++){
            render += this.inventaire.get(i).toString();
            if(i!= size -1)
                render += " , ";
        }
        return render + ")";
    }
    public void prendPossession(Objet o){
        this.inventaire.add(o);
    }
    public boolean is_in(Objet o){
        return this.inventaire.indexOf(o) != -1;
    }
    public void retireObjet(Objet j){
        this.inventaire.remove(j);

    }
    public String getNom(){return this.nom;}
    public int[] getPos(){return this.c.getPos();}
    public ArrayList<Objet> getInventaire(){return this.inventaire;}
}
