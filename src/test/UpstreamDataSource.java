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

import java.io.File;
import java.net.URI;
import java.util.Scanner;
import java.time.Duration;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.InputStream;
import java.io.FileOutputStream​;
import java.nio.charset.Charset;
import java.net.Authenticator;
import java.net.ProxySelector;
import java.net.HttpURLConnection;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;

// package com.freshair;

class UpstreamDataSource { 

	private String[] args;
	private final String API_ENDPOINT = "https://api.airvisual.com/v2/city?";
    private final String API_ENDPOINT_KEY = "8MZjvqohdQroJEogm";
    private int USAAQI, chinaAQI, temp, precip;
    private double wind;

	UpstreamDataSource () {
	}

	public void setArgs (String[] args) {
		this.args = args;
	}

    public String call() {	
    	String JSON="";
    	try {
	        //HTTP GET REQUEST
	        HttpURLConnection HTTP_CLIENT= (HttpURLConnection) 
	                            URI.create(
	                                new StringBuilder(API_ENDPOINT)
	                                .append("city=").append(args[0])
	                                .append("&state=").append(args[1])
	                                .append("&country=").append(args[2])
	                                .append("&key=").append(API_ENDPOINT_KEY)
	                                .toString())
	                            .toURL()
	                            .openConnection();
	        HTTP_CLIENT.setRequestMethod("GET");

	        
	        //HTTP RESPONSE
	        InputStream​ HTTP_RESPONSE = HTTP_CLIENT.getInputStream();
	        Scanner scn = new Scanner(HTTP_RESPONSE);
	        StringBuilder json_sb = new StringBuilder();
	        while (scn.hasNext()) {
	            json_sb.append(scn.next());
	        }
	        JSON = json_sb.toString();
	      	/*  
	        if (HTTP_CLIENT.getContentType().contains("json")) {
	            InputStream​ stream_in = (InputStream​)(HTTP_CLIENT.getContent());
	            FileOutputStream​ stream_out = new FileOutputStream​ (new File("response1.json"));
	            stream_in.transferTo(stream_out);
	            stream_in.close();
	            stream_out.close();
	        }
	        else
	            return; // lol!
			*/
	        // HTTP STATUS
	        int statusCode = HTTP_CLIENT.getResponseCode();
	        
	        // HANDLE RESPONSE
	        if (statusCode == 200 || statusCode == 201)
	            System.out.println("Success -- REST API Call:   " + 
	                args[1] + ", " + args[0] + " [" + args[2] +"]\n");
	        else
	            System.out.println("Failure! -- Pre-Java 11 REST API Call");
	        
	        System.out.println("---------------------------------");
	    } catch (Exception e) { 
	    	System.out.println(e);
	    };

	    return JSON;

    };

	public int getUSAAQI() {
		return this.USAAQI;
	}

	public int getChinaAQI() {
		return this.chinaAQI;
	}

	public int getTemp() {
		return this.temp;
	}

	public double getWind (){
		return this.wind;
	}
	public int getPrecip() {
		return this.precip;
	}


	public void setUSAAQI(int USAAQI) {
		this.USAAQI = USAAQI;
	}

	public void setChinaAQI(int chinaAQI) {
		this.chinaAQI = chinaAQI;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public void setWind (double wind){
		this.wind = wind;
	}
	public void setPrecip(int precip) {
		this.precip = precip;
	}


};