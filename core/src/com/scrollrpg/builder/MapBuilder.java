package com.scrollrpg.builder;

import com.badlogic.gdx.utils.Array;
import com.scrollrpg.builder.item.*;

public class MapBuilder {
	
	private Array<Map> MapList = new Array<Map>();
	
	public MapBuilder(){
		createMaps();
	}
	
	private void createMaps(){
		
	}
	
	public Array<Map> getMaps(){
		return MapList;
	}

}
