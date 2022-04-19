package Control;
import  Model.*;
import java.util.ArrayList;
import Utilitaire.random;

public class Control {
    private Model model;
    private Tour turn;
    private keyEcouteur k;

    public Control(Model m, keyEcouteur k){
        this.model = m;
        this.k = k;
        this.turn = new Tour(m,k);
    }
    public void newTurn() throws  Exception{
        this.turn.endTurn();

    }
    public keyEcouteur getKeyEcouteur(){
        return this.k;
    }




}
