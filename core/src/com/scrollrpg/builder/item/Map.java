package com.scrollrpg.builder.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Map {
	
	private Array<Platform> platforms = new Array<Platform>();
	private Map linkMapNorth = null, linkMapSouth = null, linkMapEast = null, linkMapWest = null;
	
	public Map(){
		
	}
	
	public void addPlatform(Platform platform){
		platforms.add(platform);
	}
	
	public Array<Platform> getPlatforms(){
		return platforms;
	}
	
	public void setLink(Map map, String cardinal){
		if(cardinal.toLowerCase().equals("east") && linkMapEast == null){
			linkMapEast = map;
		}else{
			if(cardinal.toLowerCase().equals("west") && linkMapWest == null){
				linkMapWest = map;
			}else{
				if(cardinal.toLowerCase().equals("north") && linkMapNorth == null){
					linkMapNorth = map;
				}else{
					if(cardinal.toLowerCase().equals("south") && linkMapSouth == null){
						linkMapSouth = map;
					}else{
						linkMapNorth = linkMapSouth = linkMapWest = linkMapEast = null;
					}
				}
			}
		}
	}
	
	public Map getLink(String cardinal){
		if(cardinal.toLowerCase().equals("east") && linkMapEast != null){
			return linkMapEast;
		}else{
			if(cardinal.toLowerCase().equals("west") && linkMapWest != null){
				return linkMapWest;
			}else{
				if(cardinal.toLowerCase().equals("north") && linkMapNorth != null){
					return linkMapNorth;
				}else{
					if(cardinal.toLowerCase().equals("south") && linkMapSouth != null){
						return linkMapSouth;
					}else{
						return null;
					}
				}
			}
		}
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		for(Platform plat : platforms){
			plat.draw(batch, parentAlpha);
		}
	}
}
