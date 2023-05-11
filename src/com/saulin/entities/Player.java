package com.saulin.entities;

import com.saulin.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Player extends Entity implements KeyListener {
    private boolean right;
    private boolean up;
    private boolean left;
    private boolean down;
    private static final int speed = 1;
    private static int frames = 0;
    private static final int maxFrames = 5;
    private static int spriteIndex = 0;
    private static boolean moved;
    private static final int maxSpriteIndex = 3;
    private static final int RIGHT_DIR = 0;
    private static final int LEFT_DIR = 1;
    public static int dir = RIGHT_DIR;
    private final BufferedImage[] playerGoingRight = buildSprites(4, 16, 32);;
    private final BufferedImage[] playerGoingLeft = buildSprites(4, 0, 32);;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public boolean isRight() {
        return this.right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return this.up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return this.left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return this.down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void movement(KeyEvent e, boolean isMoving) {
        int code = e.getKeyCode();
        boolean up = code == KeyEvent.VK_W || code == KeyEvent.VK_UP;
        boolean left = code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT;
        boolean right = code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT;
        boolean down = code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN;

        if(up){
            moved = isMoving;
            this.setUp(isMoving);
        }
        if(left) {
            moved = isMoving;
            dir = LEFT_DIR;
            this.setLeft(isMoving);
        }
        if(down) {
            moved = isMoving;
            this.setDown(isMoving);
        }
        if(right) {
            moved = isMoving;
            dir = RIGHT_DIR;
            this.setRight(isMoving);
        }
    }

    public BufferedImage[] buildSprites(int length, int y, int startX) {
        BufferedImage[] array = new BufferedImage[length];

        for(int i = 0; i < length; i++) {
            BufferedImage b = Game.spriteSheet.getSprite(startX, y, this.width, this.height);
            array[i] = b;
            startX+= this.width;
        }

        return array;
    }

    @Override
    public void render(Graphics graphics) {
        if(dir == RIGHT_DIR)
            graphics.drawImage(playerGoingRight[spriteIndex], this.getX(), this.getY(), null);
        if(dir == LEFT_DIR)
            graphics.drawImage(playerGoingLeft[spriteIndex], this.getX(), this.getY(), null);
    }

    @Override
    public void tick() {
        if(this.isUp()) {
            this.setY(this.getY()-speed);
        }
        if(this.isDown()) {
            this.setY(this.getY()+speed);
        }
        if(this.isLeft()) {
            this.setX(this.getX()-speed);
        }
        if(this.isRight()) {
            this.setX(this.getX()+speed);
        }

        if(moved) {
            frames++;
            if(frames == maxFrames) {
                frames = 0;
                spriteIndex++;
                if(spriteIndex > maxSpriteIndex)
                    spriteIndex = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        movement(e, true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        movement(e ,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        movement(e, false);
    }
}
