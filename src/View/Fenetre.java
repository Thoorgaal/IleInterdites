package View;
import javax.swing.*;
import java.awt.event.*;
import Model.Model;
public class Fenetre extends JFrame {
    JPanel pan;
    private int width,height;
    public Fenetre(int width,int height,Model model){
        super("Iles Interdites");
        this.width = width;
        this.height = height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        this.pan = new Contenu(this,model.getPlateau(), model.getJoueurs());
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
}
