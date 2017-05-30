package com.scrollrpg.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.scrollrpg.builder.item.*;
import com.scrollrpg.utils.AssetsUtils;

public class MapBuilder {
	
	private Array<Map> MapList = new Array<Map>();
	
	private AssetsUtils assets;
	
	public MapBuilder(AssetsUtils assets){
		this.assets = assets;
		createMaps();
	}
	
	private void createMaps(){
		// Map 1
		Map tmp_map = new Map();
		Platform tmp_platform = new Platform();
		tmp_map.addPlatform(new Platform(0, 0, 500, 100, assets.getManager().get("textures/bricks.png", Texture.class)));
		tmp_map.addPlatform(new Platform(500, 100, 500, 100, assets.getManager().get("textures/bricks.png", Texture.class)));
		tmp_map.addPlatform(new Platform(480, 360, 200, 100, assets.getManager().get("textures/bricks.png", Texture.class)));
		MapList.add(tmp_map);
	}
	
	public Array<Map> getMaps(){
		return MapList;
	}

}
