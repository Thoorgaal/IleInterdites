package Model;

import java.util.StringTokenizer;

public class Cle extends victoryObject{
    public Cle(ArtefactType t){
        this.t = t;
    }
    @Override
    public String toString(){
        return "(cle, type : " + this.t + ")";
    }
    @Override
    public ArtefactType what(){
        return this.t;
    }

    @Override
    public boolean isKey() {
        return true;
    }
}
