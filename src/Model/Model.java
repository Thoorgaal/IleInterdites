package Model;

import java.util.ArrayList;

public class Model {
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private ArrayList<ArtefactType> allTypeArte;



    public Model(int w,int h,ArrayList<String> noms,ArrayList<int[]> pos ) throws Exception{
        this.plateau = new Plateau(w,h);
        this.joueurs = new ArrayList<Joueur>();
        if(noms.size() != pos.size()){
            System.out.println("il doit y avoir autant de positions de joueurs que de noms de joueurs");
            System.exit(-1);
        }
        for(int i = 0; i<noms.size();i++){
            int[] p = pos.get(i);
            try{
                this.joueurs.add(new Joueur(this.plateau.get(p[0],p[1]),noms.get(i)));
            }catch (IllegalArgumentException e){
                throw e;
            }
        }
        this.allTypeArte = ArtefactType.getAll();

    }
    public void InitiateSimple() throws Exception{
        this.plateau.InitiateSimple();
        this.plateau.placeHeliport(this.plateau.getNormal());
        this.plateau.setArtefact(this.allTypeArte);
    }
    public void InitiateRandom(int n) throws Exception{
        this.plateau.InitiateRandom(n);
        this.plateau.placeHeliport(this.plateau.getNormal());
        this.plateau.setArtefact(this.allTypeArte);

    }

    public String toString(){
        String render = "Plateau : \n";
        render += this.plateau.toString();
        render += "\nJoueurs : \n";
        for(Joueur j : this.joueurs){
            render += j.toString();
            render += "\n";
        }

        return render;
    }
    public Plateau getPlateau(){
        return this.plateau;
    }

    public ArrayList<Joueur> getJoueurs(){return this.joueurs;}
    public ArrayList<int[]> getInnondee(){
        return this.plateau.getIdCaseInnondee();
    }
    public ArrayList<int[]> getIndSubmergee(){
        return this.plateau.getIndCaseSubmergee();
    }
    public boolean movePlayer(Joueur j, Direction d) throws Exception{
        int[] pos = d.getPos(j.getPos());
        if(!this.plateau.is_in(pos) || this.plateau.get(pos).getEtat() == EtatCase.SUBMERGE){
            return false;
        }
        Case nC = this.plateau.get(pos);
        j.move(nC);
        return true;
    }
    public boolean  assecher(Joueur j){
        //renvoie vrai et asseche la case si c'est possible, sinon ne fait rien et renvoie false
        return j.assecher();
    }
    public boolean ramasser(Joueur j){
        if(!j.getCase().hasArte()){
            return false;
        }

        ArtefactType t = j.getCase().getArtefactType();
         if(j.prendArtefac()){
             this.plateau.getTo_find().remove(t);
             return true;
         }
         return false;
    }
    public boolean win() throws Exception {
        System.out.println(this.plateau.getTo_find().size());
        if(this.plateau.getTo_find().size() != 0){
            return false;
        }
        int[] pos = this.plateau.getH().getPos();
        for(Joueur j : this.joueurs){
            System.out.println(Integer.toString(pos[0])+ Integer.toString(pos[1]));
            System.out.println(Integer.toString(j.getPos()[0]) + Integer.toString(j.getPos()[1]));
            if(j.getCase().getPos()[0] != pos[0] || j.getCase().getPos()[1] != pos[1] ){
                return false;
            }
        }
        System.out.println("bite");
        return true;
    }

    public boolean isDead(Joueur j,int[] pos) throws Exception{
        ArrayList<Case> dir  = this.plateau.getCaseInBoard(pos);
        for(Case c : dir){
            if(c.getEtat() !=EtatCase.SUBMERGE){
                return false;
            }
        }
        return true;
    }
    public boolean lost() throws Exception{
        for(Joueur j: this.joueurs){
            if(this.isDead(j,j.getPos())){
                this.joueurs.remove(j);
                System.out.println(j);
                return true;

            }
        }
        if(this.plateau.getH().getEtat() == EtatCase.SUBMERGE){
            return true;
        }
        return false;
    }
    public void update(){
        for(Joueur j: this.joueurs){
            for(Artefact a : j.getInventaire().getArt()) {
                if (this.plateau.getTo_find().indexOf(a.what()) != -1) {
                    this.plateau.getTo_find().remove(a.what());
                }
            }
        }
    }
    public boolean donCle(Joueur j1, Joueur j2, ArtefactType t){
        if(j1.getPos()[0] != j2.getPos()[0] || j1.getPos()[1] != j2.getPos()[1]){
            return false;
        }
        if(!(j1.getInventaire().getIdK(t) == -1))
            return false;
        j2.prendPossession(new Cle(t));
        j1.removeK(t);
        return true;

    }

}
