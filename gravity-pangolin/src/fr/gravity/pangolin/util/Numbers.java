package fr.gravity.pangolin.util;

public class Numbers {
	
	public static boolean between(float x, float n1, float n2) {
		if (x >= n1 && x <= n2)
			return true;
		return false;
	}
	
	public static boolean betweenStrict(float x, float n1, float n2) {
		if (x > n1 && x < n2)
			return true;
		return false;
	}
	
}
