/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;

/**
 *
 * @author mariorl
 */
public class Lapiz {
    private Color color;
    
    public Lapiz(Color color){
        this.color = color;
    }
    
    public String toString(){
        return "Soy un lapiz " + color;
    }
}
