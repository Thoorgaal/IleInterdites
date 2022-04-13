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
    private void innonde(int n){
        ArrayList<int[]> sub = this.plateau.getIndCaseSubmergee();
        for(int i = 0;i<n;i++){
            int ind = random.randInt(0,sub.size()-1);
            try{
                this.plateau.InnondeVoisinAlea(sub.get(ind));
            }catch (Exception e){
                e.printStackTrace();
                System.exit(-1);
            }
            sub.remove(ind);
        }

    }
    public void tour(){
        this.innonde(3);
    }
}
