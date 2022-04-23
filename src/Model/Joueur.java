package Model;

import java.util.ArrayList;

public class Joueur {
    private Case c;
    private boolean gagne;
    private String nom;
    private Inventaire inventaire;
    private int[] pos;

    public Joueur(Case c,String nom){
        this.c = c;
        this.nom = nom;
        this.inventaire = new Inventaire();
        this.pos = c.getPos();
        this.gagne = false;

    }

    public String toString(){
        return "(Nom:"  + this.nom + ", Inventaire :" + this.inventaire.toString() + ")";
    }

    public String getNom(){return this.nom;}
    public int[] getPos(){return this.pos    ;}
    public Inventaire getInventaire(){return this.inventaire;}
    public void move(Case c) throws Exception{
        this.c = c;
        this.pos = c.getPos();
    }
    public boolean assecher(){
        if(this.c.getEtat() != EtatCase.INNONDE){
            return false;
        }
        this.c.setEtat(EtatCase.NORMAL);
        return true;
    }

    public boolean win() {
        return gagne;
    }
    public void aGagne(){
        this.gagne = true;
    }
    public boolean is_in(victoryObject o){
        return this.inventaire.getVicObjets().indexOf(o) !=-1;
    }
    public boolean is_in(Objet o){
        return this.inventaire.getObjets().indexOf(o) !=-1;
    }
    public boolean is_inK(ArtefactType t){
        return this.inventaire.getIdK(t) !=-1;
    }
    public void prendPossession(Objet o){
        this.inventaire.addObject(o);
    }
    public boolean prendPossession(victoryObject o){
        //return true et ajoute l'objet si celui ci n'est pas dans l'inventaire du joueur
        return this.inventaire.addVictoryObject(o);
    }
    public ArrayList<ArtefactType> getNonPoss(){
        //retourne les types de clés non possédé par le joueur
        ArrayList<ArtefactType> all = ArtefactType.getAll();
        ArrayList<ArtefactType> render = new ArrayList<ArtefactType>();
        for(ArtefactType a : all){
            if(!this.inventaire.hasArtefactK(a)){
                render.add(a);
            }
        }
        return render;
    }
    private boolean removeK(ArtefactType t){
        return this.inventaire.removeK(t);
    }

    public boolean prendArtefac(){
        if(!this.c.hasArte()){
            return false;
        }
        if(!this.removeK(this.c.getArtefactType())){
            return false;
        }
        this.c.removeArtefact();
        return true;

    }
}
