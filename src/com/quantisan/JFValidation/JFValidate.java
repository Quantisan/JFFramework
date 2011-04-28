package com.quantisan.JFValidation;

import java.io.File;
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
	
	public final static boolean isLicensed() {
		File fileDir = JForexContext.getContext().getFilesDir();
		String fileName = fileDir.toString() + File.separator + LicenseKey.getKeyName();
		
		if (checkType() || checkKey(fileName)) 
		{
			return true;
		} else { return false; }
	}
	
	private final static boolean checkType() {
		IEngine.Type type = JForexContext.getEngine().getType();		
		if (type == IEngine.Type.DEMO || type == IEngine.Type.TEST) 
		{
			return true;
		} else { return false; }
	}
	
	final static boolean checkKey(String fileName) {
		LicenseKey key;
		try {
			key = new LicenseKey(fileName);
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
}
