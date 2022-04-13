package Model;

public enum EtatCase {
    NORMAL, INNONDE, SUBMERGE,NULL;
    public  String toString(){
        switch (this){
            case NORMAL:
                return "normal";
            case INNONDE:
                return "innondée";
            case SUBMERGE:
                return "submergée";
        }
        return "";
    }
}
