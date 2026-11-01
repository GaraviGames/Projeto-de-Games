package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{ //utilizo o extends do rectangle que ja me da colisão e dimensão

    public int spd = 2; //velocidade do personagem
    public boolean right,up,down,left; //variaveis de movimentação

    public int  curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean shoot = false;

    public int dir = 1;



    public Player (int x, int y){ //defino as posições do meu player e suas dimensões
        super(x,y,64,64);
    }


    public void tick(){ //aqui no tick estou utilizando if com boolean para movimentar o personagem nos eixos x e y
        boolean moved = false;

        if(right && World.isFree(x+spd,y)) { //sistema de direita e esquerda
            x+=spd;
            moved = true;
            dir = 1;
        }else if(left && World.isFree(x-spd,y)) {
            x-=spd;
            moved = true;
            dir = -1;
        }

        if(up && World.isFree(x+spd,y-spd)) { //sistema de cima e baixo
            y-=spd;
            moved = true;
        }else if(down && World.isFree(x,y+spd)) {
            y+=spd;
            moved = true;
        }

        if(moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.player_front.length) {
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

        g.drawImage(Spritesheet.player_front[curAnimation],x,y,32,32,null);

        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}
