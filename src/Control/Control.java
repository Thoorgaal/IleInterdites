package Control;
import  Model.*;
import java.util.ArrayList;
import Utilitaire.random;
import View.*;

public class Control {
    private Model model;
    private Tour turn;
    private keyEcouteur k;
    private View v;

    public Control(Model m, keyEcouteur k,View v){
        this.model = m;
        this.k = k;
        this.v = v;
        this.turn = new Tour(m,k,v);

    }
    public void newTurn() throws  Exception{
        this.turn.endTurn();

    }
    public void t() throws Exception{
        this.turn.Turn();
    }
    public keyEcouteur getKeyEcouteur(){
        return this.k;
    }




}
