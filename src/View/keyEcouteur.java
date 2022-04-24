package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Control.Control;
import Control.Action;
import Model.Direction;
import View.Fenetre;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class keyEcouteur implements KeyListener {
    private Fenetre f;
    private Control c;
    private boolean echange;

    public keyEcouteur(Fenetre f, Control c){
        super();
        this.f = f;
        this.c = c;
        this.echange = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /* est appelles quand une touche est tapper en
        fonction de la touche appelle une fonction de Controle differante*/

            switch (e.getKeyChar()) {
                case 'z':
                    try {
                        this.c.Turn(Action.HAUT);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'q':
                    try {
                        this.c.Turn(Action.GAUCHE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'd':
                    try {
                        this.c.Turn(Action.DROITE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 's':
                    try {
                        this.c.Turn(Action.BAS);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'n':
                    try {
                        this.c.newTurn();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'a':
                    try {
                        this.c.Turn(Action.ASSECHE);
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                    break;
                case 'r':
                    try {
                        this.c.Turn(Action.RAMASSE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'e':
                    try {
                        this.c.Turn(Action.ECHANGE);


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 'c' :
                    try {
                        this.c.Turn(Action.CREUSE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            case 'h':
                try{
                    this.c.Turn(Action.HELICO);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;

            case 'x':
                try{
                    this.c.Turn(Action.SACDESABLE);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;
        }
        this.f.Update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
