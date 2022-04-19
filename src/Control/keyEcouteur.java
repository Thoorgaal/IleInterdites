package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.Direction;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class keyEcouteur implements KeyListener {
    private Direction d;
    private EtatTour etatT;
    private boolean typed;
    public keyEcouteur(){
        super();
        this.d = Direction.NULL;
        this.typed = false;
    }
    public Direction getD() {
        return d;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.etatT == EtatTour.TURN) {
            switch (e.getKeyChar()) {
                case 'z':
                    if(this.typed == false)this.d = Direction.HAUT;
                    this.typed = true;
                    break;
                case 'q':
                    if(this.typed == false)this.d = Direction.GAUCHE;
                    this.typed = true;
                    break;
                case 'd':
                    if(this.typed == false)this.d = Direction.DROITE;
                    this.typed = true;
                    break;
                case 's':
                    if(this.typed == false)this.d = Direction.BAS;
                    this.typed = true;

            }
        }


    }
    void setEtatT(EtatTour e){
        this.etatT = e;
    }
    public boolean getTyped(){
        return this.typed;
    }
    public void setTyped(boolean typed){
        this.typed = typed;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("qùsldqsdqùlishdqsùhlqsdùqlksdhqsùdlkhqsdùlqskhdq");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("qùsldqsdqùlishdqsùhlqsdùqlksdhqsùdlkhqsdùlqskhdq");
    }
}
