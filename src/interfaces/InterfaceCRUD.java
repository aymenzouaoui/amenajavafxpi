/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Annonce;
import model.Reaction;
import model.Evaluation;

/**
 *
 * @author Nour Saidi
 */
public interface InterfaceCRUD<T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id) ;
    public List<T> afficher(); 
  //  public T getByID(int id); 
  //  public List<T> filterByType(String type);
    
 /*   public void ajouterAnnonce(Annonce a);
    public void modifierAnnonce(Annonce a);
    public void supprimerAnnonce(int id_annonce);
    public List<Annonce> afficherAnnonce();*/
   
    
}
