package Control;
import  Model.*;

public class Control {
    private Model model;
    private Partie turn;

    public Control(Model m){
        this.model = m;
        this.turn = new Partie(m);

    }
    public void newTurn() throws  Exception{
        this.turn.endTurn();

    }
    public void Turn(Action a) throws Exception{
        this.turn.Turn(a);
    }
    public int getPlayerActif(){
        return turn.getPlayerActif();
    }
    public int getToursRestant(){
        return turn.getToursRestant();
    }
}
