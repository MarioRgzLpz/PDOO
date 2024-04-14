/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;

/**
 *
 * @author mariorl
 */
public class UnaClase {    
    //Desde ambito de clase no se puede usar this
    public void metodoInstanciaPublico(){
        System.out.println("De instancia Publico");
    }
    
    private void metodoInstanciaPrivado(){
        System.out.println("De instancia Privado");
    } 
    
    public static void metodoClasePublico(){
        System.out.println("De instancia Publico");
    }
    
    private static void metodoClasePrivado(){
        System.out.println("De instancia Privado");
    } 
    
    public void usoDentroClase(){
        metodoInstanciaPublico();
        this.metodoInstanciaPublico();
        metodoInstanciaPrivado();
        this.metodoInstanciaPrivado();
        UnaClase.metodoClasePrivado();
        UnaClase.metodoClasePublico();
    }
    
    public static void main(String []args) {
        
    }
}
