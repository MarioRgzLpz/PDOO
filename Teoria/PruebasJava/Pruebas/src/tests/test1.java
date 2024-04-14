/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;
import teoria.*;
import java.time.LocalDate;

/**
 *
 * @author mariorl
 */
public class test1 {
    public static void main(String[] args){
        
        Lapiz miLapiz = new Lapiz (Color.ROJO);
        Lapiz tuLapiz = new Lapiz (Color.VERDE);
        LocalDate nacimiento1 = LocalDate.of(2004, 4, 14);
        LocalDate nacimiento2 = LocalDate.of(2014, 4, 14);
        Persona persona1 = new Persona ("Mario", 20, ColorPelo.MORENO, nacimiento1);
        
        System.out.println(miLapiz.toString());
        System.out.println(tuLapiz.toString());
        Persona.getNumPersonas();
        persona1.saluda(); //Por defecto tiene visibilidad paquete por lo que hay que ponerle publica
        System.out.println("Mayor edad: " + persona1.mayorDeEdad());
        persona1.cambiaNombre("Juan");
        persona1.saluda();
        System.out.println("Creo otra persona");
        Persona persona2 = new Persona ("Pepe", 10, ColorPelo.RUBIO, nacimiento2);
        persona2.saluda();
        System.out.println("Mayor edad: " + persona2.mayorDeEdad());
        Persona.getNumPersonas();
        
        This pruebaanonimo = new This();
        This prueba = new This("Pepe");
        
        This.declase();
        pruebaanonimo.nombre();
        prueba.nombre();
    }
}
