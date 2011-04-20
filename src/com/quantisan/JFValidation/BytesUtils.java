package com.quantisan.JFValidation;

import java.math.BigInteger;

public final class BytesUtils {
	private BytesUtils() {};
	
	protected static byte[] stringToBytes(String input) {
		BigInteger bigint = new BigInteger(input, 32);
		return bigint.toByteArray();
	}
	
	protected static String bytesToString(byte[] bytes) 
	{
        BigInteger bigint = new BigInteger(bytes);
        return bigint.toString(32);
	}
}
