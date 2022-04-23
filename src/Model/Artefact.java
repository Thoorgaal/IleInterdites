package Model;

import java.util.StringTokenizer;
import java.util.spi.AbstractResourceBundleProvider;

public class Artefact extends victoryObject{
    public Artefact(ArtefactType t){
        this.t = t;
    }

    @Override
    public String toString() {
        return "(Artefact, type : " + this.t + ")";
    }
    @Override
    public ArtefactType what(){
        return this.t;
    }

    @Override
    public boolean isKey() {
        return false;
    }
}
