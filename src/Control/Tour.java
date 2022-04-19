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
    private int nbJoueur;
    private Model m;
    private ArrayList<Joueur> joueurs;
    private boolean keyPressed,endTurn;
    private int tJ;
    private keyEcouteur k;
    public static final int maxAction = 3;
    private View v;
    EtatTour etat;
    public Tour(Model m, keyEcouteur k,View v){
        this.k = k;
        this.m = m;
        this.plateau = m.getPlateau();
        this.joueurs = m.getJoueurs();
        this .nbJoueur = this.joueurs.size();
        this.etat = EtatTour.NULL;
        this.keyPressed = false;
        this.endTurn = false;
        this.tJ =0;
        this.v = v;
    }
    public void Turn() throws Exception{
        this.etat = EtatTour.TURN;
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
        this.k.setEtatT(EtatTour.TURN);


    }

    public void endTurn() throws Exception{
        this.k.setEnd(true);
        this.innonde(3);
        this.v.Update();
        this.k.setEnd(false);

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
