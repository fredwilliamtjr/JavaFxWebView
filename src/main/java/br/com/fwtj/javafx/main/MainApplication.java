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
package br.com.fwtj.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setTitle("JavaFxWebView");
        Pane pane = loadMainPane();
        Scene scene = createScene(pane);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Bem Vindo");
        stage.show();

    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = loader.load(ClassLoader.getSystemResourceAsStream("br/com/fwtj/javafx/view/main.fxml"));
        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(ClassLoader.getSystemResource("br/com/fwtj/javafx/style/vista.css").toExternalForm());
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
