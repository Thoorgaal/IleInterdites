package View;
import Model.*;
import Control.*;

public class View {
    private Fenetre fen;
    private Model model;

    public View(Model m , Control c,keyEcouteur k){
        this.fen = new Fenetre(1080,920,m, c,k);
        this.model = m;
    }

    public void Update(){
        this.fen.Update();
    }
}
