package com.intercom.interview.flattener;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	
	/**
	 * Flattens a structure of nested lists and returns it in a new object.
	 * <p>The structure of lists is traversed Depth-first search (DFS).
	 * <br>
	 * E.g.: <i>[1, [2], [3, 4], 5] --> [1, 2, 3, 4, 5]</i>  
	 * <p>If an element is collection but not IS-A {@link List} it is treated like it was was a primitive object.
	 * 
	 * <p><b>WARN:</b> this method is recursive, so it could fail with a {@link StackOverflowError} the level of nesting is extremely deep  
	 * 
	 * @param listToFlatten the structure of lists to be flattened
	 * @return a new list containing the flattened version of the structure of lists passed in input 
	 */
	public static List<?> flatten(List<?> listToFlatten) {
		List<?> flattenedList = new ArrayList<Object>();
		flatten(listToFlatten, flattenedList);
		return flattenedList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void flatten(List listToFlatten, List listFlattened) {
		for (Object currentItem : listToFlatten) {
			if (currentItem instanceof List<?>) {
				flatten((List<Object>)currentItem, listFlattened);
			} else {
				listFlattened.add(currentItem);
			}
		}
	}
	
}
