package com.cv.parser.factorymethod;

public class ExtensionSingleton {

    private static ExtensionSingleton instance;
    
    private ExtensionSingleton() {
	
    }
    
    public static ExtensionSingleton getInstance() {
	if (instance == null) {
	    instance = new ExtensionSingleton();
	}
	return instance;
    }
    
    public enum Ext {
	PDF, DOC, DOCX, TXT, RTF, DOT, DOTX
    }

    public String get(Ext ext) {
	switch (ext) {
	case PDF:
	    return ".pdf";
	case DOC:
	    return ".doc";
	case DOCX:
	    return ".docx";
	case TXT:
	    return ".txt";
	case RTF:
	    return ".rtf";
	case DOT:
	    return ".dot";
	case DOTX:
	    return ".dotx";
	}
	return null;
    }
}
