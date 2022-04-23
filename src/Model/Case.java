package Model;

public class Case {
    protected int x,y;
    public static final int width = 50, height = 50;
    private type t;
    private Artefact a;
    private boolean hasA;
     protected EtatCase etat;
    public Case(){
        this.x = -1;
        this.y = -1;
        this.etat = EtatCase.NULL;
        this.t = type.NORMAL;
        this.hasA = false;
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
    public type getType(){return this.t;}
    public boolean Do(Joueur j){
        switch (this.t){
            case HELIPORT:
                return false;
        }
        return false;
    }
    public void setArtefact(ArtefactType t){
        this.a = new Artefact(t);
        this.hasA  = true;
        this.t = type.ARTEFACTCASE;

    }
    public boolean hasArte(){
        return this.hasA;
    }
    public ArtefactType getArtefactType(){
        return this.a.what();
    }
    public void removeArtefact(){
        this.hasA = false;
        this.a = null;
    }

}
