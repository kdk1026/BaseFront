package com.infoin.basicTest;

import com.infoin.app.common.util.PagingUtil;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 2. 10. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
public class PagingTest {

	public static void main(String[] args) {
		PagingUtil pagingUtil = new PagingUtil(12, 5, 110, "2", "");

		System.out.println(pagingUtil.toString());
	}

}
