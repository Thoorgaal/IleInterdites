package Model;
import java.util.Arrays;
import  java.util.ArrayList;
import Utilitaire.List;
import Utilitaire.random;
import Utilitaire.random.*;

public class Plateau {
    private ArrayList<ArrayList<Case>> plat;
    private int width, height;
    public Plateau(int w,int h){
        this.width = w;
        this.height = h;
        this.plat = new ArrayList<ArrayList<Case>>();
        for(int i = 0;i<h;i++){
            this.plat.add( new ArrayList<Case>());
            for(int j = 0;j<w;j++){
                this.plat.get(i).add(new Case(j,i,EtatCase.NORMAL));
            }
        }
    }
    public void InitiateSimple(){
        for(int i=0;i<this.width;i++){
            this.plat.get(0).get(i).setEtat(EtatCase.SUBMERGE);
            this.plat.get(this.width-1).get(i).setEtat(EtatCase.SUBMERGE);
        }
        for(int i=0;i<this.height;i++){
            this.plat.get(i).get(0).setEtat(EtatCase.SUBMERGE);
            this.plat.get(i).get(this.height -1).setEtat(EtatCase.SUBMERGE);
        }
    }
    public void InitiateRandom(){
        this.InitiateSimple();
    }
    public ArrayList<ArrayList<Case>> getPlat(){
        return this.plat;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }


    public Case get(int x,int y)throws Exception{
        if(y>=this.height){
            throw new IllegalArgumentException("y is too big dady " + Integer.toString(y) + " >" + Integer.toString(this.height));
        }
        if(x>=this.width){
            throw new IllegalArgumentException("x is too big for my pussy");
        }
            return this.plat.get(y).get(x);
    }
    public Case get(int[] pos) throws Exception{
        if(pos.length !=2){
            throw new IllegalArgumentException("should be two coordinates");
        }
        if(pos[0]>=this.width){
            throw new IllegalArgumentException("x is out of the board");
        }
        if (pos[1]>=this.height) {
            throw new IllegalArgumentException("y is out of the board");
        }
        return this.plat.get(pos[1]).get(pos[0]);
    }
    public String toString(){
        String render = "(";
        for(int i = 0;i<this.height;i++){
            for(int j = 0; j<this.width;j++){
                render += this.plat.get(i).get(j).toString();
                if(i != this.height -1 && j != this.width -1)
                    render +=  ",";
            }
            if(i != this.height -1)
                render += "\n";
        }
        return render + ")";
    }
    public ArrayList<int[]> getIdCaseInnondee(){
        ArrayList<int[]> render = new ArrayList<int[]>();
        for (ArrayList<Case> p : this.plat){
            for(Case c : p){
                if(c.getEtat() == EtatCase.INNONDE){
                    render.add(c.getPos());
                }
            }
        }
        return render;
    }
    public ArrayList<int[]> getIndCaseSubmergee(){
        ArrayList<int[]> render = new ArrayList<int[]>();
        for (ArrayList<Case> p : this.plat){
            for(Case c : p){
                if(c.getEtat() == EtatCase.SUBMERGE){
                    render.add(c.getPos());
                }
            }
        }
        return render;
    }

    public Case getRandomCaseDir(int[] pos,Direction[] dir) throws Exception{
        //retourne un voisin aléatoire dans le plateau
        ArrayList<Direction> dirF;
        if(pos[0] == 0 && pos[1]!= this.height-1 && pos[1]!=0){
            dirF= List.getDeleted(Direction.GAUCHE,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[0] == this.width-1 && pos[1]!= this.height-1 && pos[1]!=0) {
             dirF = List.getDeleted(Direction.DROITE, dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[1] == this.height-1 && pos[0] != 0 && pos[0] != this.height-1){
             dirF= List.getDeleted(Direction.BAS,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[1]==0 && pos[0] != 0 && pos[0] != this.height-1){
            dirF= List.getDeleted(Direction.HAUT,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[1] == 0 && pos[0] == 0){
            Direction[] d = new Direction[]{Direction.HAUT,Direction.GAUCHE};
            dirF = List.getDeleted(d,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[0] == 0 && pos[1] == this.height-1){
            Direction[] d = new Direction[]{Direction.BAS,Direction.GAUCHE};
            dirF = List.getDeleted(d,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[0]==this.width-1 && pos[1]==0){
            Direction[] d = new Direction[]{Direction.HAUT,Direction.DROITE};
            dirF = List.getDeleted(d,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }else if(pos[0] == this.width-1 && pos[1]==this.height-1){
            Direction[] d = new Direction[]{Direction.BAS,Direction.DROITE};
            dirF = List.getDeleted(d,dir);
            return this.get(random.getRandomElt(dirF).getPos(pos));
        }
        return null;


    }

    public void InnondeVoisinAlea(int[] pos) throws Exception{
        for(int i = 0; i<3;i++){

        }



    }
}
