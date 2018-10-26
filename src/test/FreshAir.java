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
import javafx.scene.layout.VBox;
import javafx.application.Application;

public class FreshAir extends Application {
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
		VBox rootNode = new VBox(3.0);
		rootNode.setAlignment(Pos.CENTER);

		//Components -> SceneGraph
		// rootNode.getChildren().addAll()

        //Scene
        Scene scene = new Scene(rootNode, 1024, 668); 


   		//Restrict resizeable
		home.setResizable(false);

        //Set Scene, Show stage
        home.setScene(scene);
        home.show();

	};

 	// JavaFX Lifecycle Method #3
    @Override public void stop () { 

    };
}