/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polskaad1340;

import java.util.logging.Level;
import java.util.logging.Logger;

import world.Blockade;
import world.Cataclysm;
import world.MapFrame;
import world.Package;
import world.Road;
import world.Town;
import world.Tree;
import CLIPSJNI.PrimitiveValue;
import clips.ClipsEnvironment;

/**
 *
 * @author Kuba
 */
public class PolskaAD1340 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        //bugfix, patrz http://stackoverflow.com/questions/13575224/comparison-method-violates-its-general-contract-timsort-and-gridlayout

        // TODO code application logic here
 //       OknoMapy om = new OknoMapy();

        try {
//            LadowanieMapy lm = new LadowanieMapy("/maps/example.json");
//            om.importBackgroundTileGrid(lm.getMap());
//
//            om.setForegroundTileGrid(om.createTileGrid(lm.getMapSize(), 0));
//            om.drawAllTiles();
//
//
//            //test
//            om.getForegroundTileGrid().get(0).set(0, om.tileFromNumber(1895));
//
//            ObiektPierwszegoPlanu opp = new ObiektPierwszegoPlanu(0, 0);
//            om.addObjectToForegroundList(opp);
//           // opp.move(20, 20);
//
//            om.drawAllTiles();

            ClipsEnvironment clipsEnv = new ClipsEnvironment();

            String evalString = "(find-all-facts ((?k drzewo)) TRUE)";
            PrimitiveValue pv1 = clipsEnv.getWorldEnv().eval(evalString);
            for (int i = 0; i < pv1.size(); i++) {
//                System.out.print("KRATKA ");
//                System.out.print(" id: " + pv1.get(i).getFactSlot("id"));
//                System.out.print(", X: " + pv1.get(i).getFactSlot("pozycjaX"));
//                System.out.println(", Y: " + pv1.get(i).getFactSlot("pozycjaY"));
            	Tree a = new Tree();
            	a.loadFromClips(pv1.get(i));
            	System.out.println(a);
            }

/*            CourierStatistics cs = new CourierStatistics();
            Courier agent = new Courier("poslaniec1", 10, cs);
            agent.setCapacity(500);
            agent.addPackage(new Pack("Pack1", 10));
            agent.addPackage(new Pack("Pack2", 10));*/
            
           /* MerchantStatistics ms = new MerchantStatistics();
            Merchant agent = new Merchant("kupiec1", 100, ms);*/
          /*  ThiefStatistics ts = new ThiefStatistics();
            Thief agent = new Thief("Thief1", ts);*/
    /*        WoodmanStatistics ws = new WoodmanStatistics();
            Woodman agent = new Woodman("Woodman1", 100, ws);
            agent.setGold(500);
            agent.buyAx(new Ax("Ax1", 10, 10, 10));
            agent.buyVehicle(new Vehicle("v1", 10, 10, 10));*/
          
 
//            
//            Bandits bandits1 = new Bandits(0.5f, 0.5f, 1);
//            Blockade blockade1 = new Blockade(1, 1);
//            Cataclysm cataclysm1 = new Cataclysm(1, 1, 0.5f, 10, 10);
//            MapFrame mapFrame1 = new MapFrame(8, 3, 3);
//            Package package1 = new Package(1,1.5f,1,2);
//            Road road1 = new Road("dr1", 1, "g1", "g2", "utwardzona", true, 1, 12);
//            Town town1 = new Town("grod2", 1, 100, 100);
//            Tree tree1 = new Tree(1, 1, false);
//            
//            clipsEnv.getWorldEnv().assertString(bandits1.toString());
//            clipsEnv.getWorldEnv().assertString(blockade1.toString());
//            clipsEnv.getWorldEnv().assertString(cataclysm1.toString());
//            clipsEnv.getWorldEnv().assertString(mapFrame1.toString());
//            clipsEnv.getWorldEnv().assertString(package1.toString());
 //          clipsEnv.getWorldEnv().assertString(road1.toString());
//            clipsEnv.getWorldEnv().assertString(town1.toString());
//            clipsEnv.getWorldEnv().assertString(tree1.toString());
//            clipsEnv.displayFacts();
            
            
//            KnightStatistics ks = new KnightStatistics();
//            ArrayList<Attack> attacks = new ArrayList<Attack>();
//            attacks.add(new Attack(10, 10, "Attack1"));
//            attacks.add(new Attack(20, 20, "Attack2"));
//            attacks.add(new Attack(30, 30, "Attack3"));
//            Knight agent = new Knight("Knight1", attacks, ks);
//            agent.setGold(550);
//            
//           // System.out.println(agent.toString());
//            clipsEnv.getWorldEnv().assertString(agent.toString());
        } catch (Exception ex) {
            Logger.getLogger(PolskaAD1340.class.getName()).log(Level.SEVERE, null, ex);
        }

 //       om.setVisible(true);
        System.out.println("done and done.");

    }
}
