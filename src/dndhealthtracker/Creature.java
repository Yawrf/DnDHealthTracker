/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dndhealthtracker;

import java.io.Serializable;

/**
 *
 * @author rewil
 */
public class Creature implements Serializable{
    
    private final String name;
    private final int b;
    private final int p;
    private final int s;
    private final int acid;
    private final int cold;
    private final int fire;
    private final int lightning;
    private final int thunder;
    private final int poison;
    private final int force;
    private final int psychic;
    private final int radiant;
    private final int necrotic;
    private final int maxHealth;
    private int currentHealth;
    private int tempHealth = 0;
    
    public Creature (String name, int health, int bludgeoning, int piercing, int slashing, int acid, int cold, int fire, int lightning, int thunder, int poison, int force, int psychic, int radiant, int necrotic) {
        this.name = name;
        maxHealth = health;
        currentHealth = health;
        b = bludgeoning;
        p = piercing;
        s = slashing;
        this.acid = acid;
        this.cold = cold;
        this.fire = fire;
        this.lightning = lightning;
        this.thunder = thunder;
        this.poison = poison;
        this.force = force;
        this.psychic = psychic;
        this.radiant = radiant;
        this.necrotic = necrotic;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Positive value heals, negative harms
     * @param value 
     */
    public void modifyCurrentHealth(int value) {
        if(value > 0 || tempHealth == 0) {
            currentHealth += value;
        } else {
            tempHealth += value;
            if(tempHealth < 0) {
                currentHealth += tempHealth;
                tempHealth = 0;
            }
        }
        if(currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void addTempHealth(int value) {
        tempHealth += value;
    }
    
    public int getTempHealth() {
        return tempHealth;
    }
    
    public int getB() {
        return b;
    }

    public int getP() {
        return p;
    }

    public int getS() {
        return s;
    }

    public int getAcid() {
        return acid;
    }

    public int getCold() {
        return cold;
    }

    public int getFire() {
        return fire;
    }

    public int getLightning() {
        return lightning;
    }

    public int getThunder() {
        return thunder;
    }

    public int getPoison() {
        return poison;
    }

    public int getForce() {
        return force;
    }

    public int getPsychic() {
        return psychic;
    }

    public int getRadiant() {
        return radiant;
    }

    public int getNecrotic() {
        return necrotic;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
