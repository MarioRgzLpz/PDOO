/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deepspace;

/**
 *
 * @author mariorgzlpz
 */
public class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;

    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
    }
    
    public int getNHangars() {
        return nHangars;
    }

    public int getNMedals() {
        return nMedals;
    }

    public int getNShields() {
        return nShields;
    }

    public int getNSupplies() {
        return nSupplies;
    }

    public int getNWeapons() {
        return nWeapons;
    }
    
}
