package Model;
import java.awt.desktop.SystemSleepEvent;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import  java.util.ArrayList;
import Utilitaire.List;
import Utilitaire.random;
import Utilitaire.random.*;

public class Plateau {
    private ArrayList<ArrayList<Case>> plat;
    private ArrayList<ArtefactType> to_find;
    private int width, height;
    public Plateau(int w,int h) {
        this.width = w;
        this.height = h;
        this.plat = new ArrayList<ArrayList<Case>>();
        for (int i = 0; i < h; i++) {
            this.plat.add(new ArrayList<Case>());
            for (int j = 0; j < w; j++) {
                this.plat.get(i).add(new Case(j, i, EtatCase.NORMAL));
            }
        }
    }


    public boolean is_in(int[] pos) throws Exception{
        if(pos.length !=2){
            throw new IllegalArgumentException("len(pos) should be 2");
        }
        return pos[0]>=0 && pos[0]<this.width && pos[1]>=0 && pos[1]<this.height;
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
    public void InitiateRandom(int n) throws Exception{
        this.InitiateSimple();
        for(int i =0;i<n; i++){
            try{
                ArrayList<Case> to_innonde = this.getAbleInn();
                Case c = random.getRandomElt(to_innonde);
                this.InnondeVoisinAlea(c.getPos());

            }catch (Exception e){
                throw e;
            }
        }

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
    public boolean canBeInnonde(int[] pos) throws Exception{
        //retourne false si la case peut pas avoir un vois innondé true sinon
        if(this.get(pos).getEtat() == EtatCase.SUBMERGE || this.get(pos).getEtat() == EtatCase.INNONDE) {
            ArrayList<Case> to_inspect = this.getCaseInBoard(pos);
            for (Case c : to_inspect) {
                if (c.getEtat() == EtatCase.INNONDE || c.getEtat() == EtatCase.NORMAL) {

                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Case> getNormal(){
        //retourne l'ensemble des cases normales
        ArrayList<Case> render = new ArrayList<Case>();
        for(ArrayList<Case> p : this.plat){
            for(Case c: p){
                if(c.getEtat() == EtatCase.NORMAL){
                    render.add(c);
                }
            }
        }
        return render;
    }
    public ArrayList<Case> getAbleInn() throws Exception{
        //retourne l'ensemble des cases avec un voisin innondable
        ArrayList<Case> render = new ArrayList<Case>();
        for(ArrayList<Case> p : this.plat){
            for(Case c : p ){
                try{
                    if(this.canBeInnonde(c.getPos())){
                        render.add(c);

                    }
                }catch (Exception e){
                    throw e;
                }
            }
        }
        return render;
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
        public ArrayList<Case> getCaseInnondee(){
        ArrayList<Case> render = new ArrayList<Case>();
        for (ArrayList<Case> p : this.plat){
            for(Case c : p){
                if(c.getEtat() == EtatCase.INNONDE){
                    render.add(c);
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
    public ArrayList<Case> getCaseInBoard(int[] pos) throws Exception{
        ArrayList<Case> render = new ArrayList<Case>();
        Direction[] dir = Direction.getAll();
        for(Direction d : dir){
              try{
                  int[] nP = d.getPos(pos);
                  if(this.is_in(nP)){
                        render.add(this.get(nP));

                  }
              }catch (Exception e){
                  throw e;
              }
        }
        return render;
    }


    public void InnondeVoisinAlea(int[] pos) throws Exception{
        //innonde une case aléatoire qui n'est pas déjà submergé (si innondé -> submerge, si normal -> innonde)
        try{
            ArrayList<Case> to_inspect = this.getCaseInBoard(pos);
            ArrayList<Case> to_do = new ArrayList<Case>();
            for(Case c : to_inspect){
                if(c.getEtat() != EtatCase.SUBMERGE){
                    to_do.add(c);
                }
            }
            Case c = random.getRandomElt(to_do);
            c.setEtat(EtatCase.fromInt((c.getEtat().getInt())+1));
        }catch (Exception e){
            throw e;
        }




    }
    public void placeHeliport(ArrayList<Case> p)throws Exception{
        Case c = random.getRandomElt(p);
       c.setType(type.HELIPORT);

    }
    public Case getH()throws Exception{
        for(ArrayList<Case> p : this.plat){
            for(Case c : p){
                if(c.what() == type.HELIPORT){
                    return c;
                }
            }
        }
        throw new RuntimeException("there is no heliport");

    }
    public void setArtefact(ArrayList<ArtefactType> a) throws Exception {
        this.to_find = a;
        ArrayList<Case> to_change = this.getNormal();
        to_change.remove(this.getH());
        for(ArtefactType art : a){
            Case c = random.getRandomElt(to_change);
            c.setArtefact(art);
            to_change.remove(c);
        }

    }
    public ArrayList<ArtefactType> getTo_find(){
        return this.to_find;
    }
    public void innondeCaseInnAl() throws Exception {
        ArrayList<Case> toI = this.getCaseInnondee();
        ArrayList<Case> to_Inspect = new ArrayList<Case>(toI);
        for(Case caseToI : toI){
            for(Direction d : Direction.getAll()){
                int[] c = d.getPos(caseToI.getPos());
                if(!is_in(c) || this.get(c).getEtat() !=EtatCase.NORMAL){
                    to_Inspect.remove(caseToI);
                }
            }
        }
        for(Case i : to_Inspect){
            Direction d = random.getRandomElt(Direction.getAll());
            System.out.println(i);
            this.get(d.getPos(i.getPos())).setEtat(EtatCase.INNONDE);
        }
    }


}
