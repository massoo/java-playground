package be.demo.bean;

import org.junit.Assert;
import org.junit.Test;

import be.demo.utility.MasterkeyGenerator;

public class GenerateMasterkeyTestCase {

	@Test
	public void testGenerateMasterkey() {
		try {
			System.out.println(MasterkeyGenerator.generateMasterkey());
		} catch (Exception ex) {
			Assert.fail("Unable to generate master key !");
		}
	}

	@Test
	public void testGenerateMastersalt() {
		try {
			System.out.println(MasterkeyGenerator.generateMasterSalt());
		} catch (Exception ex) {
			Assert.fail("Unable to generate master salt !");
		}
	}

}
