package Utilitaire;
import java.util.Random;
import java.util.ArrayList;


public class random {
    public static int randInt(int min, int max){
        //retourne un entier aléatoire dans [min,max]
        Random rand = new Random();
        return min + rand.nextInt(max + 1 -min);
    }
    public static <T> T getRandomElt(T[] list){
        int i = randInt(0,list.length-1);
        return list[i];
    }
    public static <T> T getRandomElt(ArrayList<T> list){
        int i = randInt(0,list.size()-1);
        return list.get(i);
    }
}
