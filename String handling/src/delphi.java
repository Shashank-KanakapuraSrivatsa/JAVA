import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map.Entry;
import java.io.BufferedReader;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class delphi {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		File file = new File("input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;

			// to read each line in the input, into arrayList
			while ((line = br.readLine()) != null) {
				arrayList.add(line);
			}

			// to print the input
			System.out.println("The given input is : ");
			for (String handle : arrayList) {
				System.out.println(handle);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please try with correct input file");
		} catch (IOException e) {
			System.out.println("Unable to read the input file");
		}
		performOperation(arrayList);
	}

	public static Multimap<String, String> performOperation(ArrayList<String> arrayList) {
		ArrayList<String> addressList = new ArrayList<String>();
		ArrayList<String> technologyList = new ArrayList<String>();
		Multimap<String, String> initialMap = ArrayListMultimap.create();
		Multimap<String, String> finalMap = ArrayListMultimap.create();
		ArrayList<String> keysList = new ArrayList<String>();
		String[] individualLine = null;
		String address = null;

		// each line is read and split into strings. Then classify whether each string
		// is a website address or a technology
		// A separate arraylist is created for both the technolgies used and the website
		// addresses
		// Additionally, the mapping of webiste and technologies used, is put in an
		// inital multimap
		for (String handle : arrayList) {
			individualLine = handle.split("\\s->\\s|\\s,|,\\s");
			for (String a : individualLine) {
				if (a.contains("https")) {
					addressList.add(a);
					address = a;
				} else {
					if (!technologyList.contains(a)) {
						technologyList.add(a);
					}
					initialMap.put(address, a);
				}
			}
		}

		/*
		 * given input is mapped into an initial multimap. Use this part of the code to
		 * print initial map
		 * System.out.println("-------------initial map-------------------"); for
		 * (Entry<String, Collection<String>> e : initialMap.asMap().entrySet()) {
		 * System.out.println(e.getKey() + " -> " + e.getValue());
		 * 
		 * } System.out.println("-----------------------------------");
		 */

		/*
		 * to print the websites used
		 * System.out.println("-------------address list-------------------"); for
		 * (String handle : addressList) { System.out.println(handle); }
		 * System.out.println("-----------------------------------");
		 */

		/*
		 * To print the list of all technologies used
		 * System.out.println("-------------technology list-------------------"); for
		 * (String handle : technologyList) { System.out.println(handle); }
		 * System.out.println("-----------------------------------");
		 */

		String finalKey = null;

		// treating the website addressed as keys and technologies as values
		// for each of the technology, we fetch the websites that contain them.
		// This websites for each technology is put into an arraylist, and this
		// arraylist is refreshed everytime we consider a unique technology
		for (String technologyHandle : technologyList) {
			for (Entry<String, String> entry : initialMap.entries()) {
				if (entry.getValue().equals(technologyHandle)) {
					if (!keysList.contains(entry.getKey())) {
						keysList.add(entry.getKey());
					}
				}
			}

			// Use this part of the code to check the websites that contain each of the
			// technologies
//			System.out.println(" ");
//			System.out.println("Websites that contain " + technologyHandle + " : ");
//			Collections.sort(keysList);
//			for (String keys : keysList) {
//				System.out.println(keys);
//			}

			// for the sake of easy string comparison, the keysList is sorted in ascending
			// order
			Collections.sort(keysList);

			// below logic is to determine which is the most suitable website for a
			// technology. This is added into finalKey and then into final multimap
			for (int i = 0; i < keysList.size(); i++) {
				finalKey = keysList.get(0);
				if (finalKey == keysList.get(i)) {
					finalKey = keysList.get(i);
				} else if (finalKey.contains(keysList.get(i)) || keysList.get(i).contains(finalKey)) {
					if (finalKey.length() < keysList.get(i).length()) {
						finalKey = finalKey; // this will not have any effect. Just for the sake of readability, this
												// assignment is used
					} else {
						finalKey = keysList.get(i);
					}
				} else {
					if (!finalMap.containsEntry(finalKey, technologyHandle)) {
						finalMap.put(finalKey, technologyHandle);
					} 
					if(!finalMap.containsEntry(keysList.get(i), technologyHandle)) {
						finalMap.put(keysList.get(i), technologyHandle);
					}
				}

			}
			if (!finalMap.containsEntry(finalKey, technologyHandle)) {
				finalMap.put(finalKey, technologyHandle);
			}
			keysList.clear();
		}

		// to print the output
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("                                OUTPUT                                     ");
		System.out.println("---------------------------------------------------------------------------");

		for (Entry<String, Collection<String>> e : finalMap.asMap().entrySet()) {
			System.out.println(e.getKey() + " -> " + e.getValue());
		}
		return finalMap;
	}
}