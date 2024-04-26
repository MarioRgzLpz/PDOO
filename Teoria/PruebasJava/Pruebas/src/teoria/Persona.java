/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author mariorl
 */
public class Persona {
    //Atributo de clase
    private static int numPersonas = 0; //Se suele poner en mayusculas
    private static final int MAYORIAEDAD=18;
    //Atributos privados
    private String nombre;
    private int edad;
    private ColorPelo pelo;
    private LocalDate fechaNacimiento;
       
    public Persona(String nombre, int edad, ColorPelo pelo, LocalDate fecha){ //Constructor
        this.nombre = nombre;
        this.edad = edad;
        this.pelo = pelo;
        this.fechaNacimiento = fecha;
        numPersonas++;
    }
    
    public void saluda() { // Si no ponemos nada tiene visibilidad paquete, lo modificamos para poder llamarlo
        System.out.println("Hola, soy " + nombre);
    }
    
    public void cambiaNombre(String otroNombre){
        nombre = otroNombre;
    }
    public static void getNumPersonas() {
        System.out.println(numPersonas);
    }
    
    public boolean mayorDeEdad(){
        LocalDate ahora = LocalDate.now();
        
        long edad = ChronoUnit.YEARS.between(fechaNacimiento, ahora);
        
        return (edad >= MAYORIAEDAD);
    }
            
}
