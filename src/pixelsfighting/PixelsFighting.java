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
    
    /**
     * The milliseconds until repaint is called again. 
     */
    private final long FRAME_REFRESH_RATE=5;
    
    /**
     * The pixels of the side length of the javax.swing.JFrame (the frame is
     *      kept as a square, besides resizing by the user).
     */
    private final int FRAME_SIDE_LENGTH=1000;
    
    /**
     * This is the size of one side of each Pixel object in pixels. 
     */
    private final int PIXEL_SIDE=10;
    
    /**
     * The number of Pixel objects. 
     */
    private int NUMBER_OF_PIXELS=FRAME_SIDE_LENGTH/PIXEL_SIDE;
    
    /**
     * This integer is used to add or subtract on arrays in order to increase
     *      how fair it is between sides, so that the domination of other
     *      colors can go either from left to right or from right to left.
     */
    private int dx=1;
    
    /**
     * The javax.swing.JFrame that the game is represented by to the user. 
     */
    private JFrame frame = new JFrame();
    
    /**
     * The realtime data of the pixels that changes. 
     */
    private Pixel[][] pixels   =new Pixel[NUMBER_OF_PIXELS][NUMBER_OF_PIXELS];

    /**
     * 
     * Start a new PixelsFighting.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new PixelsFighting();
    }
    
    /**
     * Set up PixelsFighting with the javax.swing.JFrame, this class as the 
     *      javax.swing.JPanel, the data that this program relies on, and
     *      connect these.
     */
    public PixelsFighting(){
        
        Pixel.leftPixels=NUMBER_OF_PIXELS=FRAME_SIDE_LENGTH/PIXEL_SIDE;
        Pixel.setWidth(PIXEL_SIDE);
        
        //Output/display setup::
        this.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(FRAME_SIDE_LENGTH,FRAME_SIDE_LENGTH);
        
        
        
        
        
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
    
    /**
     * The function that draws the visual aspects of the program. 
     * 
     * @param g the java.awt.Graphics instance used to draw the visual
     *      representation of the program. 
     */
    @Override
    public void paintComponent(Graphics g){
        boolean thisPixelColor;
        double disadvantageMultiplier;
        double multiplierLeft =Pixel.leftPixels/NUMBER_OF_PIXELS,
               multiplierRight=(NUMBER_OF_PIXELS-Pixel.leftPixels)
                                    /NUMBER_OF_PIXELS;
        
        int x=0;
        
        dx*=-1;
        
        if(dx==1){
            x=0;
        } else if(dx==-1){
            x=NUMBER_OF_PIXELS-1;
        } else{
            System.err.println("dx at unexpected value " + dx);
        }
        
        //Calculate::
        for(;x<NUMBER_OF_PIXELS&&x>0;x+=dx){
            for(int y=0;y<NUMBER_OF_PIXELS;y+=1){
                
                //Calculate for the next frame::
                thisPixelColor=pixels[x][y].getSide();
                
                if(thisPixelColor){
                    disadvantageMultiplier=multiplierLeft;
                }else{
                    disadvantageMultiplier=multiplierRight;
                }
                
                switch((int)(Math.random()*(4+disadvantageMultiplier))){
                    case 0:
                        if(y>0)
                            pixels[x][y-1].setSide(thisPixelColor);
                        break;
                    case 1:
                        if(x>0)
                            pixels[x-1][y].setSide(thisPixelColor);
                        break;
                    case 2:
                        if(x<NUMBER_OF_PIXELS-1)
                            pixels[x+1][y].setSide(thisPixelColor);
                        break;
                    case 3:
                        if(y<NUMBER_OF_PIXELS-1)
                            pixels[x][y+1].setSide(thisPixelColor);
                        break;
                }
                
//                System.out.println(thisPixelColor);
                
//                if(y>0)
//                    pixels[x][y-1].setSide(thisPixelColor);
//                if(x>0)
//                    pixels[x-1][y].setSide(thisPixelColor);
//                if(x<NUMBER_OF_PIXELS-1)
//                    pixels[x+1][y].setSide(thisPixelColor);
//                if(y<NUMBER_OF_PIXELS-1)
//                    pixels[x][y+1].setSide(thisPixelColor);
            }
        }
        
        //Draw::
        for(x=0;x<NUMBER_OF_PIXELS;x++){
            for(int y=0;y<NUMBER_OF_PIXELS;y++){
                //Draw for the current frame being worked::
                pixels[x][y].draw(g);
            }
        }
        
        try{
            Thread.sleep(FRAME_REFRESH_RATE);
        } catch(InterruptedException e){
            System.err.println("Error sleeping Thread :: " + e);
        }
        
        this.repaint();
    }
    
}
