/**
=================================================================================
MIT License

Copyright (c) 2018 Adrian D. Finlay, Lunick Dorcelus, Cheddae Grant, Adrian Silva

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

=================================================================================
*/ 

/* package com.freshair; */

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;


public class FreshAir extends Application {
	private static int height = 620, width =1024;
	private static Background bkg;
	private static Image app;
	private static Image middleImg = new Image ("bkg.png", FreshAir.width,FreshAir.height*.95, false, true, true );
	private static ImageView home, back, divider, fwd;
	private static Button bHome = new Button(), bBack = new Button(), bFwd = new Button();
	private static Navigation navigation;


	/* Launch JavaFX application */
	public static void main (String[] args) { FreshAir.launch(args); }

	/*  Connect to the API first.  */
	private static final String AirVisual_AQI_API = "https://http://api.airvisual.com/v2/city?city=Los%20Angeles&state=California&country=USA&key=fQryFDkwpvMtiZ8A7";
	static { 
		try { new URL(AirVisual_AQI_API).openConnection().connect(); }
		catch (Exception e) { ; };
	}


	// JavaFX Lifecycle Method #1
	@Override public void init () { 

	};

    @Override // JavaFX Lifecycle Method #2
    public void start(Stage main) {

		/* Main Screen */
		
        //Give the stage a title.
		main.setTitle("FreshAir® - Clean Air Map!");

		// Provide Icon
		app = new Image(getClass().getResource("app.png").toExternalForm());
		main.getIcons().add(app);

		//Root Node
		VBox rootNode = new VBox();

		/*Components*/

		//upperRow		
		HBox upperRow = new HBox();
		upperRow.setMinHeight(FreshAir.height*.94);
		upperRow.setPrefHeight(FreshAir.height*.94);
		upperRow.setMaxHeight(FreshAir.height*.94);
		upperRow.setAlignment(Pos.CENTER);
		upperRow.setBackground(new Background (new BackgroundImage (FreshAir.middleImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)) );
	
		//1
		VBox content = new VBox(10.0);
			
		ImageView symbol = new ImageView ( new Image(getClass().getResource("symbol.png").toExternalForm(),50,50,true,true));
		ImageView logo = new ImageView ( new Image(getClass().getResource("logo.png").toExternalForm(),150,150,true,true));
		HBox logos = new HBox(symbol, logo);
		logos.setSpacing(4.0);
		logos.setAlignment(Pos.CENTER);

		TextField search = new TextField (); 
		search.setPromptText("Specify a City, Province, Country . . .");
		search.setMinWidth(FreshAir.width*(1.0/4.0));
		search.setFocusTraversable(false);
				
		content.getChildren().addAll(logos,search);
		content.setAlignment(Pos.CENTER);
		
		//upperInnerRow -> upperRow
		upperRow.getChildren().addAll(content);
		upperRow.setAlignment(Pos.CENTER);

		//set alignment
		upperRow.setAlignment(Pos.CENTER);

		//BottomRow
		BorderPane bottomRow = new BorderPane();
		bottomRow.setPadding(new Insets(03, 10, 02, 10));
		bottomRow.setMinHeight(FreshAir.height*.06);
		bottomRow.setPrefHeight(FreshAir.height*.06);
		bottomRow.setMaxHeight(FreshAir.height*.06);
		bottomRow.setBackground( new Background (new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY ) ) ); 

		home = new ImageView ( new Image(getClass().getResource("home.png").toExternalForm(),20,20,true,true)); bHome.setGraphic(home);
		back = new ImageView ( new Image(getClass().getResource("bck.png").toExternalForm()) ); bBack.setGraphic(back);
		// divider = new Image(getClass().getResource("div.png").toExternalForm());
		fwd = new ImageView ( new Image(getClass().getResource("fwd.png").toExternalForm()) ); bFwd.setGraphic(fwd);
		
		Label lbl = new Label("Breathe in, Breathe fresh . . . ®");
		lbl.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.LIGHT, javafx.scene.text.FontPosture.ITALIC, 10));

		bottomRow.setLeft(bHome);
		bottomRow.setCenter(lbl);
		bottomRow.setRight( new HBox(bBack, bFwd)) ;

		//Components -> SceneGraph
		rootNode.getChildren().addAll(upperRow, bottomRow);

        //Scene
        Scene mainScene = new Scene(rootNode, FreshAir.width, FreshAir.height); 
        MapScene mapScene = new MapScene();
        navigation = new Navigation (mainScene);//, mapScene);
        
   		// Restrict resizeable
		main.setResizable(false);

		//Make Transparent
		main.initStyle(StageStyle.UNDECORATED);

		/*Component Event Handling*/
		home.setOnMouseClicked(e -> { 
				main.setScene(navigation.getSceneByIndex(0));
			}
		);

        //Set Scene, Show stage
        main.setScene(mainScene);
        main.show();

	};

 	// JavaFX Lifecycle Method #3
    @Override public void stop () { ; };
}