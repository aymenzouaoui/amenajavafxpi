/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

 class PrintService extends Service<Boolean> {
        private int documentId;
 private static final String API_ENDPOINT = "http://localhost:8000/documentexpedition/print/";
        public PrintService(int documentId) {
            this.documentId = documentId;
        }

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                @Override
                protected Boolean call() {
                    try {
                        // Create the URL with the document ID
                        URL url = new URL(API_ENDPOINT + documentId);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");

                        // Read the response
                        InputStream inputStream = connection.getInputStream();
                        
                        // Save the PDF file to the desktop
                        String desktopPath = System.getProperty("user.home") + "/Desktop";
                        String filePath = desktopPath + "/fileaa"+documentId+".pdf";
                        File pdfFile = new File(filePath);
                        try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                        
                        inputStream.close();
                        connection.disconnect();

                        // Success
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };
        }
    }