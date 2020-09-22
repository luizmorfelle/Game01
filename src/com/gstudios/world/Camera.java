package com.gstudios.world;

public class Camera {

	public static int x = 0;
	public static int y = 0;
	
	public static int clamp(int max, int atual, int min) {
		if(atual > max)
			atual = max;
		
		if(atual<min)
			atual = min;
		
		return atual;
	} 
	
}
