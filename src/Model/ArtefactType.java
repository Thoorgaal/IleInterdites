package Model;

import java.util.ArrayList;
import Utilitaire.List;
public enum ArtefactType {
    FEU,AIR,EAU,TERRE;
    public static ArrayList<ArtefactType> getAll(){
        return  List.fromListGetArray(new ArtefactType[] {FEU,AIR,EAU,TERRE});

    }
}
