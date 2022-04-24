package Echange;

import Model.ArtefactType;
import Model.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectJoueur extends JFrame {
    JPanel pan;
    private ArrayList<Joueur> joueurs;
    private ArrayList<ArtefactType> art;
    JLabel text;
    private Joueur sel,courant;
    private ArrayList<JButton> boutonsJoueurs;




    public SelectJoueur(ArrayList<Joueur> joueurs,ArrayList<ArtefactType> art,Joueur courant){
        super("Echange Joueur");
        this.art = art;
        this.courant = courant;
        this.text = new JLabel("A qui voulez vous échanger?");
        this.joueurs = joueurs;
        this.sel = null;
        this.pan = new JPanel();
        this.pan.add(text);
        this.setContentPane(this.pan);
        this.setSize(500,100);
        this.setVisible(true);
        this.boutonsJoueurs = this.boutonsSelPerso();
        for(JButton b : boutonsJoueurs){
            this.pan.add(b);
        }



    }
    public void setSel(Joueur s){
        this.sel = s;
    }
    private ArrayList<JButton> boutonsSelPerso(){
        ArrayList<JButton> render = new ArrayList<JButton>();
        for(Joueur j : this.joueurs){
            SelectJoueur sJ = this;
            render.add(new JButton(j.getNom()));
            render.get(render.size()-1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    SwingUtilities.getWindowAncestor(pan).dispose();
                    System.out.println(j);
                    System.out.println("bite");
                    SelArte s = new SelArte(art,j,courant);
                }
            });
        }
        return render;
    }


}
