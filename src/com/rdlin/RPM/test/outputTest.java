package com.rdlin.RPM.test;

import static org.junit.Assert.*;
import com.rdlin.RPM.util.util;

import org.junit.Test;

public class outputTest extends util{
	@Test
    public void testEncryptDecrypt(){
    	String text = "abcdef";
    	String keyword = "AbC123";
        assertEquals(text, decodeKeyword(decodeBWT(encodeBWT(encodeKeyword(text, keyword))), keyword));       
    }

}
