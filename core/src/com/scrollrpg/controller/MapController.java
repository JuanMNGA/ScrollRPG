package com.scrollrpg.controller;

import com.scrollrpg.builder.MapBuilder;
import com.scrollrpg.builder.item.Map;
import com.scrollrpg.utils.AssetsUtils;

public class MapController {
	
	private MapBuilder map_builder;
	
	private AssetsUtils assets;
	
	public MapController(AssetsUtils assets){
		this.assets = assets;
		map_builder = new MapBuilder(this.assets);
	}
	
	public Map getFirstMap(){
		return map_builder.getMaps().first();
	}
	
	public Map getNextMap(Map current, String cardinal){
		return map_builder.getMaps().get(map_builder.getMaps().indexOf(current, true)).getLink(cardinal);
	}

}
