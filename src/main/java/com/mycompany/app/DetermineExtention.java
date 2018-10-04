package com.mycompany.app;

import java.nio.file.Files;
import java.util.Optional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Base64;


public class DetermineExtention {
	private String tempFileName;


    private String getExtensionByString(String filename) {
    	String extension = null;
		if(filename.contains(".")){
			extension = filename.substring(filename.lastIndexOf(".") + 1);
		}
		return extension;
    }

    public String displayData(String filename, byte[] bytes){
    	tempFileName = filename;
    	String extension = getExtensionByString(filename);
    	// txt
    	if(extension.equals("txt")){
    		System.out.println(filename + " : Is recognized as txt extention");	
    		return displayTxt(bytes);
    	// code
    	}else if(extension.equals("js") || 
    			extension.equals("java") ||
    			 extension.equals("cs") || 
    			 extension.equals("py")){
    		System.out.println(filename + " : Is recognized as code extention");
    		return displayCode(bytes);
		// Pdf
		}else if(extension.equals("pdf")){
			System.out.println(filename + " : Is recognized as a pdf extention");
			return displayPdf(bytes);
		// jpg / jpeg
		}else if(extension.equals("jpg") || 
			extension.equals("jpeg") || 
			extension.equals("png")){
			System.out.println(filename + " : Is recognized as a img extention");
			return displayImg(bytes, extension);
		// other
		}else{
			System.out.println(filename + " : Is recongnized as Other -- Display it with meta data");
			return displayOther(bytes);
	    }
	}

	private String displayTxt(byte[] bytes){
		return "<p>"+new String(bytes)+"</p>";
	}

	private String displayCode(byte[] bytes){
		return "<pre><code>"+new String(bytes)+"</code></pre>";
	}

	private String displayPdf(byte[] bytes){
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = "data:application/pdf;base64," + encoder.encodeToString(bytes);
		return "<iframe src=" + encoding + "></iframe>";
	}

	private String displayImg(byte[] bytes, String extension){
		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = "data:image/" + extension + ";base64," + encoder.encodeToString(bytes);
		return " <img src=" + encoding + " style=\"height: 50%; width: 50%;\"> ";
	}

	private String displayOther(byte[] bytes){
	 File otherFile = null;
	 try{
	 	otherFile = new File(tempFileName);
	 	FileOutputStream fos = new FileOutputStream(otherFile);
	 	fos.write(bytes);
	 	fos.flush();
	 	fos.close();
	 }
	 catch(IOException ex){
	 	System.out.println(ex.toString());
	 }
	 String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (otherFile.lastModified()));

		return "<p> <ul> <li>Name of file: "+ otherFile.getName() + "</li>" + 
		"<li>Classpath: " + otherFile.getAbsolutePath() + "</li>" +
		"<li>Can it be read: " + otherFile.canRead() + "</li>" +
		"<li>Last modified date: " + date + "</li>" +
		"<li>The size of the file: " + otherFile.length()/1000 + "kB</li>" 
		+ "</ul></p>";
	}

}