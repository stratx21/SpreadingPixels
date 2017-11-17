/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelsfighting;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class Pixel{
    public static int leftPixels;
    
    /**
     * The identifier for the team this Pixel instance is on.
    */
    private boolean leftSide=true;
    
    /**
     * The coordinate values of this Pixel instance. 
     */
    private int x=0,y=0;
    
    /**
     * The width and height (in pixels) of this Pixel instance (this is the
     *      same for all Pixel objects).
     */
    private static int width=1;
    
    private static final Color left  = new Color(26, 102,46);
    private static final Color right = new Color(212,175,55);
    
    public Pixel(int xnew,int ynew,boolean left){
        x=xnew;
        y=ynew;
        leftSide=left;
    }
    
    public void draw(Graphics g){
        if(leftSide)
            g.setColor(left);
        else
            g.setColor(right);
        
        g.fillRect(x, y, width, width);
    }
    
    /**
     * This function returns a boolean value concerning if this Pixel
     *      instance is on the left side team. 
     * 
     * @return if this Pixel is on the left side team.
     */
    public boolean getSide(){
        return leftSide;
    }
    
    public void setSide(boolean leftNew){
        if(leftNew!=leftSide){
            if(leftNew){
                leftPixels++;
            }else{
                leftPixels--;
            }
            leftSide=leftNew;
        }
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int n){
        x=n;
    }
    
    public void setY(int n){
        y=n;
    }
    
    public static void setWidth(int w){
        width=w;
    }
}
