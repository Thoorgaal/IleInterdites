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

    public keyEcouteur(Fenetre f, Control c){
        super();
        this.f = f;
        this.c = c;
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
                try{
                    this.c.Turn(Action.ASSECHE);
                }catch (Exception ex){
                    ex.printStackTrace();

                }
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
