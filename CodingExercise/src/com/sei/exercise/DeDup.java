package com.sei.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeDup {

	private static final String BLANK = "";

	private static int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16,
			19, 1, 18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16,
			3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	public static void main(String[] args) {
		eliminateDuplicatesWithRetainedOrder();

		eliminateDuplicatesWithoutOrder();

		eliminateDuplicatesWithoutOrderAndWithDuplicateCount();
	}

	/*
	 * This method can be used to eliminate duplicates while retaining order of
	 * elements from source array.
	 *
	 * By changing return type to List<Integer>, This method is best suited for
	 * cases where index based get/set methods are used to access elements and
	 * output needs to be iterated on multiple times. This approach (as-is) does
	 * have downfall if output needs to be edited by deleting/inserting
	 * elements. To rectify that, Initialize uniqueIntegers as
	 * LinkedList<Integer> rather than ArrayList<Integer>
	 *
	 * Below approach can be when system wants to find out very first occurrence
	 * of a each incident/activity;
	 */
	private static void eliminateDuplicatesWithRetainedOrder() {
		List<Integer> uniqueIntegers = new ArrayList<Integer>();
		for (int i = 0; i < randomIntegers.length; i++) {
			if (uniqueIntegers.contains(randomIntegers[i])) {
				continue;
			}

			uniqueIntegers.add(randomIntegers[i]);
		}

		printOutput(convertCollectionToArray(uniqueIntegers));
	}

	/*
	 * This method can be used to eliminate duplicates when order of elements is
	 * not important.
	 *
	 * Set collection implementation is based on hash codes. So accessing of
	 * elements is much faster. Downside of using Set interface is that elements
	 * can not be accessed using index as the case with List interface.
	 *
	 * If elements order is a must and indexed access is not needed, then
	 * initialize uniqueIntegers as LinkedHashSet. If sorting is needed instead
	 * of ordering TreeSet can be used as implementation class.
	 *
	 * Below approach can be when system wants to find out all
	 * activities/incidents that have happened at least once.
	 */
	private static void eliminateDuplicatesWithoutOrder() {
		Set<Integer> uniqueIntegers = new HashSet<Integer>();
		for (int i = 0; i < randomIntegers.length; i++) {
			if (uniqueIntegers.contains(randomIntegers[i])) {
				continue;
			}

			uniqueIntegers.add(randomIntegers[i]);
		}

		printOutput(convertCollectionToArray(uniqueIntegers));
	}

	/*
	 * In addition to functionality offered by eliminateDuplicatesWithoutOrder,
	 * this method also has capability to return number of occurrences for each
	 * element inside original array. LinkedHashMap implementation class can be
	 * used if ordering is important. TreeMap implementation class can be used
	 * if sorting is important.
	 *
	 * Below approach can be used when system wants to find out all
	 * activities/incidents that have happened at least once. On top of that,
	 * system can also find out about number of occurrences for each.
	 */
	private static void eliminateDuplicatesWithoutOrderAndWithDuplicateCount() {
		Map<Integer, Integer> uniqueIntegersWithOccurancesCount = new HashMap<Integer, Integer>();
		Set<Integer> uniqueIntegers = new HashSet<Integer>();
		for (int i = 0; i < randomIntegers.length; i++) {
			if (uniqueIntegers.contains(randomIntegers[i])) {
				int oldCount = uniqueIntegersWithOccurancesCount.get(randomIntegers[i]);
				uniqueIntegersWithOccurancesCount.put(randomIntegers[i], oldCount++);

			} else {
				uniqueIntegersWithOccurancesCount.put(randomIntegers[i], 1);
			}
		}

		printOutput(convertCollectionToArray(uniqueIntegersWithOccurancesCount.keySet()));
	}

	private static int[] convertCollectionToArray(Collection<Integer> integers) {
		int[] outputArray = new int[integers.size()];
		Iterator<Integer> iterator = integers.iterator();

		int index = 0;
		while (iterator.hasNext()) {
			outputArray[index] = iterator.next();
			index++;
		}

		return outputArray;
	}

	private static void printOutput(int[] output) {
		String separator = BLANK;
		StringBuilder builder = new StringBuilder();
		for (int i : output) {
			builder.append(separator);
			builder.append(i);

			if (separator == BLANK) {
				separator = ", ";
			}
		}

		System.out.println(builder);
	}
}