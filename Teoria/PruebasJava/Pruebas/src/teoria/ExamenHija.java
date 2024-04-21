/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;

/**
 *
 * @author mariorl
 */
public class ExamenHija extends Examen{
    private float c;

    public ExamenHija(int a, int b,int c) {
        super(a, b);
        this.c = c;
    }
    
    public float filtraA(){return super.filtraA() -1;}
    public float filtraA(int b){return super.filtraA() + b;}
    
}
