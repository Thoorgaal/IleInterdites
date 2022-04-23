package Utilitaire;

import java.awt.image.BufferedImage;


public class Images_fonctions {
    public static BufferedImage obscurcie_image(BufferedImage img, float s){
        int Width = img.getWidth(null);
        int Height = img.getHeight(null);
        BufferedImage new_image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);


        for(int y = 0; y<Height;y++){
            for(int x = 0; x < Width; x++){
                int p = img.getRGB(x,y);

                int a =
        }
        return new_image;
    }
}
