package Model;

import java.util.ArrayList;

public class Model {
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
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
    }
    public void InitiateSimple(){
        this.plateau.InitiateSimple();
    }
    public void InitiateRandom(int n) throws Exception{
        try{
            this.plateau.InitiateRandom(n);
        }catch (Exception e){
            throw e;
        }
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
    public void movePlayer(Joueur j, Direction d) throws Exception{
        int[] pos = d.getPos(j.getPos());
        if(!this.plateau.is_in(pos)){
            throw new IllegalArgumentException("the direction given leads to nowhere");
        }
        Case nC = this.plateau.get(pos);
        j.move(nC);
    }
    public boolean  assecher(Joueur j){
        //renvoie vrai et asseche la case si c'est possible, sinon ne fait rien et renvoie false
        return j.assecher();
    }


}
