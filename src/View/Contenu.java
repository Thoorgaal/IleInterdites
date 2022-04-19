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

    public Contenu(JFrame fen, Plateau plat, ArrayList<Joueur> js){
        super();
        this.setLayout(null);
        this.fen = fen;
        this.setBounds(0,0,fen.getWidth(),fen.getHeight());
        this.plat = plat;
        this.fond = new Color(255,255,255);
        this.joueurs = js;
        /*JButton myButton = new JButton("new turn");
        myButton.setLocation(fen.getWidth() - 200, fen.getHeight()/2);
        myButton.setSize (110,25);
        Contenu thiscontenu = this;
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    c.newTurn();
                    thiscontenu.revalidate();
                    thiscontenu.repaint();
                }catch (Exception expp){

                }
            }
        });
        //this.add(myButton);*/
    }
    public void paint(Graphics g){
        int x = 20, y = 20;
        Image imgInnonde = Toolkit.getDefaultToolkit().getImage("images/10_20.png");
        Image imgSubmerge = Toolkit.getDefaultToolkit().getImage("images/0_20.png");
        Image imgNormal = Toolkit.getDefaultToolkit().getImage("images/tatami.png");
        for(ArrayList<Case> l : this.plat.getPlat()){
            for(Case c : l){
                if(c.getEtat() == EtatCase.INNONDE){
                    g.drawImage(imgInnonde, x,y, null);
                }else if(c.getEtat() ==EtatCase.SUBMERGE) {
                    g.drawImage(imgSubmerge, x,y, null);
                }else if(c.getEtat() ==EtatCase.NORMAL){
                    g.drawImage(imgNormal, x,y, null);
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
        for(Joueur j: this.joueurs) {
            g.setColor(Color.WHITE);
            //nom sur la case
            x = j.getPos()[0];
            y = j.getPos()[1];
            g.drawImage(Toolkit.getDefaultToolkit().getImage("images/"+ipri+".jpg"), 20 + x*50,20 +y*50, null);


            //inventaires
            g.setColor(Color.BLACK);
            g.drawString(j.getNom(), fen.getWidth() - 300, 20 +i*10);
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
