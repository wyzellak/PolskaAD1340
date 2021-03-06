package agents;

import items.Ax;
import items.Item;
import items.Vehicle;
import items.Wood;

import java.util.ArrayList;
import java.util.Arrays;

import polskaad1340.window.OknoMapy;
import statistics.WoodmanStatistics_Interface;
import world.MapFrame;
import CLIPSJNI.PrimitiveValue;
import clips.ClipsEnvironment;
/**
 * Klasa definiująca drwala.
 * 
 * @author Piotrek
 */
public class Woodman extends Agent {
    
    /**
     * Udźwig drwala.
     * @var int
     */
    private int capacity;
    private int maxCapacity;
    /**
     * Posiadane siekiera.
     * @var Ax
     */
    private String ax;
    
    /**
     * Posiadany wóz.
     * @var Vehicle
     */
    private String vehicle;
    
    /**
     * Lista posiadanych przy sobie drzew.
     * @var ArrayList<Wood>
     */
    private ArrayList<String> woods;
    private int allWoods;
    
    public int getAllWoods() {
		return allWoods;
	}

	public void setAllWoods(int allWoods) {
		this.allWoods = allWoods;
	}
	
    /**
     * Statystyki drwala.
     * @var WoodmanStatistics_Interface
     */
    private WoodmanStatistics_Interface statistics;
    
    public Woodman() {
    	this.mapFrame = new MapFrame();
    	this.woods = new ArrayList<String>();
		this.woods.add("");
    }
    
    public Woodman(String id, String pathToClipsFile, String clipsResultFile, WoodmanStatistics_Interface stat, MapFrame mapFrame, OknoMapy om) {
        super(id, pathToClipsFile, clipsResultFile);
        this.statistics = stat;
        this.ax = null;
        this.vehicle = null;
        this.woods = new ArrayList<String>();
        this.mapFrame = mapFrame;
        this.capacity = 10;
        this.maxCapacity = 100;
        this.fieldOfView = 1;
        this.possibleMove = 1;
        this.velocity = 1;
        this.opp = om.nowyObiektPierwszegoPlanu(mapFrame.getX(), mapFrame.getY(), this.id, 1662);
    }
    
