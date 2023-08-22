package com.infoin.basicTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 4. 25. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
public class ReadFile {

	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader("c:/test/test.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		List<String> list = new ArrayList<>();

		String str = "";
		while ( (str = bufferedReader.readLine()) != null ) {
			list.add(str);
		}
		bufferedReader.close();

		StringBuilder sb = new StringBuilder();
		for (int i=0; i < list.size(); i++) {
			if ( i == list.size() -1 ) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i) + "\n");
			}
		}

		System.out.println(sb.toString());

	}

}
