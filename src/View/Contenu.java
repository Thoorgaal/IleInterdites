package View;
import java.awt.*;
import javax.swing.*;
import Model.Plateau;
import Model.Case;
import Model.EtatCase;

import java.text.Normalizer;
import  java.util.ArrayList;

public class Contenu extends JPanel{
    private JFrame fen;
    private Plateau plat;
    private Color fond;
    public Contenu(JFrame fen,Plateau plat){
        super();
        this.fen = fen;
        this.setBounds(0,0,fen.getWidth(),fen.getHeight());
        this.plat = plat;
        this.fond = new Color(255,255,255);

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
                    System.out.println(Integer.toString(x) + " " + Integer.toString(y));
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
    }
    public void update(Plateau plat){
        this.plat = plat;
    }
}
