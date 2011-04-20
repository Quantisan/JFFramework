package com.quantisan.JFValidation;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hasher {
	private Hasher() {};
	
	public static byte[] getHash(String text) throws UnsupportedEncodingException, 
												NoSuchAlgorithmException 
	{	
		byte[] msgBytes;
		msgBytes = text.getBytes("UTF-8");
		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");		
		return md.digest(msgBytes);
	}
}
