package zeldaminiclone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Game extends Canvas implements Runnable, KeyListener { //utilizo o extends do rectangle que ja me da colisão e dimensão

    public static int WIDTH = 920, HEIGHT = 518; //dimensões do meu programa e no qual vai rodar
    public static int SCALE = 2;
    private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


    public Player player; //chamando class do player

    public World world; //chamando class do Mundo

    public List<Inimigo> inimigos = new ArrayList<Inimigo>();

    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
        new Spritesheet();

        player = new Player(32,32); //parametros para onde o personagem vai iniciar
        world = new World(); //iniciando o world no game
        inimigos.add(new Inimigo(32,33));
        inimigos.add(new Inimigo(32,64));
        inimigos.add(new Inimigo(32,64+32));
        inimigos.add(new Inimigo(64,33));
        inimigos.add(new Inimigo(64,64));
        inimigos.add(new Inimigo(64,64+32));
    }

    public void tick() {
        player.tick();

        for(int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).tick();
        }

    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){ //melhoria de renderização e graficos caso haja erro ele refaz o processo
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();

        for(int xx = 0; xx < WIDTH; xx +=32) {
            for(int yy = 0; yy < HEIGHT; yy +=32) {
                g.drawImage(Spritesheet.tileGrass,xx,yy,null);
            }
        }

        //g.setColor(new Color(14, 112, 34));
        //g.fillRect(0, 0, WIDTH, HEIGHT); //faço um backgroud de preto para visualizar melhor

        /*g.setColor(Color.red);
        g.fillRect(0, 0, 50, 50); dou dimensões a um ponto que será meu player*/

        world.render(g); //chamando metodo do Mundo para renderizar no game
        player.render(g); //chamando metodo do player para renderizar no game

        for(int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).render(g);
        }

        g.dispose();

        g = bs.getDrawGraphics();
        g.drawImage(layer,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
        bs.show();
    }

    public static void main(String[] args){ //é a janela do jogo
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game); //adicionando o Jogo
        frame.setTitle("Mini Zelda"); //titulo do jogo
        frame.pack(); //empacotar todos os codigos e saber o tamanho certo do jogo
        frame.setLocationRelativeTo(null); //centralizar janela (precisa ficar depois do pack



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //comando para fechar o jogo quando eu fechar a janela e nn ficar rodando no pc

        frame.setVisible(true); //para ver a janela



        new Thread(game).start(); //inicia o game e mostra o mesmo pra mim

    }
    public void run() {

        while(true) { //utilizo tick e render pra renderizar e limpar meu jogo
            tick();
            render();
            try {
                Thread.sleep(1000/60); //meu thread.sleep divide 1000 frames e 60 ou seja 60 FPS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //meu keypressed está fazendo quando eu clicar nessas direções o personagem se mova
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            player.shoot = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //aqui quando eu parar de pressionar o meu personagem vai parar de se movimentar
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = false;
        }

    }
}

