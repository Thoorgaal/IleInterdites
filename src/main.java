import  Model.*;
import java.util.ArrayList;
import Control.*;
import View.*;

public class main {
    public static void main(String[] args){
        ArrayList<String> noms = new ArrayList<String>();
        noms.add("Bite");
        ArrayList<int[]> pos = new ArrayList<int[]>();
        int[] p = {1,0};
        pos.add(p);
        Model m;
        Control c;

        try{
            m = new Model(15,15,noms,pos);
            m.InitiateSimple();
            c = new Control(m);


            View v  = new View(m);
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }



    }
}
