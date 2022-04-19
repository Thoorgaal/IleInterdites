package Control;
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
    private int nbJoueur;
    private Model m;
    private ArrayList<Joueur> joueurs;
    private boolean keyPressed;
    private int tJ;
    private keyEcouteur k;
    public static final int maxAction = 3;
    EtatTour etat;
    public Tour(Model m, keyEcouteur k){
        this.k = k;
        this.m = m;
        this.plateau = m.getPlateau();
        this.joueurs = m.getJoueurs();
        this .nbJoueur = this.joueurs.size();
        this.etat = EtatTour.NULL;
        this.keyPressed = false;
        this.tJ =0;
    }
    public void Turn(){
        this.etat = EtatTour.TURN;
        this.k.setEtatT(this.etat);
        for(int i = 0; i<nbJoueur;i++){
            this.tJ = i;
            int mA = maxAction;
            while(maxAction>0){
                if(this.k.getTyped()){
                    mA--;
                    this.k.setTyped(false);
                    Direction to_do = this.k.getD();

                }
            }
        }

    }

    public void endTurn() throws Exception{
        this.innonde(3);

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
