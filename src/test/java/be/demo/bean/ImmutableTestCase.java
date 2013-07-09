package be.demo.bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class ImmutableTestCase {

	@Test
	public void mutablePrimitiveArray() {
		final int[] array = new int[] {0, 1, 2, 3};
		array[0] = 42;
		
		assertThat(0,not(array[0]));
		assertThat(42, equalTo(array[0]));
	}
	
	@Test
	public void immutablePrimitiveArray() {
		List<Integer> integerList = Collections.unmodifiableList(Arrays.asList(0,1,2,3));
		
		try {
		integerList.set(0, 42);
			Assert.fail("Error: We should not be able to modify this list");
		} catch (UnsupportedOperationException ex) {
			// everything is running smooth 
		}
		
		assertThat(0, equalTo(integerList.get(0)));
		
		Integer reference = integerList.get(0);
		reference = 42;
		
		assertThat(0, equalTo(integerList.get(0)));
	}
	
}
