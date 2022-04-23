package Control;
import View.*;
import Model.Model;
import Model.Plateau;
import Model.Case;
import Model.Joueur;
import Utilitaire.random;
import Model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Tour {
    private Plateau plateau;
    private Model m;

    private ArrayList<Joueur> joueurs;
    private int nbJoueur;
    public static final int maxAction = 3;

    private int tJ;
    private int action_realisee;

    public Tour(Model m){
        this.m = m;
        this.plateau = m.getPlateau();

        this.joueurs = m.getJoueurs();
        this .nbJoueur = this.joueurs.size();
        this.tJ =0;
        action_realisee = 0;
    }
    public void Turn(Action a) throws Exception{
        if(this.action_realisee < maxAction) {
            switch (a) {
                case HAUT:
                    System.out.println("haut");
                    try {
                        this.m.movePlayer(this.joueurs.get(this.tJ), Direction.HAUT);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case GAUCHE:
                    System.out.println("gauche");
                    try {
                        this.m.movePlayer(this.joueurs.get(this.tJ), Direction.GAUCHE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case DROITE:
                    System.out.println("droite");
                    try {
                        this.m.movePlayer(this.joueurs.get(this.tJ), Direction.DROITE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case BAS:
                    System.out.println("bas");
                    try {
                        this.m.movePlayer(this.joueurs.get(this.tJ), Direction.BAS);
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
            }
            this.action_realisee ++;
        }

        /*this.etat = EtatTour.TURN;
        for(int i =0; i<nbJoueur;i++){
            int max = this.maxAction;
            while(max>0){
                if(this.k.getTyped() == true){
                    this.k.setTyped(false);
                    Direction d = this.k.getD();
                    if(d == Direction.NULL){
                        if(this.k.getEnd() == true){
                            System.out.println("bite");
                            this.endTurn();
                        }
                    }
                }
            }
        }
        this.k.setEtatT(EtatTour.TURN);*/


    }

    public void endTurn() throws Exception{
        this.innonde(3);
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
}
