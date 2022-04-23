package View;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

import Control.Control;
import Model.*;

import java.text.Normalizer;
import  java.util.ArrayList;

public class Contenu extends JPanel{
    private JFrame fen;
    private Plateau plat;
    private ArrayList<Joueur> joueurs;
    private Color fond;

    public Contenu(JFrame fen, Plateau plat, ArrayList<Joueur> js, Control c){
        super();
        this.setLayout(null);
        this.fen = fen;
        this.setBounds(0,0,fen.getWidth(),fen.getHeight());
        this.plat = plat;
        this.fond = new Color(255,255,255);
        this.joueurs = js;
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
    public void paint(Graphics g){
        int x = 20, y = 20;
        Image imgInnonde = Toolkit.getDefaultToolkit().getImage("images/10_20.png");
        Image imgSubmerge = Toolkit.getDefaultToolkit().getImage("images/0_20.png");
        Image imgNormal = Toolkit.getDefaultToolkit().getImage("images/tatami.png");
        Image imgDiplome = Toolkit.getDefaultToolkit().getImage("images/diplome.jpeg");
        for(ArrayList<Case> l : this.plat.getPlat()){
            for(Case c : l){
                if(c.getEtat() == EtatCase.INNONDE){
                    g.drawImage(imgInnonde, x,y, null);
                }else if(c.getEtat() ==EtatCase.SUBMERGE) {
                    g.drawImage(imgSubmerge, x,y, null);
                }else if(c.getEtat() ==EtatCase.NORMAL){
                    try{
                        switch (c.what()){
                            case NORMAL:
                                g.drawImage(imgNormal, x,y, null);
                                break;
                            case HELIPORT:
                                g.drawImage(imgDiplome, x,y, null);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                g.setColor(this.fond);
                g.drawRect(x,y,Case.width,Case.height);
                x+=Case.width;
            }
            x = 20;
            y+=Case.height;
        }
        int i = 0;
        int ipri = 1;
        Font Fontnomperso = new Font ("Courier New", 1, 17);
        for(Joueur j: this.joueurs) {
            g.setColor(Color.WHITE);
            //nom sur la case
            x = j.getPos()[0];
            y = j.getPos()[1];
            g.drawImage(Toolkit.getDefaultToolkit().getImage("images/"+ipri+".jpg"), 20 + x*50,20 +y*50, null);


            //inventaires
            g.setColor(Color.red);
            g.fillRect(fen.getWidth() - 295, 20 +i*75, 280,130);
            g.drawImage(Toolkit.getDefaultToolkit().getImage("images/"+ipri+".jpg"), fen.getWidth() - 295, 20 +i*75, null);
            g.setColor(Color.black);
            g.setFont(Fontnomperso);
            g.drawString(j.getNom(), fen.getWidth() - 240, 60 +i*75);
            i += 2;
            ArrayList<Objet> inv = j.getInventaire();
            for(Objet obj: inv){

            }
            ipri++;
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
