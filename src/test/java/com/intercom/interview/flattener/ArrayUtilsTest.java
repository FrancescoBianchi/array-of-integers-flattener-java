package com.intercom.interview.flattener;

import static com.intercom.interview.flattener.ArrayUtils.flatten;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void canFlattenSimpleNestedLists() {
		List<Object> simpleBiDimensionalList = asList(1, asList(2, 3, 4), 5);	
		assertEquals(flatten(simpleBiDimensionalList), asList(1, 2, 3, 4, 5));
		
		List<Object> moreComplexBiDimensionalList = asList(1, asList(2, 3, 4), 5,  asList(6, 7), asList(8, 9));	
		assertEquals(flatten(moreComplexBiDimensionalList), asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}
	
	@Test
	public void canFlattenMultiDimensionalLists() {
		List<Object> simple4DimensionList = asList(1, asList(2, asList(3, asList(4, 5), 6), 7), 8);	
		assertEquals(flatten(simple4DimensionList), asList(1, 2, 3, 4, 5, 6, 7, 8));
		
		List<Object> complex5DimensionsList = asList(1, asList(2, asList(3, asList(4, asList(5, 6)), asList(7, 8)), asList(9)), asList(10, asList(11, 12), 13));
		assertEquals(flatten(complex5DimensionsList), asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void canFlattenDisomogeneousDimensionalLists() {
		List<?> unidimensionalListContainingBasicTypes = asList(1, "2", false, 3.14);	
		assertEquals(flatten(unidimensionalListContainingBasicTypes), asList(1, "2", false, 3.14));
		
		List<Object> biDimensionalListContainingBasicTypes = asList(1, asList("2"), false);	
		assertEquals(flatten(biDimensionalListContainingBasicTypes), asList(1, "2", false));
		
		Map<String, String> simpleMap = new HashMap<String, String>() {{
			put("foo", "bar");
			put("Hello", "World");
		}};
		Set<Date> simpleSet = new HashSet<Date>() {{
			add(new Date());
		}};

		List<Object> unidimensionalListContainingCollectionsThatAreNotLysts = asList(1, "2", simpleMap, simpleSet);
		assertEquals(flatten(unidimensionalListContainingCollectionsThatAreNotLysts), asList(1, "2", simpleMap, simpleSet));
		
		List<Object> bidimensionalListContainingCollectionsThatAreNotLysts = asList(1, "2", asList(simpleMap, simpleSet));
		assertEquals(flatten(bidimensionalListContainingCollectionsThatAreNotLysts), asList(1, "2", simpleMap, simpleSet));
	}
	
	@Test
	public void unidimensionalListAreLeftUntouched() {
		List<Integer> listOfOneElement = new ArrayList<Integer>();
		listOfOneElement.add(1);
		assertEquals(flatten(listOfOneElement), asList(1));
		
		List<Integer> listOfFewElements = new ArrayList<Integer>();
		listOfFewElements.add(1);
		listOfFewElements.add(2);
		listOfFewElements.add(3);
		assertEquals(flatten(listOfFewElements), asList(1, 2, 3));
		
		List<Integer> listContaningNullElements = new ArrayList<Integer>();
		listContaningNullElements.add(1);
		listContaningNullElements.add(null);
		listContaningNullElements.add(3);
		assertEquals(flatten(listContaningNullElements), asList(1, null, 3));
		
		List<Integer> listContaningRepeatedNumbers = new ArrayList<Integer>();
		listContaningRepeatedNumbers.add(1);
		listContaningRepeatedNumbers.add(2);
		listContaningRepeatedNumbers.add(2);
		listContaningRepeatedNumbers.add(3);
		listContaningRepeatedNumbers.add(3);
		assertEquals(flatten(listContaningRepeatedNumbers), asList(1, 2, 2, 3, 3));
	}

	@Test
	public void itDoesNotFailIfListIsEmptyAndReturnsItUntouched() {
		try {
			flatten(Collections.EMPTY_LIST);
			assertTrue(flatten(Collections.EMPTY_LIST).isEmpty());
		} catch (Exception exception) {
			fail("No exception is expected to be thrown is we try to flatten an empty list");
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void failsWithANullPointerExceptionIfAskedToFlattenANullObject() {
		flatten((List<?>)null);
	}
	
}