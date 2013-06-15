package agents;

import items.Pack;

import java.util.ArrayList;
import java.util.Arrays;

import polskaad1340.window.OknoMapy;
import statistics.CourierStatistics_Interface;
import world.MapFrame;
import CLIPSJNI.PrimitiveValue;

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
    protected int capacity;
    
    /**
     * Lista paczek.
     * @var ArrayList<Pack>
     */
    protected ArrayList<String> packages;
    
    /**
     * Statystyki posłańca.
     * @var CourierStatistics_Interface
     */
    protected CourierStatistics_Interface statistics;
    
    /**
     * Koń.
     * @var Horse
     */
    protected String horse;
    
    public Courier() {
    	this.mapFrame = new MapFrame();
    	this.packages = new ArrayList<String>();
    }
    
    /**
     * Konstruktor. Nadanie domyślnego udźwigu.
     * @param capacity 
     */
    public Courier(String id, String pathToClipsFile, CourierStatistics_Interface stat, MapFrame mapFrame, OknoMapy om) {
        super(id, pathToClipsFile);

        this.capacity = 5;
        this.packages = new ArrayList<String>();
        this.setHorse(null);
        this.statistics = stat;
        this.mapFrame = mapFrame;
        this.velocity = 1;
        this.fieldOfView = 1;
        this.possibleMove = 1;
        this.opp = om.nowyObiektPierwszegoPlanu(mapFrame.getX(), mapFrame.getY(), 1088);
    }
    
	public void loadFromClips(PrimitiveValue pv) {
		try {
			this.velocity = pv.getFactSlot("predkosc").intValue();
			this.extraVelocity = pv.getFactSlot("dodatekPredkosc").intValue();
			this.energy = pv.getFactSlot("energia").intValue();
			this.energyLoss = pv.getFactSlot("strataEnergii").intValue();
			this.energyRecovery = pv.getFactSlot("odnawianieEnergii").intValue();
			this.gold = pv.getFactSlot("zloto").intValue();
			this.fieldOfView = pv.getFactSlot("poleWidzenia").intValue();
			this.capacity = pv.getFactSlot("udzwig").intValue();
			this.horse = pv.getFactSlot("kon").toString();
			
			String packsTmp = pv.getFactSlot("paczki").toString();
			String[] packs = packsTmp.replace("(", "").replace(")", "").split(" ");
			this.packages = new ArrayList<String>(Arrays.asList(packs));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    public int getCapacity() {
        return this.capacity;
    }
    
    public Courier setCapacity(int capacity) {
        this.capacity = capacity;
        
        return this;
    }
    
    /**
     * Dostarczanie przesyłki.
     * @param Pack pack
     * @return Courier
     * @TODO Obsługa przypadku, w którym próbujemy dostarczyć przesyłkę, której nie ma przy sobie.
     */
	public Courier deliveryPackage(Pack pack) {
		// Zwiększenie wielkości mieszka.
		this.setGold(this.getGold() + pack.getPrice());
		// Zwiększenie przychodu.
		this.getStatistics().setIncome(this.getStatistics().getIncome() + pack.getPrice());
		// Zwiększenie zysku.
		this.getStatistics().setProfit(this.getStatistics().getProfit() + pack.getPrice());
		// Zwiększenie liczby dostarczonych przesyłek.
		this.getStatistics().setNumberOfDeliveriedPacks(this.getStatistics().getNumberOfDeliveriedPacks() + 1);
		// Zwiększenie całkowitego czasy dostawy.
		this.getStatistics().setTotalDeliveryTime(this.getStatistics().getTotalDeliveryTime() + pack.getDeliveryTime());

		return this;
	}
    
    
    /**
     * Setter dla statystyk posłańca.
     * @param CourierStatistics_Interface statistics
     * @return Courier
     */
    public Courier setStatistics(CourierStatistics_Interface statistics) {
        this.statistics = statistics;
        
        return this;
    }
    
    /**
     * Getter dla statystyk posłańca.
     * @return CourierStatistics_Interface
     */
    public CourierStatistics_Interface getStatistics() {
        return this.statistics;
    }
    

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("(poslaniec (udzwig ");
		buffer.append(this.capacity);
		buffer.append(")"); 
		if (!this.packages.isEmpty()) {
			buffer.append("(paczki ");
			for (int i = 0; i < this.packages.size(); i++) {
				buffer.append(this.packages.get(i));
				
				if (i < this.packages.size() - 1) {
					buffer.append(" ");
				}
			}
			buffer.append(")");
		}
		if (horse != null) {
			buffer.append(" (kon ");
			buffer.append(this.horse).append(")");
		}
		buffer.append(" (id ");
		buffer.append(this.id);
		buffer.append(") (poleWidzenia ");
		buffer.append(this.fieldOfView);
		buffer.append(") (predkosc ");
		buffer.append(this.velocity);
		buffer.append(") (dodatekPredkosc ");
		buffer.append(this.extraVelocity);
		buffer.append(") (energia ");
		buffer.append(this.energy);
		buffer.append(") (strataEnergii ");
		buffer.append(this.energyLoss);
		buffer.append(") (odnawianieEnergii ");
		buffer.append(this.energyRecovery);
		buffer.append(") (zloto ");
		buffer.append(this.gold);
		buffer.append(") (mozliwyRuch ");
		buffer.append(this.possibleMove);
		buffer.append(") (idKratki ");
		buffer.append(this.mapFrame.getId());
		buffer.append("))");
		return buffer.toString();
	}

	public String getHorse() {
		return horse;
	}

	public void setHorse(String horse) {
		this.horse = horse;
	}

	public ArrayList<String> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<String> packages) {
		this.packages = packages;
	}

}
