package com.rdlin.RPM.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Util{

	public String encodeBWT(String input){
		//small input case
		if (input.length()==0 || input.length()==1){
			return input + "$";
		}
		//
		input = input + "$";
		//cyclic shifts
		ArrayList<String> inputList = new ArrayList<String>();
		for(int i = 0; i<input.length(); i++){
			inputList.add(input);
			input=input.substring(1)+input.charAt(0);
		}
		Collections.sort(inputList);
		String output = "";
		//extract last characters
		for(int i = 0; i<input.length(); i++){
			output+=(inputList.get(i)).charAt(input.length()-1);
		}
		return output;
	}
	public String decodeBWT(String input){	
		char[] inputArray = input.toCharArray();
		//sorting input
		char[] decodeArray = inputArray.clone();		
		Arrays.sort(decodeArray);
		//two temporary arrays required to figure out decode Values
		int[] decodeValues = new int[input.length()];
		char[] temp = inputArray.clone();
		char[] temp2 = decodeArray.clone();
		for(int i = 0; i<input.length(); i++){
			for(int j = 0; j<input.length(); j++){
				if(temp2[j]==temp[i]){
					decodeValues[j]=i;
					temp[i]=' ';
					temp2[j]=' ';
					break;
				}
			}
		}
        //initializing iterator for decoding
		int iterator = input.indexOf('$');
		String output = "";
		//decode
		while(iterator!=0){
			output+=decodeArray[iterator];
			iterator=decodeValues[iterator];
		}
		return output;
	}
	
	//removes instances of "strip" in "input"
	public String stripChars(String input, String strip) {
	    StringBuilder result = new StringBuilder();
	    for (char c : input.toCharArray()) {
	        if (strip.indexOf(c) == -1) {
	            result.append(c);
	        }
	    }
	    return result.toString();
	}
	
	//removes all duplicates from string
	public String removeDuplicates(String input) {
	    StringBuilder noDupes = new StringBuilder();
	    for (int i = 0; i < input.length(); i++) {
	        String si = input.substring(i, i + 1);
	        if (noDupes.indexOf(si) == -1) {
	            noDupes.append(si);
	        }
	    }
	    return noDupes.toString();
	}
	
	public String encodeKeyword(String input, String keyword){
		keyword = removeDuplicates(keyword);
		String encode = "";
		String output = "";
		//partial ascii alphabet
		String partial = "";
		for (int i = 32; i<=126; i++){
			partial+=(char)i;
		}
		//System.out.println(partial);
		for(int i = 0; i<keyword.length();i++){
			encode = keyword + stripChars(partial, keyword);
		}
		//System.out.println(encode);
		for(int i = 0; i<input.length();i++){
			output+=encode.charAt((int)(input.charAt(i))-32);
		}
		//System.out.println(output);
		return output;	
	}
	
	public String decodeKeyword(String input, String keyword){
		keyword = removeDuplicates(keyword);
		String encode = "";
		String output = "";
		//partial ascii alphabet
		String partial = "";
		for (int i = 32; i<=126; i++){
			partial+=(char)i;
		}
		//System.out.println(partial);
		for(int i = 0; i<keyword.length();i++){
			encode = keyword + stripChars(partial, keyword);
		}
		//System.out.println(encode);
		for(int i = 0; i<input.length();i++){
			output+=partial.charAt(encode.indexOf(input.charAt(i)));
		}
		//System.out.println(output);
		return output;	
	}
	
	public String encode(String input, String keyword){
		return encodeBWT(encodeKeyword(input, keyword));
	}
	
	public String decode(String input, String keyword){
		return decodeKeyword(decodeBWT(input), keyword);
	}
	
	//read lines from file
	public static List<String> readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
        	System.out.println(line);
            lines.add(line);
        }
        bufferedReader.close();
        return lines;
    }

}
