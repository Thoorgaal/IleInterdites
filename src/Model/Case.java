package Model;

public class Case {
    protected int x,y;
    public static final int width = 50, height = 50;
    private type t;
    protected EtatCase etat;
    public Case(){
        this.x = -1;
        this.y = -1;
        this.etat = EtatCase.NULL;
        this.t = type.NORMAL;
    }
    public Case(int x, int y,EtatCase etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
        this.t = type.NORMAL;
    }
    public void setEtat(EtatCase e){
        this.etat = e;
    }
    public EtatCase getEtat(){
        return this.etat;
    }
    public String toString(){
        return "(Postion : (" + Integer.toString(x) + "," +Integer.toString(y) +"), Etat : " + this.etat.toString() +", Type : " + this.t +  ")";
    }
    public int[] getPos(){
        return new int[]{this.x,this.y};
    }
    public type what() throws Exception {
        if(this.t == null) throw new Exception("the type is null(" + Integer.toString(this.x) + "," +  Integer.toString(this.y) + ")");
        return this.t;
    }

    public void setType(type t) {
        this.t = t;
    }
    public boolean Do(Joueur j){
        switch (this.t){
            case HELIPORT:
                j.aGagne();
                return true;
        }
        return false;
    }
}
