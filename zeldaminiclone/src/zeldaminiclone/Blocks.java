package zeldaminiclone;

import java.awt.*;

public class Blocks extends Rectangle {

    public Blocks(int x, int y){
        super(x,y,32,32);
    }

    public void render(Graphics g){

        g.drawImage(Spritesheet.tileWall, x, y, 32,32,null);

        /*g.setColor(Color.red);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);*/
    }
}
