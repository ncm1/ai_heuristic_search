/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author seo
 */
abstract class NodeCost {
    double f;
    double g;
    double h;
    
    public double getCost(){
        return f;
    };
    public void setCost(double f){
        this.f =f;
    };
    
    abstract public double calculateCost();
    
    public void calculateAndSetCost(){
        setCost(calculateCost());
    };
    
}
