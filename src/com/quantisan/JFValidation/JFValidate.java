package com.quantisan.JFValidation;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.dukascopy.api.IEngine;
import com.quantisan.JFUtil.JForexContext;

/**
 * Licence validation utility class
 * @author plam
 *
 */
public final class JFValidate {
	private JFValidate() {};
	
	public static boolean isLicensed() {
		if (checkType() || checkKey()) 
		{
			return true;
		} else { return false; }
	}
	
	private static boolean checkType() {
		IEngine.Type type = JForexContext.getEngine().getType();		
		if (type == IEngine.Type.DEMO || type == IEngine.Type.TEST) 
		{
			return true;
		} else { return false; }
	}
	
	private static boolean checkKey() {
		LicenseKey key;
		try {
			key = new LicenseKey();
		} catch (FileNotFoundException e) {
			return false;
		}
		
		byte[] keyDigest = key.getHash();
		byte[] signature = key.getSignature();
		boolean isHashOK, isSigOK;
		try {
			isHashOK = ValidationUtils.validateHash(keyDigest, key.getInformation());
			isSigOK = ValidationUtils.validateSignature(signature, key.getMessageLine());
		} catch (GeneralSecurityException e) {
			return false;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		
		return isHashOK && isSigOK;
	}
//	
//	public static void main(String[] args) {
//		System.out.println(checkKey());
//	}
}