    public void loadFromClips(PrimitiveValue pv) {
		try {
			this.id = pv.getFactSlot("id").toString();
			this.capacity = pv.getFactSlot("udzwig").intValue();
			this.maxCapacity = pv.getFactSlot("maxUdzwig").intValue();
			this.ax = !pv.getFactSlot("siekiera").toString().equalsIgnoreCase("nil")? pv.getFactSlot("siekiera").toString() : null;
			this.vehicle = !pv.getFactSlot("woz").toString().equalsIgnoreCase("nil")? pv.getFactSlot("woz").toString() : null;
			this.possibleMove = pv.getFactSlot("mozliwyRuch").intValue();
			this.allWoods =pv.getFactSlot("drewnoOgolem").intValue();
			
			String woodsTmp = pv.getFactSlot("scieteDrewno").toString();
			String[] woods = woodsTmp.replace("(", "").replace(")", "").split(" ");
			this.woods = new ArrayList<String>(Arrays.asList(woods));

			this.mapFrame.setId(pv.getFactSlot("idKratki").intValue());
			this.fieldOfView = pv.getFactSlot("poleWidzenia").intValue();
			this.velocity = pv.getFactSlot("predkosc").intValue();
			this.extraVelocity = pv.getFactSlot("dodatekPredkosc").intValue();
			this.energy = pv.getFactSlot("energia").intValue();
			this.energyLoss = pv.getFactSlot("strataEnergii").intValue();
			this.energyRecovery = pv.getFactSlot("odnawianieEnergii").intValue();
			this.gold = pv.getFactSlot("zloto").intValue();
			this.target = !pv.getFactSlot("cel").toString().equalsIgnoreCase("nil")? pv.getFactSlot("cel").toString() : null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    public ArrayList<Item> findItems(ClipsEnvironment clipsEnv) {
    	ArrayList<Item> foundItems = new ArrayList<Item>();
    	
    	if (this.ax != null) {
			try {
				String evalString = "(find-all-facts ((?s siekiera))(eq ?s:id " + this.ax + "))";
				PrimitiveValue pv1 = clipsEnv.getWorldEnv().eval(evalString);
				Ax axTmp = new Ax(pv1.get(0));
				foundItems.add(axTmp);
			} catch (Exception e) {

			}
		}

		if (this.vehicle != null) {
			try {
				String evalString = "(find-all-facts ((?w woz))(eq ?w:id " + this.vehicle + "))";
				PrimitiveValue pv1 = clipsEnv.getWorldEnv().eval(evalString);
				Vehicle vehicleTmp = new Vehicle(pv1.get(0));
				foundItems.add(vehicleTmp);
			} catch (Exception e) {

			}
		}
		//drewno
		if(!woods.isEmpty()){
		for (String woodId:woods){
			if ( !woodId.equals("")){
			try {
				String evalString = "(find-all-facts ((?w drewno))(eq ?w:id " + woodId + "))";
				PrimitiveValue pv1 = clipsEnv.getWorldEnv().eval(evalString);
				Wood vehicleTmp = new Wood(pv1.get(0));
				foundItems.add(vehicleTmp);
			} catch (Exception e) {

			}
			}
		}
		}
	
		return foundItems;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    public Woodman setCapacity(int capacity) {
        this.capacity = capacity;
        
        return this;
    }
    
    public ArrayList<String> getWoods() {
        return this.woods;
    }
    
    /**
     * Przeciążenie metody ustawiającej wielkość mieszka uwzględniając zapis do statystyk.
     * @param int gold
     * @return Woodman
     */
    @Override
    public Woodman setGold(int gold) {
        super.setGold(gold);
        this.getStatistics().setProfit(this.getGold());
        
        return this;
    }
    
    public Woodman setStatistics(WoodmanStatistics_Interface statistics) {
        this.statistics = statistics;
        
        return this;
    }
    
    public WoodmanStatistics_Interface getStatistics() {
        return this.statistics;
    }

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("(drwal ( udzwig ");
		buffer.append(this.capacity);
		buffer.append(" ) ");
		
		buffer.append("( maxUdzwig ");
		buffer.append(this.maxCapacity);
		buffer.append(" ) ");
		
		if (ax != null) {
			buffer.append("( siekiera ");
			buffer.append(this.ax);
			buffer.append(" ) ");
		}
		if (vehicle != null) {
			buffer.append("( woz ");
			buffer.append(this.vehicle);
			buffer.append(" ) ");
		}
		buffer.append("( scieteDrewno ");
		for (int i = 0; i < this.woods.size(); i++) {
			buffer.append(this.woods.get(i)).append(" ");
		}
		buffer.append(") ( id ");
		buffer.append(this.id);
		buffer.append(" ) ( mozliwyRuch ");
		buffer.append(this.possibleMove);
		buffer.append(" ) ( idKratki ");
		buffer.append(this.mapFrame.getId());
		buffer.append(" ) ( poleWidzenia ");
		buffer.append(this.fieldOfView);
		buffer.append(" ) ( predkosc ");
		buffer.append(this.velocity);
		buffer.append(" ) ( dodatekPredkosc ");
		buffer.append(this.extraVelocity);
		buffer.append(" ) ( energia ");
		buffer.append(this.energy);
		buffer.append(" ) ( strataEnergii ");
		buffer.append(this.energyLoss);
		buffer.append(" ) ( odnawianieEnergii ");
		buffer.append(this.energyRecovery);
		buffer.append(" ) ( drewnoOgolem ");
		buffer.append(this.allWoods);
		buffer.append(" ) ( zloto ");
		buffer.append(this.gold);
		if (this.target != null) {
			buffer.append(" ) ( cel ");
			buffer.append(this.target);
		}
		
		buffer.append(" ) )");
		return buffer.toString();
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getAx() {
		return ax;
	}

	public void setAx(String ax) {
		this.ax = ax;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public void setWoods(ArrayList<String> woods) {
		this.woods = woods;
	}
}
