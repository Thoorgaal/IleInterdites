package Model;

public enum Direction {
    HAUT, BAS, GAUCHE, DROITE,NULL;

    @Override
    public String toString() {
        switch (this) {
            case HAUT:
                return "haut";
            case BAS:
                return "bas";
            case GAUCHE:
                return "gauche";
            case DROITE:
                return "droite";
        }
        return "NULL";
    }

    public int[] getPos(int[] pos) {
        switch (this) {
            case HAUT:
                return new int[]{pos[0], pos[1] - 1};
            case BAS:
                return new int[]{pos[0], pos[1] + 1};
            case GAUCHE:
                return new int[]{pos[0] - 1, pos[1]};
            case DROITE:
                return new int[]{pos[0] + 1, pos[1]};
        }
        return pos;
    }

    public static Direction[] getAll() {
        return new Direction[]{HAUT, BAS, GAUCHE, DROITE};
    }

    public int fromDGetInt() {
        switch (this) {
            case HAUT:
                return 0;
            case BAS:
                return 1;
            case GAUCHE:
                return 2;
            case DROITE:
                return 3;
        }
        return -1;
    }
}


