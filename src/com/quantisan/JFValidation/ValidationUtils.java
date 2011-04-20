package com.quantisan.JFValidation;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;


public final class ValidationUtils {
	private ValidationUtils() {};
	
    private static String pubKeyStr = "o84092606gc29agp48dtod040g21800e1023o0621022g2g80g205jftc9sjsfkiu21pg3613iba3f5os85lg6gp3f8gkn42rlk7e8fplhqtc9opv3o2616qmu1k8er0tacusdcahasb0kkjm68e3v19o5qsrhhr1g328887v9k3ccj4r6aepgfqamkn0jdausnp68eoovp4dem3o5gchertfscd8sqb0cvmuu839hgkgr3ututmqno6j5esjv9qp88d4nh366rscho4fjbarr5g3nrkh6aermve5oogvl0jm9rvgmt2h9487fcahgu7o6ev5mr61n4pp2dd7nvquulp1necgdouba6d39buj4q1n5hbj6mf47t9jolm33ula90sahcv5kiusfdoqmeq9aik4d8ijjnlcheilt87sq9p1lou30dts182v5prpfsg93l4v4u21cksrf081g2001";

    protected static boolean validateSignature(byte[] signature, String message) 
    		throws NoSuchAlgorithmException, InvalidKeySpecException, 
    				InvalidKeyException, SignatureException, 
    				UnsupportedEncodingException 
    {
    	byte[] encKey = BytesUtils.stringToBytes(pubKeyStr);
    	X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
    	
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
    	
		Signature rsa = Signature.getInstance("SHA1withRSA"); 
		rsa.initVerify(pubKey);
		rsa.update(message.getBytes("UTF-8"));
		return rsa.verify(signature);
    }
    
    protected static boolean validateHash(byte[] digest, String message) 
    		throws UnsupportedEncodingException, NoSuchAlgorithmException 
    {
    	byte[] newDig = Hasher.getHash(message);
    	return Arrays.equals(digest, newDig);
    }
}