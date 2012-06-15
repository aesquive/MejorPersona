/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alberto
 */
public class Vector<T0 ,T1>{
    
    private T0 x;
    private T1 y;
    
    public Vector(T0 x , T1 y){
        this.x=x;
        this.y=y;
    }

    /**
     * @return the x
     */
    public T0 getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(T0 x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public T1 getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(T1 y) {
        this.y = y;
    }
    
}
