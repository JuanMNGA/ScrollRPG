package com.scrollrpg.controller;

import com.scrollrpg.builder.MapBuilder;
import com.scrollrpg.builder.item.Map;

public class MapController {
	
	private MapBuilder map_builder;
	
	public MapController(){
		map_builder = new MapBuilder();
	}
	
	public Map getFirstMap(){
		return map_builder.getMaps().first();
	}
	
	public Map getNextMap(Map current, String cardinal){
		return map_builder.getMaps().get(map_builder.getMaps().indexOf(current, true)).getLink(cardinal);
	}

}
