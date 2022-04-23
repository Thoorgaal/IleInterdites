package View;
import javax.swing.*;

import Model.Model;
import Control.*;


public class Fenetre extends JFrame {
    private Contenu  content;
    private int width,height;
    private keyEcouteur k;
    public Fenetre(int width,int height,Model model, Control c){
        super("Iles Interdites");

        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);

        this.content = new Contenu(this,model.getPlateau(), model.getJoueurs(), c);
        this.add(this.content);

        this.k = new keyEcouteur(this, c);
        this.addKeyListener(this.k);

        this.setVisible(true);
        this.setFocusable(true);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
    public void Update(){
        this.content.Update();
    }
}
