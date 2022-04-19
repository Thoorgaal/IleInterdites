package View;
import javax.swing.*;
import java.awt.event.*;

import Model.Model;
import Control.*;


public class Fenetre extends JFrame {
    private Contenu  content;
    private int width,height;
    private keyEcouteur k;
    public Fenetre(int width,int height,Model model, Control c,keyEcouteur k){
        super("Iles Interdites");
        this.k = k;
        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        this.content = new Contenu(this,model.getPlateau(), model.getJoueurs(), c);
        this.getContentPane().add(this.content);
        setVisible(true);
        this.addKeyListener(this.k);
        System.out.println(k);
        System.out.println(this.k);
        System.out.println(this.getKeyListeners()[0]);

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
