package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Inimigo extends Rectangle{ //utilizo o extends do rectangle que ja me da colisão e dimensão

    public int spd = 2; //velocidade do personagem
    public int right = 0,up = 0,down = 1,left = 0; //variaveis de movimentação

    public int  curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int dir = -1;



    public Inimigo (int x, int y){ //defino as posições do meu player e suas dimensões
        super(x,y,64,64);
    }


    public void tick(){ //aqui no tick estou utilizando if com boolean para movimentar o personagem nos eixos x e y
        boolean moved = true;
        if(down == 1 && World.isFree(x, y - 1)){
            y++;
        }

        if(moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.inimigo_front.length) {
                    curAnimation = 0;
                }
            }
        }

        if(shoot) { //chamando class do tiro
            shoot = false;
            bullets.add(new Bullet(x,y,dir)); //usando proporções e direções de tiro
        }
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).tick();
        }
    }
    public void render(Graphics g){ //meu render está renderizando meu player em azul para visualizar durante o processo
        //g.setColor(new Color(0xFF8034FF, true));
        //g.fillRect(x,y,width,height);

        g.drawImage(Spritesheet.inimigo_front[curAnimation],x,y,32,32,null);

        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}
