package agents;

import java.util.ArrayList;
import items.*;

/**
 * Klasa definiująca posłańca.
 * 
 * @author Piotrek
 */
public class Courier extends Agent {
    
    /**
     * Udźwig.
     * @var int
     */
    protected int _capacity;
    
    /**
     * Lista paczek.
     * @var ArrayList<Pack>
     */
    protected PackCollection _packages;
    
    /**
     * Koń.
     * @var Horse
     */
    protected Horse _horse;
    
    /**
     * Konstruktor. Nadanie domyślnego udźwigu.
     * @param capacity 
     */
    public void Courier(int capacity) {
        super.Agent();
        
        this.setCapacity(capacity);
        this._packages = new PackCollection();
        this.setHorse(null);
    }
    
    /**
     * Getter dla udźwigu.
     * @return int
     */
    public int getCapacity() {
        if(this.getHorse() != null) {
            return this.getHorse().getCapacity();
        }
        return this._capacity;
    }
    
    /**
     * Setter dla udźwigu.
     * @param int capacity
     * @return Courier
     */
    public Courier setCapacity(int capacity) {
        this._capacity = capacity;
        
        return this;
    }
    
    /**
     * Getter dla listy paczek.
     * @return ArrayList<Pack> 
     */
    public ArrayList<Pack> getPackages() {
        return this._packages.getAll();
    }
    
    /**
     * Dodawanie paczki.
     * @param Pack pack
     * @return Courier
     * @TODO Obsługa przypadku, w którym nie można wziąć więcej paczek.
     */
    public Courier addPackage(Pack pack) {
        //Sprawdzenie czy można wziąć kolejną paczkę.
        if(this._packages.getTotalWeight()+pack.getMass() <= this.getCapacity()) {
            this._packages.addPackage(pack);
        }
        
        return this;
    }
    
    /**
     * Usuwanie paczki.
     * @param Pack pack
     * @return Courier
     */
    public Courier removePackage(Pack pack) {
        this._packages.removePackage(pack);
        
        return this;
    }
    
    /**
     * Getter dla konia.
     * @return Horse
     */
    public Horse getHorse() {
        return this._horse;
    }
    
    /**
     * Setter dla konia.
     * @param Horse horse
     * @return Courier
     */
    public Courier setHorse(Horse horse) {
        this._horse = horse;
        
        return this;
    }
    
    /**
     * Zakup konia. Zwraca TRUE jeżeli ma wystarczającą ilość golda, w przeciwnym wypadku FALSE.
     * @param horse
     * @return boolean
     */
    public boolean buyHorse(Horse horse) {
        if(this.getGold() < horse.getPrice()) {
            return false;
        }
        this.setGold(this.getGold() - horse.getPrice());
        this.setHorse(horse);
        
        return true;
    }
    
    /**
     * Przeciążenie getter dla zużycia energii uwzględniając obciążenie i posiadanego konia.
     * @return int;
     */
    @Override
    public int getEnergyLoss() {
        int energyLoss = super.getEnergyLoss() + (int)Math.round(this._packages.getTotalWeight() * 0.5);
        
        if(this.getHorse() != null) {
            energyLoss = Math.round(energyLoss * this.getHorse().getAgentEnergyLossDecrease());
        }
        
        return energyLoss;
    }
    
    /**
     * Przeciążenie gettera dla prędkości posłańca.
     * @return int
     */
    @Override
    public int getVelocity() {
        if(this.getHorse() != null) {
            return this.getHorse().getVelocity();
        }
        return super.getVelocity();
    }
}
