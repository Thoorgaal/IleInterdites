import  Model.*;

import java.awt.*;
import  java.util.ArrayList;
import Control.*;
import View.*;
import Model.*;
import java.awt.event.*;

import javax.swing.*;

public class main {
    public static void main(String[] args){

        ArrayList<String> noms = new ArrayList<String>();
        ArrayList<int[]> pos = new ArrayList<int[]>();
        noms.add("yo");
        noms.add("uo");
        int[] p = {4,4};
        int[] p2 = {4,4};
        pos.add(p);

        pos.add(p2);
        start_game(noms,pos);
        /*

        JFrame menu = new JFrame("Menu");
        menu.setLayout(null);
        menu.setPreferredSize(new Dimension(400, 250));

        JLabel l = new JLabel("créez vos personages");
        l.setLocation(130,10);
        l.setSize(200,20);

        JTextArea t1 = new JTextArea();
        t1.setLocation(100,40);
        t1.setSize(200,20);

        JTextArea t2 = new JTextArea();
        t2.setLocation(100,70);
        t2.setSize(200,20);

        JTextArea t3 = new JTextArea();
        t3.setLocation(100,100);
        t3.setSize(200,20);

        JTextArea t4 = new JTextArea();
        t4.setLocation(100,130);
        t4.setSize(200,20);

        JButton b = new JButton("commencer");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> noms = new ArrayList<String>();
                if(t1.getText().length() > 0){noms.add(t1.getText());}
                if(t2.getText().length() > 0){noms.add(t2.getText());}
                if(t3.getText().length() > 0){noms.add(t3.getText());}
                if(t4.getText().length() > 0){noms.add(t4.getText());}

                int i = 1;
                ArrayList<int[]> pos = new ArrayList<int[]>();
                for(String n:noms){
                    pos.add(new int[]{i, i});
                    i++;
                }
                menu.dispose();
                if(noms.size() >= 2 ){
                    start_game(noms,pos);
                }
            }
        });
        b.setLocation(100,180);
        b.setSize(200,20);

        menu.add(l);
        menu.add(t1);
        menu.add(t2);
        menu.add(t3);
        menu.add(t4);
        menu.add(b);
        menu.pack();
        menu.setVisible(true);*/
    }
    public static void start_game(ArrayList<String> noms,ArrayList<int[]> pos){
        Model m;
        Control c;
        try{
            m = new Model(15,15,noms,pos);
            m.InitiateRandom(25);
            c = new Control(m);
            ArrayList<Joueur> j = m.getJoueurs();

            Joueur J = j.get(0);
            Joueur j2 =j.get(1);
            System.out.println(J);
            m.movePlayer(j2,Direction.HAUT);
            System.out.println(j2);
            //m.movePlayer(j.get(0), Direction.HAUT);
            View v  = new View(m,c);
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
