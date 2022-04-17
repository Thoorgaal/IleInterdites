package Control;
import Model.Model;
import Model.Plateau;
import Model.Case;
import Utilitaire.random;

import java.util.ArrayList;

public class Tour {
    private Plateau plateau;
    public Tour(Model m){
        this.plateau = m.getPlateau();

    }

    public void endTurn() throws Exception{
        this.innonde(3);
    }
    private void innonde(int n)throws Exception{
        for(int i =0; i<n;i++){
            try{
                ArrayList<Case> to_inspect = this.plateau.getAbleInn();
                Case to_do = random.getRandomElt(to_inspect);
                this.plateau.InnondeVoisinAlea(to_do.getPos());
            }catch (Exception e){
                throw e;
            }
        }

    }
}
