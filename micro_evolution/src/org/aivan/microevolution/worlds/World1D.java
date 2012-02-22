package org.aivan.microevolution.worlds;

import java.util.ArrayList;

import org.aivan.microevolution.worlds.points.Point;
import org.aivan.microevolution.worlds.points.SimplePoint;



public class World1D extends World {

	private static final long POINT_COUNT = 100;
	@Override
	public void init() {
		// Create points
		points = new ArrayList<Point>();
		for(long i=0;i<POINT_COUNT;i++) {
			points.add(new SimplePoint(this));
		}
		
		// Create food
		foodFactory.init();
		
		//Create lifeForms:
		lifeFormFactory.init();
		
	}

	
			
}
