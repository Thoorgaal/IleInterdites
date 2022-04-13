package View;
import Model.*;

public class View {
    private Fenetre fen;
    private Model model;

    public View(Model m ){
        this.fen = new Fenetre(1080,920,m);
        this.model = m;
    }
}
