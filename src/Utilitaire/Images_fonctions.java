package Utilitaire;

import java.awt.image.BufferedImage;


public class Images_fonctions {
    public static BufferedImage obscurcie_image(BufferedImage img, float s){
        int Width = img.getWidth(null);
        int Height = img.getHeight(null);
        BufferedImage new_image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y<Height;y++){
            for(int x = 0; x < Width; x++) {
                int p = img.getRGB(x, y);
                System.out.println(p);

                int a = (p >> 24) & 0xff;
                int r = (int) s*((p >> 16) & 0xff);
                int g = (int) s*((p >> 8) & 0xff);
                int b = (int) s*(p & 0xff);

                //p = (a << 24) | (r << 16) | (g << 8) | b;
                p = (int) s*p;
                System.out.println(p);
                new_image.setRGB(x, y, p);
            }
        }
        return new_image;
    }
}
