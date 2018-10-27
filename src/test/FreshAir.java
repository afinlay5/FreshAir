/*
=================================================================================
MIT License

Copyright (c) 2018 Adrian D. Finlay, Lunick Dorcelus, Yunier Alvarez, Cheddae Grant, Adrian Silva

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
**/ 

// package com.freshair;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
// import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
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
	private static int height = 668, width =1024;
	private static final Background bkg;

	static {
		bkg = new Background (
			 		new BackgroundImage(
						new Image("bkg.jpg", true),
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.CENTER,
						// BackgroundSize(double width, double height, boolean widthAsPercentage, boolean heightAsPercentage, boolean contain, boolean cover)
						BackgroundSize.DEFAULT
					)
		);
	}

	/* Launch JavaFX application */
	public static void home (String[] args) { FreshAir.launch(args); }

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
    public void start(Stage home) {

		/* Home Screen */
		
        //Give the stage a title.
		home.setTitle("FreshAir® - Clean Air Map");

		// Provide Icon
		// home.getIcons().add(app);

		//Root Node
		VBox rootNode = new VBox();

		/*Components*/

		//TopRow
		HBox topRow = new HBox();
		Label lbl = new Label("Fresh Air");
		lbl.setFont(Font.font("Simplex", 25.14));
		topRow.getChildren().add(lbl);
		topRow.setAlignment(Pos.CENTER);
		// topRow.setBackground(	new Background(	new BackgroundFill(Paint.valueOf("#9cdef6"), CornerRadii.EMPTY, Insets.EMPTY ) ) );
		topRow.setBackground(	new Background(	new BackgroundFill(Color.rgb(156,206, 246, 0.20), CornerRadii.EMPTY, Insets.EMPTY ) ) ); 
		topRow.setMinHeight(FreshAir.height*.06);
		topRow.setPrefHeight(FreshAir.height*.07);
		topRow.setMaxHeight(FreshAir.height*.10);


		//MiddleRow
		HBox middleRow = new HBox();
		middleRow.setBackground(bkg);


		//BottomRow
		HBox bottomRow = new HBox();
		bottomRow.setMinHeight(FreshAir.height*.02);
		bottomRow.setPrefHeight(FreshAir.height*.04);
		bottomRow.setMaxHeight(FreshAir.height*.06);
		bottomRow.setBackground( new Background (new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY ) ) ); 


		//Components -> SceneGraph
		rootNode.getChildren().addAll(topRow, middleRow, bottomRow);

        //Scene
        Scene scene = new Scene(rootNode, FreshAir.width, FreshAir.height); 


   		//Restrict resizeable
		home.setResizable(false);

		//Make Transparent
		// home.initStyle(StageStyle.UNDECORATED);

        //Set Scene, Show stage
        home.setScene(scene);
        home.show();

	};

 	// JavaFX Lifecycle Method #3
    @Override public void stop () { 

    };
}