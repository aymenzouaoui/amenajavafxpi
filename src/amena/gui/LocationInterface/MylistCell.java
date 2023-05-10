/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.LocationInterface;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author klair
 */
  class MyListCell extends ListCell<MyData> {
        private AnchorPane myAnchorPane;
        private ImageView imageView,dis;
        private Label titleLabel;
        private Label descriptionLabel;
        private Label mod;

        public MyListCell() {
            super();
            myAnchorPane = new AnchorPane();
            imageView = new ImageView();
            titleLabel = new Label();
            descriptionLabel = new Label();
            dis = new ImageView();

            myAnchorPane.getChildren().addAll(imageView, titleLabel, descriptionLabel,dis);

            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            dis.setFitHeight(15);
            dis.setFitWidth(15);
            AnchorPane.setLeftAnchor(imageView, 10.0);
            AnchorPane.setTopAnchor(imageView, 10.0);

            AnchorPane.setLeftAnchor(titleLabel, 70.0);
            AnchorPane.setTopAnchor(titleLabel, 20.0);
            
             AnchorPane.setLeftAnchor(descriptionLabel, 200.0);
            AnchorPane.setTopAnchor(descriptionLabel, 20.0);
            
            AnchorPane.setRightAnchor(dis, 10.0);
            AnchorPane.setTopAnchor(dis, 10.0);
        }
        
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                imageView.setImage(item.getImage());
                titleLabel.setText(item.getTitle());
                descriptionLabel.setText(item.getDescription());
                dis.setImage(item.getEtat());

                setGraphic(myAnchorPane);
            }
        }
  }