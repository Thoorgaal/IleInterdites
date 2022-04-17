package Control;
import  Model.*;
import java.util.ArrayList;
import Utilitaire.random;

public class Control {
    private Model model;
    private Tour turn;
    public Control(Model m){
        this.model = m;
        this.turn = new Tour(m);
    }
    public void newTurn() throws  Exception{
        this.turn.endTurn();
    }




}
