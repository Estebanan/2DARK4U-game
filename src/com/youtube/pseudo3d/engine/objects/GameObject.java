package com.youtube.pseudo3d.engine.objects;

import java.awt.image.BufferedImage;

import com.youtube.pseudo3d.logic.GameLogic;
import com.youtube.pseudo3d.util.Vector2d;

public abstract class GameObject {

	protected Vector2d position;
	protected GameLogic raycaster;
	
	protected BufferedImage texture;
	
	public boolean dying;
	public boolean dead;
	public boolean moving;
	public int health;
	
	public GameObject(GameLogic raycaster, Vector2d position) {
		this.raycaster = raycaster;
		this.position = position;
	}
	
	public abstract void update(double elapsed);
	
	public Vector2d getPosition() {
		return position;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
