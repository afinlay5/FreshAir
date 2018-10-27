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
**/ 

import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

// package com.freshair;

class MapScene extends Scene {
	private Label logo;
	private final int height, width;
	private final TextField city, province, country;
	private ImageView glass;
	private static ImageView home, back, fwd;
	private final UpstreamDataSource uds;
	private final Button bFwd, bBack, bHome;
	private final BorderPane root;
	
	MapScene (BorderPane root, int height, int width, String[] pt, Button bFwd, Button bBack, Button bHome, UpstreamDataSource uds) {
		super(root, width, height);
		this.root = root;
		this.uds = uds;
		this.bFwd = bFwd;
		this.bBack = bBack;
		this.bHome = bHome;
		this.width = width;
		this.height = height;
		this.logo = new Label("FreshAir");
		this.logo.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.LIGHT, javafx.scene.text.FontPosture.ITALIC, 10));
		this.city = new TextField(pt[0]);
		this.province = new TextField(pt[1]);
		this.country = new TextField(pt[2]);

		this.bHome.setGraphic( new ImageView ( new Image(getClass().getResource("home.png").toExternalForm(),20,20,true,true)) ); 
		this.bBack.setGraphic( new ImageView ( new Image(getClass().getResource("bck.png").toExternalForm(),20,20,true,true)) ); 
		this.bFwd.setGraphic( new ImageView ( new Image(getClass().getResource("fwd.png").toExternalForm(),20,20,true,true)) ); 
		
		this.setup();
	};

	public void setup() {
		//TopRow
		Label topLbl = new Label("FreshAir® - Clean Air Map!");
		topLbl.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));

		//Left
		VBox left = new VBox();

		//TopLeftRow
		HBox topLeftRow = new HBox();
		glass = new ImageView ( new Image(getClass().getResource("magnifier.png").toExternalForm(),20,20,true,true));
		city.setEditable(false);
		province.setEditable(false);
		country.setEditable(false);
		topLeftRow.getChildren().addAll(glass, city, province, country);
		topLeftRow.setSpacing(8.0);

		//BottomLeftRow
		VBox botLeftCol = new VBox();
		botLeftCol.setAlignment(Pos.CENTER);
		
		Label lbl_city = new Label(city.getText());
		lbl_city.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));
		Label temp = new Label (String.valueOf(uds.getTemp()).concat("°C"));
		temp.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 25));
		
		Label lbl_weather = new Label("Weather:");
		lbl_weather.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));
		Label wind = new Label (String.valueOf(uds.getWind()).concat(" knots"));
		wind.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 25));
		Label precip = new Label (String.valueOf(uds.getPrecip()).concat("% chance of rain."));
		precip.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 25));
		
		Label lbl_pollution = new Label("Pollution");
		lbl_pollution.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));
		Label chinaAQI = new Label("China AQI: ".concat( String.valueOf(uds.getChinaAQI())));
		chinaAQI.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));
		Label usaAQI = new Label("USA AQI: ".concat( String.valueOf(uds.getUSAAQI())));
		usaAQI.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 20));

		botLeftCol.getChildren().addAll(
				lbl_city, temp,
				lbl_weather, wind,precip,
				lbl_pollution, chinaAQI, usaAQI
		);

		left.getChildren().addAll(topLeftRow, botLeftCol);


		//BottomRow
		BorderPane bottomRow = new BorderPane();
		bottomRow.setPadding(new Insets(03, 10, 02, 10));
		bottomRow.setMinHeight(this.height*.06);
		bottomRow.setPrefHeight(this.height*.06);
		bottomRow.setMaxHeight(this.height*.06);
		bottomRow.setBackground( new Background (new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY ) ) ); 

		home = new ImageView ( new Image(getClass().getResource("home.png").toExternalForm(),20,20,true,true)); bHome.setGraphic(home);
		back = new ImageView ( new Image(getClass().getResource("bck.png").toExternalForm()) ); bBack.setGraphic(back);
		fwd = new ImageView ( new Image(getClass().getResource("fwd.png").toExternalForm()) ); bFwd.setGraphic(fwd);
		
		Label lbl = new Label("Breathe in, Breathe fresh . . . ®");
		lbl.setFont(Font.font("Simplex", javafx.scene.text.FontWeight.LIGHT, javafx.scene.text.FontPosture.ITALIC, 10));

		bottomRow.setLeft(bHome);
		bottomRow.setCenter(lbl);
		bottomRow.setRight( new HBox(bBack, bFwd)) ;

		bFwd.setDisable(true);

		// Root Pane
		root.setTop(topLbl);
		root.setLeft(left);
		root.setBottom(bottomRow);

		/*Component Event Handling*/
		bHome.setOnMouseClicked(e -> { 
				Navigation.goHome();
			}
		);
		

		bBack.setOnMouseClicked(e -> { 
				Navigation.retreat();
			}
		);

	};
};