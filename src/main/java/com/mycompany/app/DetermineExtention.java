package com.mycompany.app;

import java.nio.file.Files;
import java.util.Optional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Base64;


public class DetermineExtention {

    private String getExtensionByString(String filename) {
    	String extension = null;
		if(filename.contains(".")){
			extension = filename.substring(filename.lastIndexOf(".") + 1);
		}
		return extension;
    }

    public String displayData(String filename, byte[] bytes){
    	// txt
    	if(getExtensionByString(filename).equals("txt")){
    		System.out.println(filename + " : Is recognized as txt extention");	
    		return displayTxt(bytes);
    	// code
    	}else if(getExtensionByString(filename).equals("js") || 
    			getExtensionByString(filename).equals("java") ||
    			 getExtensionByString(filename).equals("cs") || 
    			 getExtensionByString(filename).equals("py")){
    		System.out.println(filename + " : Is recognized as code extention");
    		return displayCode();
		// Pdf
		}else if(getExtensionByString(filename).equals("pdf")){
			System.out.println(filename + " : Is recognized as a pdf extention");
			return displayPdf(bytes);
		// jpg / jpeg
		}else if(getExtensionByString(filename).equals("jpg") || getExtensionByString(filename).equals("jpeg")){
			System.out.println(filename + " : Is recognized as a img extention");
			return displayImg(bytes);
		// other
		}else{
			System.out.println(filename + " : Is recongnized as Other -- Display it with meta data");
			return displayOther();
	    }
	}

	private String displayTxt(byte[] bytes){
		return "<p>"+new String(bytes)+"</p>";
	}

	private String displayCode(){
		return "<pre><code>\"/data\"</code></pre>";
	}

	private String displayPdf(byte[] bytes){
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = "data:application/pdf;base64," + encoder.encodeToString(bytes);
		return "<iframe src=" + encoding + "></iframe>";
	}

	private String displayImg(byte[] bytes){
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = "data:image/jpeg;base64," + encoder.encodeToString(bytes);
		return " <img src=" + encoding + " > ";
	}

	private String displayOther(){
		return "<p>Other</p>";
	}

}