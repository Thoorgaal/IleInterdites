package Echange;

import Model.Artefact;
import Model.ArtefactType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Control.Partie;

import Model.Cle;
import Model.Joueur;
public class SelArte extends JFrame {
    private ArrayList<ArtefactType> arte;
    private ArtefactType sel;
    private JPanel pan;
    private Joueur s,c;
    private Partie p;



    public SelArte(ArrayList<ArtefactType> arte,Joueur sel,Joueur courant,Partie p){
        super("Sélection de la clé  à échanger");
        this.s  = sel;
        this.p = p;
        this.c = courant;
        System.out.println(s);
        System.out.println("bite");
        this.arte = arte;
        this.sel = null;
        this.pan = new JPanel();
        JLabel text = new JLabel("Quel artefact voulez vous donner");
        this.pan.add(text);
        this.setContentPane(this.pan);
        this.setSize(500,100);
        this.setVisible(true);
        ArrayList<JButton> bout = this.boutons();
        for(JButton b : bout){
            this.pan.add(b);
        }



    }
    private ArrayList<JButton> boutons(){
        ArrayList<JButton> render = new ArrayList<JButton>();
        for(ArtefactType a  : this.arte){
            render.add(new JButton(a.toString()));
            render.get(render.size()-1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sel = a;
                    System.out.println(a);
                    SwingUtilities.getWindowAncestor(pan).dispose();
                    if(s!=null)s.prendPossession(new Cle(a));
                    c.removeK(a);
                    System.out.println(c);
                    System.out.println(s);
                }
            });
            JButton c = new JButton("Annuler");
             c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.getWindowAncestor(pan).dispose();
                    p.decrementA();
                }
            });
            render.add(c);
        }
        return render;
    }

}
