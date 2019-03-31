import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


class testCase5 {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		ArrayList<String> compareList = new ArrayList<String>();
		Multimap<String, String> compareMap = ArrayListMultimap.create();
		Multimap<String, String> resultMap = ArrayListMultimap.create();


		File file = new File("test5.txt");
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
		
		resultMap = delphi.performOperation(arrayList);
	
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/jml", compareList);
		
		compareList.clear();
		
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/kml", compareList);
		
		compareList.clear();
		
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/lml", compareList);
		
		compareList.clear();
		
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/xml", compareList);
		
		compareList.clear();
		
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/yml", compareList);
		
		compareList.clear();
		
		compareList.add("Apache");
		compareList.add("Joomla");
		compareList.add("PHP4");
		compareList.add("AngularJS");
		compareMap.putAll("https://upb.de/site/zml", compareList);
		
		compareList.clear();
				
		compareList.add("Drupal");
		compareList.add("Apache");
		compareList.add("PHP5");
		compareList.add("RedHat");
		compareMap.putAll("https://upb.de/site/drupal", compareList);
		
		boolean result =  assertEquals(compareMap,resultMap);
		if(result == true) {
			System.out.println("test case5 PASSED!!");
		}
		else {
			System.out.println("test case5 FAILED!!");
		}
	}

	private static boolean assertEquals(Multimap<String, String> compareMap, Multimap<String, String> resultMap) {
		boolean equality = true;
		String key = null;
		Collection<String> value = null;
		
		for (Entry<String, Collection<String>> e : resultMap.asMap().entrySet()) {
			key = e.getKey();
			value = e.getValue();
			if(!compareMap.containsKey(key) && !compareMap.containsValue(value)) {
				equality = false;
			}
		}
		return equality;
	}
}
