package com.quantisan.JFValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.quantisan.JFUtil.JForexContext;
import com.quantisan.JFUtil.Printer;

public final class LicenseKey {
	private static final String keyFileName = "jfqtd.key";
	
	private String signature, message;
	
	/**
	 * @return the signature
	 */
	protected byte[] getSignature() {
		try {
			return BytesUtils.stringToBytes(signature);
		} catch (NullPointerException ex) {
			return new byte[1];
		}
	}

	/**
	 * @return the message
	 */
	protected String getMessageLine() {
		if (message == null)
			return "";
		else	return message;
	}
	
	protected String getInformation() {
		int index = getMessageLine().indexOf(",");
		return getMessageLine().substring(index + 1);
	}
	
	protected byte[] getHash() {
		String hash = getMessageLine().split(",")[0];
		try	{
			return BytesUtils.stringToBytes(hash);
		}
		catch (NumberFormatException ex) {
			return new byte[1];
		}
	}

	protected LicenseKey() throws FileNotFoundException {
		File fileDir = JForexContext.getContext().getFilesDir();
		String fileName = fileDir.toString() + File.separator + getKeyName();
//		String fileName = getKeyName();
		
		BufferedReader reader;

		reader = new BufferedReader(new FileReader(fileName));			
		
		try {
			message = reader.readLine();
			signature = reader.readLine();
			reader.close();
		} catch (IOException ex) {
			Printer.printErr("License key file error", ex);
			this.signature = "";
			this.message = "";
			return;
		}		
	}

	protected static String getKeyName() {
		return keyFileName;
	}	
	
	public static void main(String[] args) {
		LicenseKey key = null;
		try {
			key = new LicenseKey();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(key.getMessageLine());
		System.out.println(key.getInformation());
		System.out.println(key.getSignature());
	}
}
