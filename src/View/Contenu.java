package View;
import java.awt.*;
import javax.swing.*;

import Model.*;

import java.text.Normalizer;
import  java.util.ArrayList;

public class Contenu extends JPanel{
    private JFrame fen;
    private Plateau plat;
    private ArrayList<Joueur> joueurs;
    private Color fond;

    public Contenu(JFrame fen,Plateau plat, ArrayList<Joueur> js){
        super();
        this.setLayout(null);
        this.fen = fen;
        this.setBounds(0,0,fen.getWidth(),fen.getHeight());
        this.plat = plat;
        this.fond = new Color(255,255,255);
        this.joueurs = js;
        JButton myButton = new JButton("new turn");
        myButton.setLocation(fen.getWidth() - 200, fen.getHeight()/2);
        myButton.setSize (110,25);
        this.add(myButton);
    }
    public void paint(Graphics g){
        int x = 20, y = 20;

        for(ArrayList<Case> l : this.plat.getPlat()){
            for(Case c : l){
                if(c.getEtat() == EtatCase.INNONDE){
                    g.setColor(new Color(0,255,255));
                    g.fillRect(x,y,Case.width,Case.height);
                }else if(c.getEtat() ==EtatCase.SUBMERGE) {
                    g.setColor(new Color(0,0,255));
                    g.fillRect(x,y,Case.width,Case.height);
                }else if(c.getEtat() ==EtatCase.NORMAL){
                    //System.out.println(Integer.toString(x) + " " + Integer.toString(y));
                    g.setColor(new Color(0,255,0));
                    g.fillRect(x,y,Case.width,Case.height);
                }
                g.setColor(this.fond);
                g.drawRect(x,y,Case.width,Case.height);
                x+=Case.width;
            }
            x = 20;
            y+=Case.height;
        }
        int i = 0;
        for(Joueur j: this.joueurs) {
            g.setColor(Color.WHITE);
            //nom sur la case
            x = j.getPos()[0];
            y = j.getPos()[0];
            g.drawString(j.getNom(), 20 + x*50,20 +y*50 +20);

            //inventaires
            g.setColor(Color.BLACK);
            g.drawString(j.getNom(), fen.getWidth() - 300, 20 +i*10);
            i += 2;
            ArrayList<Objet> inv = j.getInventaire();
            for(Objet obj: inv){

            }
        }
    }
    public void update(Plateau plat){
        this.plat = plat;
    }
}
