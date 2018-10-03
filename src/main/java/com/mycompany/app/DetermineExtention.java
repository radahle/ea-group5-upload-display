package com.mycompany.app;

import java.util.Optional;

public class DetermineExtention {

    private String getExtensionByString(String filename) {
    	String extension = null;
		if(filename.contains(".")){
			extension = filename.substring(filename.lastIndexOf(".") + 1);
		}
		return extension;
    }

    public String displayData(String filename){
    	// txt
    	if(getExtensionByString(filename).equals("txt")){
    		System.out.println(filename + " : Is recognized as txt extention");	
    		return displayTxt();
    	// code
    	}else if(getExtensionByString(filename).equals("js") || 
    			 getExtensionByString(filename).equals("cs") || 
    			 getExtensionByString(filename).equals("py")){
    		System.out.println(filename + " : Is recognized as code extention");
    		return displayCode();
		// Pdf
		}else if(getExtensionByString(filename).equals("pdf")){
			System.out.println(filename + " : Is recognized as a pdf extention");
			return displayPdf();
		// jpg / jpeg
		}else if(getExtensionByString(filename).equals("jpg") || getExtensionByString(filename).equals("jpeg")){
			System.out.println(filename + " : Is recognized as a img extention");
			return displayImg();
		// other
		}else{
			System.out.println(filename + " : Is recongnized as Other -- Display it with meta data");
			return displayOther();
	    }
	}

	private String displayTxt(){
		return "This is text";
	}

	private String displayCode(){
		return "<pre><code> String code = \"Will display code\"; </code></pre>";
	}

	private String displayPdf(){
		return "Will display a pdf.";
	}

	private String displayImg(){
		return " <img src=\"https://tek.vgc.no/2348/2348082/tb8d0cbc.956x538.jpg\" alt=\"Smiley face\" height=\"200\" width=\"200\"> ";
	}

	private String displayOther(){
		return "Other";
	}

}
