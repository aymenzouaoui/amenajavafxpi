/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamsamena;

import digideramers.amena.models.Competition;
import digideramers.amena.models.Gifts;
import digidreamers.amena.services.CompetitionCRUD;
import digidreamers.amena.services.GiftsCRUD;
import java.sql.Date;
import test.workshop.services.ColisCRUD;

/**
 *
 * @author Ahlem
 */
public class DigiDreamsAmena {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // MyConnection mc = MyConnection.getInstance();
    
        
    /*    GiftsCRUD gc = new GiftsCRUD();
        
        Gifts g1 = new Gifts("ahlem","desc1","v1", 1) ; 
        Gifts g2 = new Gifts("mhamdi","desc2","v2", 1) ; 
        Gifts g3 = new Gifts("salma","desc3","v3", 2) ; 
        gc.ajouter(g1);
        gc.ajouter(g2);
        gc.ajouter(g3);
        System.out.println(gc.afficher());*/
        
        
     //   Gifts g4 = new Gifts(1,"test","desc1","v1", 2) ; 
    //    gc.modifier(g4);
    //    System.out.println(gc.afficher());

    //    gc.supprimer(2);
        
    //    System.out.println(gc.afficher());

    //    System.out.println(gc.getByID(1));

        //  System.out.println(gc.filterByComp(1));
          
        //System.out.println(gc.afficher());
        
        
        CompetitionCRUD cr = new CompetitionCRUD(); 
       
     /*  Competition c1= new Competition("titre1",Date.valueOf("2023-01-2"),Date.valueOf("2023-01-02"),1, 100) ; 
      Competition c2= new Competition("titre2",Date.valueOf("2023-04-29"),Date.valueOf("2023-05-07"),1, 200) ; 
      Competition c3= new Competition("titre3",Date.valueOf("2023-05-12"),Date.valueOf("2023-09-07"),2, 120) ; 
       cr.ajouter(c1);
       cr.ajouter(c2);
       cr.ajouter(c3);*/
        
       System.out.println(cr.afficher());
        
        Competition c4= new Competition(10,"maroua",Date.valueOf("2023-05-8"),Date.valueOf("2023-05-10"),3, 250) ; 

       cr.modifier(c4);
       ColisCRUD c = new ColisCRUD();
       c.supprimer(120);
           
        
        System.out.println(cr.afficher());

        // cr.supprimer(2);
        //System.out.println(cr.afficher());
      
       // System.out.println(cr.getByID(3));
      
       // System.out.println(cr.filterByType(1));
       
    } 
    
}
