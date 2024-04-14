/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;

/**
 *
 * @author mariorl
 */
public class This {
    private static String JEFE="Jefe";
    private String nombre;
    
    public This (String nombre){
        this.nombre = nombre; // Modifica el atributo de instancia nombre. Se suele usar de esta manera en el constructor
    }
    
    public This () { //Constructor sin parametros
        this("Anónimo"); // Llamada al contructor con parametros pasandole el nombre anónimo
    }
    
    //Desde ambito de clase no se puede usar this
    public static void declase(){
        // System.out.println(this.nombre); //non-static variable this cannot be referenced from a static context
        System.out.println(JEFE);
    }
    
    public void nombre(){
        System.out.println(this.nombre);
    }
}
