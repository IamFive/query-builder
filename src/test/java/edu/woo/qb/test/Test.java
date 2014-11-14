package edu.woo.qb.test;

public class Test {

	/**
	 *
	 * @author Administrator
	 * 2010-10-19 下午04:57:00
	 * Test.java
	 * @param args
	 * TODO : 
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String propertyName = "2333[or:a]ddd";
		int groupBeginIndex = propertyName .indexOf('[');
		int groupEndIndex = propertyName.indexOf(']');
		if(groupBeginIndex != -1 && groupEndIndex != -1) {
			String temp = propertyName.substring(groupBeginIndex + 1, groupEndIndex);
			System.out.println(temp);
		}

	}

}
