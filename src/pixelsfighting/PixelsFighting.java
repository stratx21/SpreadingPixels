/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelsfighting;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class PixelsFighting extends JPanel{
    private final int FRAME_SIDE_LENGTH=500;
    private final int PIXEL_SIDE=2;
    private int NUMBER_OF_PIXELS;
    
    private JFrame frame = new JFrame();
    
    private Pixel[][] pixels=new Pixel[FRAME_SIDE_LENGTH][FRAME_SIDE_LENGTH];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new PixelsFighting();
    }
    
    public PixelsFighting(){
        
        NUMBER_OF_PIXELS=FRAME_SIDE_LENGTH/PIXEL_SIDE;
        Pixel.setWidth(PIXEL_SIDE);
        
        //Output/display setup::
        this.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(500,500);
        
        
        
        
        
        //Pixel objects setup::
        for(int x=0;x<NUMBER_OF_PIXELS;x++){
            boolean toBeLeft=x<((NUMBER_OF_PIXELS)/2);
            for(int y=0;y<NUMBER_OF_PIXELS;y++){
                pixels[x][y]=new Pixel(x*PIXEL_SIDE,y*PIXEL_SIDE,toBeLeft);
            }
        }
        
        
        frame.add(this);
        frame.setVisible(true);  
        this.setVisible(true);
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        boolean thisPixelColor;
        for(int x=0;x<NUMBER_OF_PIXELS;x++){
            for(int y=0;y<NUMBER_OF_PIXELS;y++){//Inverted the loop
                
                pixels[y][x].draw(g);
                
                thisPixelColor=pixels[y][x].getSide();
                
                switch((int)(Math.random()*4)){
                    case 0:
                        if(y>0)
                            pixels[y-1][x].setSide(thisPixelColor);
                        break;
                    case 1:
                        if(x>0)
                            pixels[y][x-1].setSide(thisPixelColor);
                        break;
                    case 2:
                        if(x<NUMBER_OF_PIXELS-1)
                            pixels[y][x+1].setSide(thisPixelColor);
                        break;
                    case 3:
                        if(y<NUMBER_OF_PIXELS-1)
                            pixels[y+1][x].setSide(thisPixelColor);
                        break;
                }
            }
        }
        
        try{
            Thread.sleep(20);
        } catch(InterruptedException e){
            System.err.println("Error sleeping Thread :: " + e);
        }
        
        this.repaint();
    }
    
}
