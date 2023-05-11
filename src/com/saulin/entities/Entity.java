package com.saulin.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.setHeight(height);
        this.setWidth(width);
        this.setX(x);
        this.setY(y);
        this.setSprite(sprite);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.getSprite(), this.getX(), this.getY(), null);
    }

    public abstract void tick();

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
