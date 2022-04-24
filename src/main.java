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
                    if(i == 1){
                        pos.add(new int[]{2, 2});
                    } else if (i == 2) {
                        pos.add(new int[]{12, 12});
                    } else if (i == 3) {
                        pos.add(new int[]{2, 12});
                    }else if (i == 4){
                        pos.add(new int[]{12, 2});
                    }
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
        menu.setVisible(true);
    }
    public static void start_game(ArrayList<String> noms,ArrayList<int[]> pos){
        Model m;
        Control c;
        try{
            m = new Model(15,15,noms,pos);
            m.InitiateRandom(25);
            ArrayList<Joueur> j = m.getJoueurs();
            j.get(0).ad(new Cle(ArtefactType.MATHS));
            j.get(0).ad(new Cle(ArtefactType.PHYSIQUE));




            c = new Control(m);
            View v  = new View(m, c);
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
