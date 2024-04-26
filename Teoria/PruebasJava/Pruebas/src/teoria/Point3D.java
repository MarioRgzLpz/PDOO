/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoria;

/**
 *
 * @author mariorl
 */
public class Point3D {
    private static int MIN = 0;
    private static int MAX = 100;
    private int x;
    private int y;
    private int z;

    public Point3D (int x, int y, int z){
        this.x = restrict(x);
        this.y = restrict(y);
        this.z = restrict(z);
    }
    
    public Point3D (int x, int y){
        this(x,y,0);
    }
    public Point3D (int x){
        this(x,0,0);
    }
    public Point3D (){
        this(0,0,0);
    }
    
    private int restrict(int a){
        int result = Math.max(MIN, a);
        result = Math.min(result, MAX);
        return result;
    }
    
    public void imprimir(){
        System.out.println("X: " + x + ",Y: " + y + ",Z: " + z);
    }
    
    public static void main (String []args){
        Point3D p1 = new Point3D();
        Point3D p2 = new Point3D(10);
        Point3D p3 = new Point3D(10,30);
        Point3D p4 = new Point3D(110, 20,-7);
        
        p1.imprimir();
        p2.imprimir();
        p3.imprimir();
        p4.imprimir();
    }
}
