package agents;

import statistics.*;
/**
 * Klasa definiująca złodzieja.
 * 
 * @author Piotrek
 */
public class Thief extends Agent {
    
    /**
     * Ilość dotychczasowych pobytów w więzieniu.
     * @var int
     */
    protected int _numberOfStaysInJail;
    
    /**
     * Czy jest w więzeniu?
     * @var boolean
     */
    protected boolean _isInJail;
    
    /**
     * Ilość iteracji jakie siedzi w więzieniu.
     * @var int
     */
    protected int _numberOfIterationsInJail;
    
    /**
     * Wielkość zdobytego aktualnie łupu.
     * @var int
     */
    protected int _booty;
    
    /**
     * Statystyki złodzieja.
     * @var ThiefStatistics_Interface
     */
    protected ThiefStatistics_Interface _statitics;
    
    /**
     * Konstruktor złodzieja.
     */
    public void Thief() {
        super.Agent();
        
        this.setNumberOfStaysInJail(0);
        this.setIsInJail(false);
        this.setNumberOfIterationsInJail(0);
        this.setBooty(0);
    }
    
    /**
     * Getter dla liczby dotychczazsowych pobytów w więzieniu.
     * @return int
     */
    public int getNumberOfStaysInJail() {
        return this._numberOfStaysInJail;
    }
    
    /**
     * Setter dla liczby dotychczazsowych pobytów w więzieniu.
     * @param int numberOfStaysInJail
     * @return Thief
     */
    public Thief setNumberOfStaysInJail(int numberOfStaysInJail) {
        this._numberOfStaysInJail = numberOfStaysInJail;
        
        return this;
    }
    
    /**
     * Getter dla flagi czy jest w więzeniu.
     * @return boolean
     */
    public boolean getIsInJail() {
        return this._isInJail;
    }
    
    /**
     * Setter dla flagi czy jest w więzeniu.
     * @param boolean isInJail
     * @return Thief
     */
    public Thief setIsInJail(boolean isInJail) {
        this._isInJail = isInJail;
        
        return this;
    }
    
    /**
     * Getter dla ilości iteracji pozostałych w więzieniu.
     * @return int
     */
    public int getNumberOfIterationsInJail() {
        return this._numberOfIterationsInJail;
    }
    
    /**
     * Setter dla ilości iteracji pozostałych w więzieniu.
     * @param int numberOfIterationsInJail
     * @return Thief
     */
    public Thief setNumberOfIterationsInJail(int numberOfIterationsInJail) {
        if(numberOfIterationsInJail == 0) {
            this.setIsInJail(false);
            this.setNumberOfStaysInJail(this.getNumberOfStaysInJail()+1);
        }
        this._numberOfIterationsInJail = numberOfIterationsInJail;
        
        return this;
    }
    
    /**
     * Getter dla ilości aktualnego łupu.
     * @return int
     */
    public int getBooty() {
        return this._booty;
    }
    
    /**
     * Setter dla ilości aktualnego łupu.
     * @param int booty
     * @return Thief
     */
    public Thief setBooty(int booty) {
        this._booty = booty;
        //Zwiększenie całkowitego łupu.
        this.getStatistics().setTotalBooty(this.getStatistics().getTotalBooty() + this.getBooty());
        this.setGold(this.getGold() + this.getBooty());
        
        return this;
    }
    
    /**
     * Przeciążenie settera dla wielkości mieszka uwzględniając zapis do statystyk.
     * @param int gold
     * @return Thief
     */
    @Override
    public Thief setGold(int gold) {
        super.setGold(gold);
        this.getStatistics().setProfit(this.getGold());
        
        return this;
    }
    
    /**
     * Przeciążenie metody dotyczącej przemieszania się uwzględniając pobyt w więzeniu.
     * @return boolean
     */
    @Override
    public boolean run() {
        if(this.getIsInJail()) {
            return false;
        }
        return super.run();
    }
    
    /**
     * Przeciążenie metody dotyczącej odpoczynku. Podczas pobytu w więzieniu traci energie.
     */
    @Override
    public void recover() {
        if(this.getIsInJail()) {
            this.setEnergy(this.getEnergy()-1);
            this.setNumberOfIterationsInJail(this.getNumberOfIterationsInJail()-1);
        }
        else {
            super.recover(); 
        }
    }
    
    /**
     * Metoda odpowiedzialna za pójscie do więzienia, w której obliczamy ilość dni do odsiadki,
     * zerujemy jego aktualny łup.
     */
    public void goToJail() {
        this.setIsInJail(true);
        //Ustalenie długości pobytu w więzieniu na podstawie wzoru: wielkość łupu przy sobie * 0.1 + ilość dotychczasowych pobytów w więzieniu.
        this.setNumberOfIterationsInJail(Math.round(this.getBooty() * 0.1f) + this.getNumberOfStaysInJail());
        //Zmiejszenie mieszka o ilość przechwyconego przez straż.
        this.setGold(this.getGold() - this.getBooty());
        //Wyzerowanie aktualnego łupu.
        this.setBooty(0);
    }
    
    /**
     * Setter dla statystyk złodzieja.
     * @param ThiefStatistics_Interface statistics
     * @return Thief
     */
    public Thief setStatistics(ThiefStatistics_Interface statistics) {
        this._statitics = statistics;
        
        return this;
    }
    
    /**
     * Getter dla statystyk złodzieja.
     * @return ThiefStatistics_Interface
     */
    public ThiefStatistics_Interface getStatistics() {
        return this._statitics;
    }
}