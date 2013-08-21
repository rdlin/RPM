package com.rdlin.RPM.main;

import com.rdlin.RPM.util.util;

public class main extends util{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "testpassword123";
		String keyword = "tEsTkAst132";
		System.out.println("Original text: " + text);
		
		String key = encodeKeyword(text, keyword);
		System.out.println("EncodedKeyword String: " + key);
		
		String encoded = encodeBWT(key);
		System.out.println("EncodedBWT String: " + encoded);
		
		String decoded = decodeBWT(encoded);
		System.out.println("DecodedBWT String: " + decoded);
		
		String lock = decodeKeyword(decoded, keyword);
		System.out.println("DecodedKeyword String: " + lock);

	}

}
