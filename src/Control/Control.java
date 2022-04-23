package Control;
import  Model.*;
import java.util.ArrayList;
import Utilitaire.random;
import View.*;

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
    public void Turn(Action a) throws Exception{
        this.turn.Turn(a);
    }
}
