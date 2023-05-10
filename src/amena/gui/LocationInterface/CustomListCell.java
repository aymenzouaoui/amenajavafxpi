/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.LocationInterface;

import amena.model.Vehicule;
import javafx.scene.control.ListCell;

/**
 *
 * @author klair
 */
  class CustomListCell extends ListCell<Vehicule> {

      @Override
        protected void updateItem(Vehicule item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                // Set the cell content to a 3-line, 4-column format
                setText(item.toString());
            }
        }
}
