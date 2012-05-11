/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.primefaces.component.tree.Tree;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Alberto
 */
public class TreeNodeAccion extends DefaultTreeNode{
    
    private String action;
    
    public TreeNodeAccion(Object data , TreeNode parent , String action){
        super(data,parent);
        this.action=action;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
}
