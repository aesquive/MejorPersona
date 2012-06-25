/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author alberto
 */
public class Vector{
    
    private int id;
    private String x;
    private String y;
    
    public Vector(int id ,String x , String y){
        this.id=id;
        this.x=x;
        this.y=y;
    }

    /**
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(String y) {
        this.y = y;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
