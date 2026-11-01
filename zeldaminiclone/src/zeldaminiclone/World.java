package zeldaminiclone;

import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class World { //esse é o class de formatão do mundo

    public static List<Blocks> blocos = new ArrayList<Blocks>();

    public World(){ //todos esses for realizam a implementação das paredes ao redor do mundo
        for(int xx = 0; xx < 32; xx++){ //lado superior da parede
            blocos.add(new Blocks(xx*32,0));
        }
        for(int xx = 0; xx < 32; xx++){ //lado inferior da parede
            blocos.add(new Blocks(xx*32,Game.HEIGHT-32));
        }
        for(int yy = 0; yy < 32; yy++){ //lado esquerdo da parede
            blocos.add(new Blocks(0,yy*32));
        }
        for(int yy = 0; yy < 32; yy++){ //lado direito da parede
            blocos.add(new Blocks(Game.WIDTH-32,yy*32));
        }
    }

    public static boolean isFree(int x, int y) {
        for (int i = 0; i < blocos.size(); i++) {
            Blocks blocoAtual = blocos.get(i);
            if (blocoAtual.intersects(new Rectangle(x, y, 32, 32))) {
                return false;
            }
        }

        return true;

    }

    public void render(Graphics g){
        for(int i = 0; i < blocos.size(); i++){
            blocos.get(i).render(g);
        }
    }
}
