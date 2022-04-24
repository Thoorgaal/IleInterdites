package Control;
import Model.Model;
import Model.Plateau;
import Model.Case;
import Model.Joueur;
import Utilitaire.List;
import Utilitaire.random;
import Model.Direction;
import Model.ArtefactType;
import Model.Cle;
import javax.swing.*;
import Echange.*;

import javax.swing.text.Position;
import java.awt.event.*;
import Echange.SelArte;
import Model.EtatCase;
import java.util.ArrayList;
import java.awt.Dimension;
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

    private ETatPartie etatPartie = ETatPartie.NORMAL;
    private int[] pos_sac_de_sable;

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
        if(this.etatPartie == ETatPartie.NORMAL) {
            if (this.action_realisee < maxAction) {
                switch (a) {
                    case HAUT:
                        try {
                            if (!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.HAUT)) {
                                System.out.println("yo");
                                this.action_realisee--;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case GAUCHE:
                        try {
                            if (!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.GAUCHE)) {
                                System.out.println("yo");
                                this.action_realisee--;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case DROITE:
                        try {
                            if (!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.DROITE)) {
                                System.out.println("yo");
                                this.action_realisee--;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case BAS:
                        try {
                            if (!this.m.movePlayer(this.joueurs.get(this.tJ), Direction.BAS)) {
                                this.action_realisee--;
                                System.out.println("yo");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case ASSECHE:
                        try {
                            if (!this.m.assecher(this.joueurs.get(this.tJ))) {
                                this.action_realisee--;

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case RAMASSE:
                        if (!this.m.ramasser(this.joueurs.get(this.tJ))) {
                            this.action_realisee--;
                        }
                        break;
                    case HELICO:
                        if(this.joueurs.get(this.tJ).getInventaire().haselico) {
                            this.etatPartie = ETatPartie.HELICO;
                            this.joueurs.get(this.tJ).getInventaire().haselico = false;
                        }else{
                            this.action_realisee--;
                        }
                        break;
                    case SACDESABLE:
                        if(this.joueurs.get(this.tJ).getInventaire().hassacsable) {
                            this.etatPartie = ETatPartie.SACSABLE;
                            this.joueurs.get(this.tJ).getInventaire().hassacsable = false;
                            pos_sac_de_sable = this.joueurs.get(this.tJ).getPos();
                        }else{
                            this.action_realisee--;
                        }
                        break;
                    case ECHANGE:
                        SelectJoueur e = new SelectJoueur(List.getDeleted(this.joueurs.get(this.tJ), this.m.getJoueurInPos(this.joueurs.get(this.tJ).getPos())), this.joueurs.get(this.tJ).getInventaire().getK(), this.joueurs.get(this.tJ), this);

                        if (SelectJoueur.cancel) {
                            this.action_realisee--;
                            SelectJoueur.cancel = false;
                        }
                        break;
                    case CREUSE:
                        Case cJ = this.joueurs.get(this.tJ).getCase();
                        if (cJ.getEtat() == EtatCase.NORMAL) {
                            int i = random.randInt(0, 2);
                            switch (i) {
                                case 0:
                                    cJ.setEtat(EtatCase.INNONDE);
                                    break;
                                case 1:
                                    Joueur j = this.joueurs.get(this.tJ);
                                    ArrayList<ArtefactType> to_inspect = List.getDeleted(List.getConcat(j.getInventaire().getK(), j.getInventaire().getA()), ArtefactType.getAll());
                                    j.prendPossession(new Cle(random.getRandomElt(to_inspect)));


                                    break;
                            }
                        }
                }
                this.action_realisee++;
            }
        }else if(this.etatPartie == ETatPartie.HELICO){
            switch (a) {
                case HAUT:
                    try {
                        if (!this.m.movePlayerhelico(this.joueurs.get(this.tJ), Direction.HAUT)) {
                            System.out.println("yo");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case GAUCHE:
                    try {
                        if (!this.m.movePlayerhelico(this.joueurs.get(this.tJ), Direction.GAUCHE)) {
                            System.out.println("yo");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case DROITE:
                    try {
                        if (!this.m.movePlayerhelico(this.joueurs.get(this.tJ), Direction.DROITE)) {
                            System.out.println("yo");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case BAS:
                    try {
                        if (!this.m.movePlayerhelico(this.joueurs.get(this.tJ), Direction.BAS)) {
                            this.action_realisee--;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case HELICO:
                    this.etatPartie = ETatPartie.NORMAL;
            }
        }else{
            switch (a) {
                case HAUT:
                    if (m.getPlateau().is_in(Direction.HAUT.getPos(pos_sac_de_sable))) {
                        pos_sac_de_sable = Direction.HAUT.getPos(pos_sac_de_sable);
                    }
                    break;
                case GAUCHE:
                    if (m.getPlateau().is_in(Direction.GAUCHE.getPos(pos_sac_de_sable))) {
                        pos_sac_de_sable = Direction.GAUCHE.getPos(pos_sac_de_sable);
                    }
                    break;
                case DROITE:
                    if (m.getPlateau().is_in(Direction.DROITE.getPos(pos_sac_de_sable))) {
                        pos_sac_de_sable = Direction.DROITE.getPos(pos_sac_de_sable);
                    }
                    break;
                case BAS:
                    if (m.getPlateau().is_in(Direction.BAS.getPos(pos_sac_de_sable))) {
                        pos_sac_de_sable = Direction.BAS.getPos(pos_sac_de_sable);
                    }
                    break;
                case SACDESABLE:
                    try {
                        if (this.m.assecher(pos_sac_de_sable)) {
                            this.etatPartie = ETatPartie.NORMAL;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void endTurn() throws Exception{
        this.m.update();
        this.innonde(3);
        this.win = this.m.win();
        this.lost = this.m.lost();
        if(this.lost){
            JFrame menu = new JFrame("Fin");
            menu.setLayout(null);
            menu.setPreferredSize(new Dimension(400, 250));

            JLabel l = new JLabel("Perdu");
            l.setLocation(130,10);
            l.setSize(200,20);
            JButton b = new JButton("Finir");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            b.setLocation(100,180);
            b.setSize(200,20);
            menu.add(b);
            menu.add(l);
            menu.pack();
            menu.setVisible(true);
        }
        if(this.win){
            JFrame menu = new JFrame("Fin");
            menu.setLayout(null);
            menu.setPreferredSize(new Dimension(400, 250));

            JLabel l = new JLabel("Gagné");
            l.setLocation(130,10);
            l.setSize(200,20);
            JButton b = new JButton("Finir");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            b.setLocation(100,180);
            b.setSize(200,20);
            menu.add(b);
            menu.add(l);
            menu.pack();
            menu.setVisible(true);
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
                this.plateau.innondeCaseInnAl();
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
    public void decrementA(){
        this.action_realisee--;
    }


    public ETatPartie getEtatPartie(){return this.etatPartie;}
    public int[] getCaseSacSable(){return this.pos_sac_de_sable;}
}
