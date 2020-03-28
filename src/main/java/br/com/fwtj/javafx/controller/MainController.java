/*
    Maven-JavaFX-Package-Example
    Copyright (C) 2017-2018 Luca Bognolo

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package br.com.fwtj.javafx.controller;

import br.com.fwtj.javafx.main.MainApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller class for the entire layout.
 */
public class MainController implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        webView.getEngine().titleProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
                MainApplication.stage.setTitle(newvalue);
            }
        });

        webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("oldValue: " + oldValue);
                System.out.println("newValue: " + newValue);
                File file = new File(".");
                String[] downloadableExtensions = {".doc", ".xls", ".zip", ".exe", ".rar", ".pdf", ".jar", ".png", ".jpg", ".gif", ".pfx", ".pdf", "%20Data"};
                for (String downloadAble : downloadableExtensions) {
                    if (newValue.endsWith(downloadAble)) {
                        try {
                            if (!file.exists()) {
                                file.mkdir();
                            }
                            File download = new File(file + "/" + "newValue.pdf");
                            if (download.exists()) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Exists");
                                alert.setHeaderText("What you're trying to download already exists");
                                alert.setContentText("What you're trying to download already exists");
                                alert.showAndWait();
                                return;
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Downloading");
                            alert.setHeaderText("Started Downloading");
                            alert.setContentText("Started Downloading");
                            alert.showAndWait();
                            FileUtils.copyURLToFile(new URL(webView.getEngine().getLocation()), download);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Download");
                            alert.setHeaderText("Download is completed your download will be in:");
                            alert.setContentText(file.getAbsolutePath());
                            alert.showAndWait();
                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("ERROR");
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                        }
                    }
                }
            }
        });

        webView.getEngine().setJavaScriptEnabled(true);
        webView.getEngine().load("http://localhost");

    }


}
