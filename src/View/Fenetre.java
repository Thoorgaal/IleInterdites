package View;
import javax.swing.*;
import java.awt.event.*;
import Model.Model;
import Control.*;
public class Fenetre extends JFrame {
    Contenu pan;
    private int width,height;
    public Fenetre(int width,int height,Model model, Control c){
        super("Iles Interdites");
        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        this.pan = new Contenu(this,model.getPlateau(), model.getJoueurs(), c);
        this.getContentPane().add(this.pan);
        setVisible(true);
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
        pan.Update();
    }
}
