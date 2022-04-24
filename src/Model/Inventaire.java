package Model;

import java.util.ArrayList;

public class Inventaire {
    public boolean haselico = false;
    public boolean hassacsable = false;

    private ArrayList<victoryObject> vicObjets;
    public Inventaire(){
        this.vicObjets = new ArrayList<victoryObject>();
    }
    public Inventaire(ArrayList<victoryObject> vicObjets){
        this.vicObjets = vicObjets;
    }
    public boolean addVictoryObject(victoryObject o){
        boolean isK = o.isKey();
        ArtefactType t = o.what();
        for(victoryObject art : this.vicObjets){
            if(art.isKey() == isK && t == art.what()){
                return false;

            }
        }
        this.vicObjets.add(o);
        return true;
    }
    public ArrayList<victoryObject> getVicObjets(){
        return this.vicObjets;
    }
    public boolean hasArtefactT(ArtefactType a){
        for(victoryObject v : vicObjets){
            if( v.what() == a){
                return true;
            }
        }
        return false;
    }

    public int getIdK(ArtefactType t){
        int i = 0;
        for(victoryObject v : this.vicObjets){
            if( v.isKey() && v.what() == t){
                return i;
            }
            i++;
        }
        return -1;
    }
    public boolean removeK(ArtefactType t){
        for(victoryObject v : this.vicObjets){
            if( v.isKey() && v.what() == t){
                this.vicObjets.remove(v);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Artefact> getArt(){
        ArrayList<Artefact> render = new ArrayList<Artefact>();
        for(victoryObject o : this.vicObjets){
            if(!o.isKey()){
                render.add((Artefact) o);

            }
        }
        return render;
    }
}
