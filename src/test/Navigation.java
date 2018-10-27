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
import java.util.ListIterator;
import java.util.Collections;

class Navigation {
	private final List<Scene> scenes;
	private final ListIterator<Scene> iter;
	Navigation (Scene ... scenes) {
		//Fixed Size, unmodifiable ListView
		this.scenes = Collections.unmodifiableList(Arrays.asList(scenes[0])); //scenes[1]
		this.iter = this.scenes.listIterator();
	}

	public Scene getSceneByIndex(int i) {
		if (i>2) throw new IllegalStateException("Three scenes in this stage.");
		else if (i<=0) throw new IllegalStateException("Three scenes in this stage.");
		else return scenes.get(i-1);
	};

	public ListIterator getIterator() {
		return this.iter;
	}
}