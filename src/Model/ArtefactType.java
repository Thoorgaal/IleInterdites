package Model;

import java.util.ArrayList;
import Utilitaire.List;
public enum ArtefactType {
    MATHS, PHYSIQUE, SPORT, HISTOIRE;
    public static ArrayList<ArtefactType> getAll(){
        return  List.fromListGetArray(new ArtefactType[] {MATHS, PHYSIQUE, SPORT, HISTOIRE});

    }
}
