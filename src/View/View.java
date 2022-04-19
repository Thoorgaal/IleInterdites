package View;
import Model.*;
import Control.*;

public class View {
    private Fenetre fen;
    private Model model;

    public View(Model m , Control c){
        this.fen = new Fenetre(1080,920,m, c);
        this.model = m;
    }

    public void Update(){
        fen.Update();
    }
}
