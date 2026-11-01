package zeldaminiclone;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet { //importo biblioteca java com Imagem da pasta res

    public static BufferedImage spritesheet; //puxando metodo da spritesheet

    public static BufferedImage[] player_front; //carregando imagem com buffered do player de frente
    public static BufferedImage tileWall;
    public static BufferedImage tileGrass; //0,132

    public static BufferedImage[] inimigo_front;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inimigo_front = new BufferedImage[3];
        inimigo_front[0] = Spritesheet.getSprite(0,132,32,32);
        inimigo_front[1] = Spritesheet.getSprite(0,165,32,32);
        inimigo_front[2] = Spritesheet.getSprite(0,198,32,32);

        player_front = new BufferedImage[3];
        player_front[0] = Spritesheet.getSprite(0,0,32,32);
        player_front[1] = Spritesheet.getSprite(0,33,32,32);
        player_front[2] = Spritesheet.getSprite(0,66,32,32);

        tileWall = Spritesheet.getSprite(33, 99, 31, 31);
        tileGrass = Spritesheet.getSprite(0,99,32,32);
    }

    public static BufferedImage getSprite(int x,int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
