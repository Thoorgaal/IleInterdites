package View;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

import Control.Control;
import Model.*;
import Utilitaire.List;
import Utilitaire.Images_fonctions;

import java.awt.image.BufferedImage;
import  java.util.ArrayList;

public class Contenu extends JPanel{
    private JFrame fen;
    private Plateau plat;
    private ArrayList<Joueur> joueurs;
    private Control c;
    private Color fond;

    public Contenu(JFrame fen, Plateau plat, ArrayList<Joueur> js, Control c){
        super();
        this.setLayout(null);
        this.fen = fen;
        this.setBounds(0,0,fen.getWidth(),fen.getHeight());
        this.plat = plat;
        this.fond = new Color(255,255,255);
        this.joueurs = js;
        this.c = c;

        JButton myButton = new JButton("new turn");
        myButton.setLocation(fen.getWidth() - 200, 650);
        myButton.setSize (110,25);
        Contenu thiscontenu = this;
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    c.newTurn();
                    thiscontenu.Update();
                }catch (Exception expp){

                }
                myButton.setFocusable(false);
                fen.setFocusable(true);
            }
        });
        fen.add(myButton);
    }
    public void paint(Graphics g) {
        int x = 20, y = 20;
        Image imgInnonde = Toolkit.getDefaultToolkit().getImage("images/10_20.png");
        Image imgSubmerge = Toolkit.getDefaultToolkit().getImage("images/0_20.png");
        Image imgNormal = Toolkit.getDefaultToolkit().getImage("images/tatami.png");
        Image imgDiplome = Toolkit.getDefaultToolkit().getImage("images/diplome.jpeg");
        Image imgMaths = Toolkit.getDefaultToolkit().getImage("images/maths.jpeg");
        Image imgPhysique = Toolkit.getDefaultToolkit().getImage("images/physique.jpeg");
        Image imgSport = Toolkit.getDefaultToolkit().getImage("images/sport.jpeg");
        Image imgHistoire = Toolkit.getDefaultToolkit().getImage("images/histoire.png");
        for (ArrayList<Case> l : this.plat.getPlat()) {
            for (Case c : l) {
                if (c.getEtat() == EtatCase.INNONDE) {
                    g.drawImage(imgInnonde, x, y, null);
                } else if (c.getEtat() == EtatCase.SUBMERGE) {
                    g.drawImage(imgSubmerge, x, y, null);
                } else if (c.getEtat() == EtatCase.NORMAL) {
                    try {
                        switch (c.what()) {
                            case NORMAL:
                                g.drawImage(imgNormal, x, y, null);
                                break;
                            case HELIPORT:
                                g.drawImage(imgDiplome, x, y, null);
                                break;
                            case ARTEFACTCASE:
                                if (c.hasArte()) {
                                    switch (c.getArtefactType()) {
                                        case MATHS:
                                            g.drawImage(imgMaths, x, y, null);
                                            break;
                                        case PHYSIQUE:
                                            g.drawImage(imgPhysique, x, y, null);
                                            break;
                                        case SPORT:
                                            g.drawImage(imgSport, x, y, null);
                                            break;
                                        case HISTOIRE:
                                            g.drawImage(imgHistoire, x, y, null);
                                            break;

                                    }
                                }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                g.setColor(this.fond);
                g.drawRect(x, y, Case.width, Case.height);
                x += Case.width;
            }
            x = 20;
            y += Case.height;
        }

        //affichages des personages
        Font Fontnomperso = new Font("Courier New", 1, 17);
        Font FontNumTour = new Font("Courier New", 1, 25);
        Font FontArte = new Font("Courier New", 1,  14);
        Font FontClef = new Font("Courier New", 1,  10);

        ArrayList<int[]> positions_joueurs = new ArrayList<int[]>();
        ArrayList<ArrayList<Integer>> id_corespondant = new ArrayList<>();

        int joueurActif = this.c.getPlayerActif();
        int ToursRestant = this.c.getToursRestant();

        for (int j = 0; j < this.joueurs.size(); j++) {
            Joueur joueur = joueurs.get(j);

            //cration de la liste des ids et des position
            if (List.get_index_element(positions_joueurs, joueur.getPos()) != -1) {
                id_corespondant.get(List.get_index_element(positions_joueurs, joueur.getPos())).add(j);
            } else {
                positions_joueurs.add(joueur.getPos());
                id_corespondant.add(new ArrayList<>());
                id_corespondant.get(id_corespondant.size() - 1).add(j);
            }


            //inventaires
            g.setColor(Color.red);
            g.fillRect(fen.getWidth() - 295, 20 + j * 150, 280, 130);
            g.drawImage(Toolkit.getDefaultToolkit().getImage("images/" + (j + 1) + ".jpg"),
                    fen.getWidth() - 295, 20 + j * 150, null);
            g.setColor(Color.black);
            g.setFont(Fontnomperso);
            g.drawString(joueur.getNom(), fen.getWidth() - 240, 60 + j * 150);

            //affiche le num de tours
            if(j == joueurActif){
                g.setFont(FontNumTour);
                g.drawString(String.valueOf(ToursRestant),fen.getWidth() - 50, 55 + j * 150);
            }

            //affichage des titres
            g.setFont(FontArte);
            g.drawString("Artefacts:", fen.getWidth() - 295, 100 + j * 150);
            int decalageArtefact = 0;
            String textClefs = "Clefs: ";
            for(victoryObject obj: joueur.getInventaire().getVicObjets()){
                if(obj.isKey()){
                    switch (obj.what()){
                        case MATHS:
                            textClefs += "MATH, ";
                            break;
                        case PHYSIQUE:
                            textClefs += "PHYSIQUE, ";
                            break;
                        case SPORT:
                            textClefs += "SPORT, ";
                            break;
                        case HISTOIRE:
                            textClefs += "HISTOIRE, ";
                            break;
                    }
                }else{
                    switch (obj.what()){
                        case MATHS:
                            g.drawImage(imgMaths, fen.getWidth() - 215 +45*decalageArtefact,
                                    75 + j * 150, 40,40, null);
                            decalageArtefact ++;
                            break;
                        case PHYSIQUE:
                            g.drawImage(imgPhysique, fen.getWidth() - 215 +45*decalageArtefact,
                                    75 + j * 150, 40,40, null);
                            decalageArtefact ++;
                            break;
                        case SPORT:
                            g.drawImage(imgSport, fen.getWidth() - 215 +45*decalageArtefact,
                                    75 + j * 150, 40,40, null);
                            decalageArtefact ++;
                            break;
                        case HISTOIRE:
                            g.drawImage(imgHistoire, fen.getWidth() - 215 +45*decalageArtefact,
                                    75 + j * 150, 40,40, null);
                            decalageArtefact ++;
                            break;
                    }
                }
            }
            textClefs = textClefs.substring(0, textClefs.length() - 2);
            g.setFont(FontClef);
            g.drawString(textClefs, fen.getWidth() - 295, 135 + j * 150);
        }

        //dessin des filles sur la map
        for (int i = 0; i < id_corespondant.size(); i++) {
            //position de la case
            x = positions_joueurs.get(i)[0];
            y = positions_joueurs.get(i)[1];

            //cas ou il y a une seul fille sur la case
            try {
                if (id_corespondant.get(i).size() == 1 && this.plat.get(positions_joueurs.get(i)).getType() == type.NORMAL
                        && this.plat.get(positions_joueurs.get(i)).getEtat() == EtatCase.NORMAL) {
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("images/" + (id_corespondant.get(i).get(0) + 1) + ".jpg"),
                            20 + x * 50, 20 + y * 50, 50, 50, null);
                } else {
                    for (int j = 0; j < id_corespondant.get(i).size(); j++) {
                        // correctif de position
                        int ybis = 0;
                        if (j - 2 >= 0) {
                            ybis = 1;
                        }
                        int xbis = j - 2 * ybis;

                        g.drawImage(Toolkit.getDefaultToolkit().getImage("images/" + (id_corespondant.get(i).get(j) + 1) + ".jpg"),
                                20 + x * 50 + 25 * xbis, 20 + y * 50 + 25 * ybis, 25, 25, null);
                    }
                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public void changeModel(Plateau plat){
        this.plat = plat;
    }
    public void Update(){
        this.revalidate();
        this.repaint();
    }
}