package Control;
import Echange.SelectJoueur;
import Model.Model;
import Model.Plateau;
import Model.Case;
import Model.Joueur;
import Utilitaire.List;
import Utilitaire.random;
import Model.Direction;
import Model.ArtefactType;
import Model.Cle;
import Echange.SelArte;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
    private Plateau plateau;
    private Model m;

    private ArrayList<Joueur> joueurs;
    private int nbJoueur;
    public static final int maxAction = 3;

    private int tJ;
    private int action_realisee;
    private boolean win,lost;

    public Partie(Model m){
        this.m = m;
        this.plateau = m.getPlateau();

        this.joueurs = m.getJoueurs();
        this .nbJoueur = this.joueurs.size();
        this.tJ =0;
        action_realisee = 0;
        this.win = false;
        this.lost = false;
    }
    public void Turn(Action a) throws Exception{
        if(this.action_realisee < maxAction) {
            switch (a) {
                case HAUT:
                    try {
                        if(!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.HAUT)) {
                            System.out.println("yo");
                            this.action_realisee--;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case GAUCHE:
                    try {
                        if(!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.GAUCHE)){
                            System.out.println("yo");
                            this.action_realisee--;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case DROITE:
                    try {
                        if(!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.DROITE)){
                            System.out.println("yo");
                            this.action_realisee--;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case BAS:
                    try {
                        if(!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.BAS)){
                            this.action_realisee--;
                            System.out.println("yo");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case ASSECHE:
                    try{
                        if(!this.m.assecher(this.joueurs.get(this.tJ))){
                            this.action_realisee--;

                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    break;
                case RAMASSE:
                    if(!this.m.ramasser(this.joueurs.get(this.tJ))){
                        this.action_realisee--;
                    }
                case ECHANGE:
                    SelectJoueur e = new SelectJoueur(List.getDeleted(this.joueurs.get(this.tJ),this.m.getJoueurInPos(this.joueurs.get(this.tJ).getPos())),this.joueurs.get(this.tJ).getInventaire().getK(),this.joueurs.get(this.tJ));


            }
            this.action_realisee ++;
        }
    }

    public void endTurn() throws Exception{
        this.m.update();
        this.innonde(3);
        this.win = this.m.win();
        this.lost = this.m.lost();
        if(this.lost){
            System.out.println("Lost");
            System.exit(0);
        }
        if(this.win){
            System.out.println("win");
            System.exit(0);
        }
        int alea = random.randInt(0,1);
        if(alea == 1){
            Joueur actu = this.joueurs.get(tJ);
            ArrayList<ArtefactType> to_inspect = actu.getNonPoss();
            int nbclef = to_inspect.size();
            int nbelico = 1;
            int nbsable = 1;
            if(actu.getInventaire().haselico){nbelico = 0;}
            if(actu.getInventaire().hassacsable){nbsable = 0;}
            int n = nbclef + nbelico + nbsable;
            if(n> 0){
                int i = (new Random()).nextInt(n);
                if(i<nbclef){
                    actu.prendPossession(new Cle(to_inspect.get(i)));
                }else if(nbelico == 0){
                    actu.getInventaire().hassacsable = true;
                } else if (nbsable == 0) {
                    actu.getInventaire().haselico = true;
                } else if (i - nbclef == 0) {
                    actu.getInventaire().hassacsable = true;
                }else{
                    actu.getInventaire().haselico = true;
                }
            }
        }
        this.action_realisee = 0;
        this.tJ = (tJ+1)%nbJoueur;
    }
    private void innonde(int n)throws Exception{
        for(int i =0; i<n;i++){
            try{
                ArrayList<Case> to_inspect = this.plateau.getAbleInn();
                Case to_do = random.getRandomElt(to_inspect);
                this.plateau.InnondeVoisinAlea(to_do.getPos());
            }catch (Exception e){
                throw e;
            }
        }
    }

    public int getPlayerActif(){
        return this.tJ;
    }
    public int getToursRestant(){
        return Partie.maxAction - this.action_realisee;
    }
    public Model getModel(){
        return this.m;
    }
}
