/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena;

import amena.model.Reservation;
import amena.model.Vehicule;
import amena.services.ReservationCRUD;
import amena.services.VehiculeCRUD;
import java.sql.Date;


/**
 *
 * @author klair
 */
public class Amena {

    public static void main(String[] args) {
        
      /*  
        Vehicule v1 = new Vehicule("Voiture", "110 tun 1225", false,"0",0, "bmw","rouge",150);
        Vehicule v2 = new Vehicule("Moto", "55456", false,"0",0, "103","noir",75);
        Vehicule v3 = new Vehicule("Velo","", false,"0",0, "Btwin","vert",30);
        Vehicule v5 = new Vehicule("Voiture", "210 tun 725", false,"0",0, "bmw","noir",150);
        Vehicule v6 = new Vehicule("Voiture", "150 tun 1025", false,"0",0, "peugeot","rouge",200);
        Vehicule v7 = new Vehicule("Camion", "90 tun 1225", true,"0",0, "Volvo","rouge",70);
        Vehicule v8 = new Vehicule("Velo", "", false,"0",0, "aze","rouge",35);
       

        
        VehiculeCRUD vc = new VehiculeCRUD(); 

       
     /*   vc.ajouter(v1) ;
        vc.ajouter(v2) ;
        vc.ajouter(v3) ;
//        vc.ajouter(v4) ;
        vc.ajouter(v5) ;
        vc.ajouter(v6) ;
        vc.ajouter(v7) ;*/

        /*System.out.println(vc.afficher());
       
        
        Vehicule v4 = new Vehicule(23,"camion","220 tun 3652", false,"0",0, "Btwin","blanc",200);
        vc.modifier(v4);
        System.out.println(vc.afficher());
      
       /* 
        
        vc.supprimer(); 
        System.out.println(vc.afficher());
        
        
        System.out.println(vc.getByID(18));
        System.out.println(vc.filterByEtat(0));

*/
       // ReservationCRUD rc = new ReservationCRUD(); 
  /*      
        
        Reservation r1 = new Reservation(15,1,Date.valueOf("2023-1-1"),Date.valueOf("2023-1-10"),1000);
        Reservation r2 = new Reservation(16,2,Date.valueOf("2023-2-1"),Date.valueOf("2023-1-10"),1500);
        Reservation r3 = new Reservation(18,3,Date.valueOf("2023-3-1"),Date.valueOf("2023-3-10"),1000);

        rc.ajouter(r1);
        rc.ajouter(r2);
        rc.ajouter(r3);       
        System.out.println(rc.afficher());

        Reservation r4 = new Reservation(30,16,2,Date.valueOf("2023-2-1"),Date.valueOf("2023-3-10"),2000);
        rc.modifier(r4);        
        System.out.println(rc.afficher());
    
        */
      /*  rc.supprimer(30);
        System.out.println(rc.afficher());

        System.out.println(rc.getByID(29));
        
        System.out.println(rc.filterByDate_fin(Date.valueOf("2023-1-10")));*/




   }
    
}
