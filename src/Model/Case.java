package Model;

public class Case {
    private int x,y;
    public static final int width = 50, height = 50;
    private EtatCase etat;
    public Case(){
        this.x = -1;
        this.y = -1;
        this.etat = EtatCase.NULL;
    }
    public Case(int x, int y,EtatCase etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
    }
    public void setEtat(EtatCase e){
        this.etat = e;
    }
    public EtatCase getEtat(){
        return this.etat;
    }
    public String toString(){
        return "(Postion : (" + Integer.toString(x) + "," +Integer.toString(y) +"), Etat : " + this.etat.toString() + ")";
    }
    public int[] getPos(){
        return new int[]{this.x,this.y};
    }
}
