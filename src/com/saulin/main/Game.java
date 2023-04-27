package com.saulin.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;
    private BufferedImage bufferedImage = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);;
    //private SpriteSheet spriteSheet = new SpriteSheet("/spritesheet.png");



    public Game() {
        this.setPreferredSize(new Dimension(this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE));

        this.initFrame();
    }

    public void initFrame() {
        frame = new JFrame("Zelda");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        this.thread = new Thread(this);
        this.isRunning = true;
        this.thread.start();
    }

    public synchronized void stop() {
        this.isRunning = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        Game game = new Game();

        game.start();
    }

    public void tick() {
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferedImage.getGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        /***Renderizando o Jogo***/
        /***/

        graphics.dispose();
        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(
                bufferedImage,
                0,
                0,
                this.WIDTH*this.SCALE,
                this.HEIGHT*this.SCALE,
                null
        );
        bufferStrategy.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = Math.pow(10, 9) / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while(this.isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                this.tick();
                this.render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

        }

        this.stop();
    }
}