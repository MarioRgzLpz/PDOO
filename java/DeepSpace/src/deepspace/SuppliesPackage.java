/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;

/**
 *
 * @author mariorgzlpz
 */
public class SuppliesPackage {
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;
    
    SuppliesPackage(float ap,float fu,float sp){
        ammoPower=ap;
        fuelUnits=fu;
        shieldPower=sp;
    }
    
    SuppliesPackage(SuppliesPackage s){
        this(s.ammoPower, s.fuelUnits, s.shieldPower);
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    
    
}
