package Model;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Objet> objets;
    private ArrayList<victoryObject> vicObjets;
    public Inventaire(){
        this.objets = new ArrayList<Objet>();
        this.vicObjets = new ArrayList<victoryObject>();
    }
    public Inventaire(ArrayList<Objet> objets, ArrayList<victoryObject> vicObjets){
        this.objets = objets;
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
    public void addObject(Objet o){
        this.objets.add(o);
    }
    public ArrayList<victoryObject> getVicObjets(){
        return this.vicObjets;
    }
    public ArrayList<Objet> getObjets(){
        return this.objets;
    }
    public boolean hasArtefactK(ArtefactType a){
        for(victoryObject v : vicObjets){
            if(v.isKey() && v.what() == a){
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
    public String toString(){
        String render = "Objets : (";
        for(int i =0; i<this.objets.size();i++){
            render+=this.objets.get(i).toString();
            if(i<this.objets.size()-1) render +=",";
        }
        render += "Artefact et clés  : ";
        for(int i =0; i<this.vicObjets.size();i++){
            render+=this.vicObjets.get(i).toString();
            if(i<this.vicObjets.size()-1) render +=",";
        }
        return render;


    }
}
