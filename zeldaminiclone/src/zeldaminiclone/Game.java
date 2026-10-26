package zeldaminiclone;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{

    public static int WIDTH = 980, HEIGHT = 680;

    public Game(){

        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

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



        new Thread(game).start();

    }
    public void run() {

        while(true) {
            System.out.println("Chamando o game looping");
        }

    }
}

