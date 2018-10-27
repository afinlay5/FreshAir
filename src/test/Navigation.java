/*
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

// package com.freshair;

import java.util.List;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ListIterator;
import java.util.Collections;

final class Navigation {
	private static List<Scene> scenes;
	private static ListIterator<Scene> iter;
	private static Stage app;
	private static Scene currentScene;
	private boolean exists = false;

	Navigation (Stage stage, Scene ... scenes) {
		if (exists)
			throw new IllegalStateException("One such instance only.");
		else {
			//Fixed Size, unmodifiable ListView
			this.scenes = Collections.unmodifiableList(Arrays.asList(scenes[0], scenes[1])); 
			this.iter = this.scenes.listIterator();
			this.currentScene = scenes[0];
			this.app = stage;
			this.exists = true;
		}
	}

	public static Scene getSceneByIndex(int i) {
		resolveCurrentScene();//temp crappy solution, maybe....certainly.

		if (i>2) throw new IllegalStateException("Three scenes in this stage.");
		else if (i<=0) throw new IllegalStateException("Three scenes in this stage.");
		else {
			Navigation.currentScene = scenes.get(i-1);
			return scenes.get(i-1); //Weakeness: The caller has to be responsible in switching scenes
		}
	};

	public static void resolveCurrentScene () {
		//horribly inefficient to do this nonsense every time (or at all)

		Scene scene = (Scene) (Navigation.app.getScene());

		if (scene.equals(currentScene))
			return;
		else {
			//walk baclk
			while (iter.hasPrevious())
				iter.previous();

			iter.forEachRemaining( e -> {
					if (e.equals(scene)) {
						iter.previous();
						return;
					}
				}
			);
		}
	};

	public ListIterator getIterator() {
		return this.iter;
	};
	public static Stage getStage() {
		return Navigation.app;
	};
	public static void goHome() {
		Navigation.currentScene = scenes.get(0);
		//walk the iterator backwards
		while (iter.hasPrevious())
			iter.previous();
		app.setScene(Navigation.currentScene);
		app.show();
	};

	public static void advance () {
		resolveCurrentScene ();
		if (iter.hasNext() && iter.nextIndex() != scenes.size()) { //this check is probably not necessary?
			iter.next();
			Scene scene = iter.next();
			Navigation.currentScene = scene;
			Navigation.app.setScene(scene);
			Navigation.app.show();
		}
		else if (iter.nextIndex() == scenes.size() ) {
			return;
		}
		else throw new IllegalStateException ("No such scene!");
	};

	public static void retreat () {
		resolveCurrentScene ();
		if (iter.hasPrevious() ) { 
			iter.previous();
			Scene scene = iter.previous();
			Navigation.currentScene = scene;
			Navigation.app.setScene(scene);
			Navigation.app.show();
		}
		else if (iter.previousIndex() == -1) {

		}
		else throw new IllegalStateException ("No such scene!");
	};
}