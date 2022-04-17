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
    public static EtatCase[] getAll(){
        return new EtatCase[]{NORMAL,INNONDE,SUBMERGE};
    }
    public int getInt(){
        switch (this){
            case NORMAL:
                return 0;
            case INNONDE:
                return 1;
            case SUBMERGE:
                return 2;
        }
        return 3;
    }
    public static EtatCase fromInt(int i){
        switch (i){
            case 0:
                return NORMAL;
            case 1:
                return INNONDE;
            case 2:
                return SUBMERGE;
            default:
                return NULL;

        }
    }
}
