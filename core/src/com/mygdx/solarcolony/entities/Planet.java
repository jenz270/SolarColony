package com.mygdx.solarcolony.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet {
	private int x;
	private int y;
	private int radius;
	private int faction;
	private int totalPop;
	private int curPop;
	private boolean isSelected;
	
	public Planet(int x, int y, int radius, int faction)
	{
		this.isSelected = false;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.faction = faction;
		this.totalPop = (int) (radius * 1.2) + 500;
		this.curPop = totalPop/2;
	}
	
	//automatic function for population regeneration 
	public int updatePop()
	{
		curPop += 2;
		if(curPop >= totalPop)
			curPop = totalPop;
		
		return curPop;
	}
	
	//collision function takes a ship as a parameter and adjusts planet population accordingly
	public int collision(Ship ship){
		if(ship.getFaction() != faction){
			curPop -= 50;
			if(curPop <= 0){
				faction = ship.getFaction();
				curPop = (int)(totalPop * 0.25);
			}
		} else if(ship.getFaction() == faction){
			curPop += 50;
			if(curPop >= totalPop)
				curPop = totalPop;
		}
		return curPop;
	}
	
	public void changeFac(int new_faction)
	{
		faction = new_faction;
	}
	
	public int getFac()
	{
		return faction;
	}
	
	public int shipLaunch()
	{
		return 0;
	}
	
	public void setSelected(boolean select)
	{
		this.isSelected = select;
	}
	
	public boolean isSelected()
	{
		return this.isSelected;
	}
	
	public void draw(ShapeRenderer sr)
	{
		if(faction == 0){
			sr.setColor(1, 1, 1, 1);
		} else if(faction == 1){
			sr.setColor(0, 1, 0, 1);
		} else if(faction == 2){
			sr.setColor(1, 0, 0, 1);
		} else if (faction == 3){
			sr.setColor(0, 1, 0, 1);
		}
		
		sr.begin(ShapeType.Filled);
		sr.circle(x, y, radius);
		sr.end();
		
		if(isSelected){
			sr.begin(ShapeType.Line);
			sr.circle(x, y, radius+10);
			sr.end();
		}
		
		
	}
	
	public boolean contains(float px, float py)
	{
		if(Math.pow((px-x), 2) + Math.pow((py-y), 2) < Math.pow(radius, 2))
			return true;
		else
			return false;
	}
	
}