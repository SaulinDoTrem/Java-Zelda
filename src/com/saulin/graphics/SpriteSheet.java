package com.saulin.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage spritesheet;

    public void setSpritesheet(BufferedImage spritesheet) {
        this.spritesheet = spritesheet;
    }

    public BufferedImage getSpritesheet() {
        return this.spritesheet;
    }

    public SpriteSheet(String path) {
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResource(path));
            setSpritesheet(spritesheet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public BufferedImage getSprite(int x, int y, int width, int height) {
        return this.getSpritesheet().getSubimage(x, y, width, height);
    }
}
