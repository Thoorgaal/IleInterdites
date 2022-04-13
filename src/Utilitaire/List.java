package Utilitaire;
import java.util.ArrayList;

public class List {
    public static <T> ArrayList<T> getDeleted(T elt, T[] list){
        ArrayList<T> render = new ArrayList<T>();
        for(T e : list){
            if(e != elt){
                render.add(e);
            }
        }
        return render;
    }
    public static <T> ArrayList<T> Intersect(ArrayList<T> l1,ArrayList<T> l2){
        ArrayList<T> render = new ArrayList<T>();
        for(T elt : l1){
            if(l2.contains(elt)){
                render.add(elt);
            }
        }
        return render;
    }
    public static <T> ArrayList<T> getDeleted(T[] elt, T[] list){
        ArrayList<T> render = new ArrayList<T>();
        for(T e:list){
            render.add(e);
        }
        for(T e : elt){
            render = Intersect(render,getDeleted(e,list ));
        }
        return render;
    }

}
