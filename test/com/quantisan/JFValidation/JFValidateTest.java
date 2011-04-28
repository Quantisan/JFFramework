package com.quantisan.JFValidation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JFValidateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckKey() {
		assertTrue(JFValidate.checkKey(LicenseKey.getKeyName()));
	}

}
