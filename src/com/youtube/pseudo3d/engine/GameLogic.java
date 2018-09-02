package com.youtube.pseudo3d.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.youtube.pseudo3d.engine.objects.GameObject;
import com.youtube.pseudo3d.engine.objects.collect.AxeCollect;
import com.youtube.pseudo3d.engine.objects.collect.LatternCollect;
import com.youtube.pseudo3d.engine.objects.collect.SwordCollect;
import com.youtube.pseudo3d.engine.objects.collect.WandCollect;
import com.youtube.pseudo3d.engine.objects.missle.WandMissle;
import com.youtube.pseudo3d.engine.objects.still.Barrel;
import com.youtube.pseudo3d.engine.objects.still.Pillar;
import com.youtube.pseudo3d.engine.objects.still.Spider;
import com.youtube.pseudo3d.gui.Gui;
import com.youtube.pseudo3d.main.Main;
import com.youtube.pseudo3d.resource.TextureHolder;
import com.youtube.pseudo3d.resource.TextureHolder.ID;
import com.youtube.pseudo3d.util.Constants;
import com.youtube.pseudo3d.util.Vector2d;

public class GameLogic {

	public static final int TEST_MAP_TEXTURE_WIDTH = 64;
	public static final int TEST_MAP_TEXTURE_HEIGHT = 64;
	
	private Camera camera;
	private Player player;
	private Rayprojector rayprojector;
	
	private BufferedImage screen;
	
	private Main main;

	private ArrayList<GameObject> gameObjects;
	
	private Gui gui;
	
	public GameLogic(Main main) {
		this.main = main;
		initTextures();
		initGameObjects();
		initScreen();
		initRaycastingFields();
		initGui();
	}
	
	private void initTextures() {
		generateTextures();
	}
	
	private void generateTextures() {
		TextureHolder.load(ID.TEST_MAP, 		"/maps/test_map.png");
		TextureHolder.load(ID.EMBLEM, 			"/tiles/emblem.png");
		TextureHolder.load(ID.BRICK_0, 			"/tiles/brick_0.png");
		TextureHolder.load(ID.BRICK_1, 			"/tiles/brick_1.png");
		TextureHolder.load(ID.PURPLESTONE, 		"/tiles/purplestone.png");
		TextureHolder.load(ID.BLUESTONE, 		"/tiles/bluestone.png");
		TextureHolder.load(ID.MOSSYSTONE, 		"/tiles/mossystone.png");
		TextureHolder.load(ID.WOOD, 			"/tiles/wood.png");
		TextureHolder.load(ID.COBBLESTONE, 		"/tiles/cobblestone.png");
		
		TextureHolder.load(ID.BARREL, 			"/sprites/barrel.png");
		TextureHolder.load(ID.PILLAR, 			"/sprites/pillar.png");
		TextureHolder.load(ID.SPIDER, 			"/sprites/spider.png");
		
		TextureHolder.load(ID.WAND_MISSLE,  	"/sprites/missle/wand-missle.png");
		
		TextureHolder.load(ID.LATTERN_COLLECT,  "/sprites/collect/lattern_collect.png");
		TextureHolder.load(ID.SWORD_COLLECT,  	"/sprites/collect/sword_collect.png");
		TextureHolder.load(ID.AXE_COLLECT,  	"/sprites/collect/axe_collect.png");
		TextureHolder.load(ID.WAND_COLLECT,  	"/sprites/collect/wand_collect.png");
		
		TextureHolder.load(ID.PLAYER_LATTERN,	"/player/lattern/lattern_hand.png");
		TextureHolder.load(ID.PLAYER_SWORD,		"/player/sword/sword_hand.png");
		TextureHolder.load(ID.PLAYER_SWORD_ATTACK,"/player/sword/sword_attack.png");
		TextureHolder.load(ID.PLAYER_AXE,		"/player/axe/axe_hand.png");
		TextureHolder.load(ID.PLAYER_WAND,		"/player/wand/wand_hand.png");
		TextureHolder.load(ID.PLAYER_WAND_ATTACK,"/player/wand/wand_attack.png");
		
		TextureHolder.load(ID.GUI_EMPTY_SLOT,	"/gui/empty-slot.png");
		TextureHolder.load(ID.GUI_SELECTED_SLOT,"/gui/selected-slot.png");
		TextureHolder.load(ID.GUI_HEALTH_BAR,   "/gui/health-bar.png");
		TextureHolder.load(ID.GUI_LATTERN_ICON, "/gui/lattern-icon.png");
		TextureHolder.load(ID.GUI_SWORD_ICON, 	"/gui/sword-icon.png");
		TextureHolder.load(ID.GUI_AXE_ICON, 	"/gui/axe-icon.png");
		TextureHolder.load(ID.GUI_WAND_ICON, 	"/gui/wand-icon.png");
	}
	
	private void initGameObjects() {
		gameObjects = new ArrayList<GameObject>();
			
		gameObjects.add(new Barrel(this, new Vector2d(16.5, 22.5)));
		gameObjects.add(new Spider(this, new Vector2d(17.5, 21.5)));
		gameObjects.add(new Barrel(this, new Vector2d(17.5, 18.5)));

		gameObjects.add(new Pillar(this, new Vector2d(22.5, 13.5)));
		gameObjects.add(new Barrel(this, new Vector2d(22.5, 7.5)));
		gameObjects.add(new Pillar(this, new Vector2d(22.5, 6.5)));

		gameObjects.add(new Spider(this, new Vector2d(22.5, 2.5)));
		gameObjects.add(new Barrel(this, new Vector2d(22.5, 1.5)));
		gameObjects.add(new Spider(this, new Vector2d(21.5, 1.5)));
		
		gameObjects.add(new Spider(this, new Vector2d(15.5, 13.5)));
		gameObjects.add(new Barrel(this, new Vector2d(16.5, 13.5)));
		gameObjects.add(new Barrel(this, new Vector2d(17.5, 15.5)));
		gameObjects.add(new Barrel(this, new Vector2d(19.5, 14.5)));
		
		gameObjects.add(new Barrel(this, new Vector2d(8.5, 14.5)));
		gameObjects.add(new Spider(this, new Vector2d(7.5, 15.5)));
		gameObjects.add(new Barrel(this, new Vector2d(2.5, 14.5)));
		gameObjects.add(new Spider(this, new Vector2d(2.5, 13.5)));
		gameObjects.add(new Pillar(this, new Vector2d(1.5, 12.5)));
		gameObjects.add(new Spider(this, new Vector2d(5.5, 22.5)));
		gameObjects.add(new Pillar(this, new Vector2d(9.5, 22.5)));
		gameObjects.add(new Pillar(this, new Vector2d(10.5,22.5)));
		gameObjects.add(new Spider(this, new Vector2d(6.5, 21.5)));
		gameObjects.add(new Barrel(this, new Vector2d(6.5, 20.5)));
		
		gameObjects.add(new LatternCollect(this, new Vector2d(18.5, 18.5)));
		gameObjects.add(new SwordCollect(this, new Vector2d(17.5, 17.5)));
		gameObjects.add(new AxeCollect(this, new Vector2d(16.5, 16.5)));
		gameObjects.add(new WandCollect(this, new Vector2d(15.5, 15.5)));
	}
	
	private void initScreen() {
		screen = new BufferedImage(Constants.RESOLUTION_WIDTH, Constants.RESOLUTION_HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	private void initRaycastingFields() {
		camera = new Camera();
		player = new Player(this);
		rayprojector = new Rayprojector(this);
	}
	
	private void initGui() {
		gui = new Gui(this);
	}
	
	public void handleInput(double elapsed) {
		player.handleInput(elapsed);
	}
	
	public void update(double elapsed) {
		// UPDATE SCREEN SIZE DEPENDING ON WINDOW SIZE
		screen = new BufferedImage(Constants.RESOLUTION_WIDTH, Constants.RESOLUTION_HEIGHT, BufferedImage.TYPE_INT_RGB);
		rayprojector.projectRays();
		player.update(elapsed);
		gui.update(elapsed);
		
		updateGameObjects(elapsed);
		updateWallCollisions();
		
		updatePickupLattern();
		updatePickupSword();
		updatePickupAxe();
		updatePickupWand();
	}
	
	private void updateGameObjects(double elapsed) {
		for(int i=0; i<gameObjects.size(); i++)
			gameObjects.get(i).update(elapsed);
	}
	
	private void updateWallCollisions() {
		for(int i=0; i<gameObjects.size(); i++)
			if(gameObjects.get(i) instanceof WandMissle
					&& TextureHolder.get(ID.TEST_MAP).getRGB((int) (gameObjects.get(i).getPosition().x),
							(int) (gameObjects.get(i).getPosition().y)) != 0xff000000) {
				gameObjects.remove(i);
				i--;
			}
	}
	
	private void updatePickupLattern() {
		for(int i=0; i<gameObjects.size(); i++)
			if(gameObjects.get(i) instanceof LatternCollect
					&& Math.floor(gameObjects.get(i).getPosition().x) == Math.floor(player.getPosition().x)
					&& Math.floor(gameObjects.get(i).getPosition().y) == Math.floor(player.getPosition().y)) {
				Items.unlocked.put(Items.Holding.LATTERN, true);
				gameObjects.remove(i);
				i--;
			}
	}
	
	private void updatePickupSword() {
		for(int i=0; i<gameObjects.size(); i++)
			if(gameObjects.get(i) instanceof SwordCollect
					&& Math.floor(gameObjects.get(i).getPosition().x) == Math.floor(player.getPosition().x)
					&& Math.floor(gameObjects.get(i).getPosition().y) == Math.floor(player.getPosition().y)) {
				Items.unlocked.put(Items.Holding.SWORD, true);
				gameObjects.remove(i);
				i--;
			}
	}
	
	private void updatePickupAxe() {
		for(int i=0; i<gameObjects.size(); i++)
			if(gameObjects.get(i) instanceof AxeCollect
					&& Math.floor(gameObjects.get(i).getPosition().x) == Math.floor(player.getPosition().x)
					&& Math.floor(gameObjects.get(i).getPosition().y) == Math.floor(player.getPosition().y)) {
				Items.unlocked.put(Items.Holding.AXE, true);
				gameObjects.remove(i);
				i--;
			}
	}
	
	private void updatePickupWand() {
		for(int i=0; i<gameObjects.size(); i++)
			if(gameObjects.get(i) instanceof WandCollect
					&& Math.floor(gameObjects.get(i).getPosition().x) == Math.floor(player.getPosition().x)
					&& Math.floor(gameObjects.get(i).getPosition().y) == Math.floor(player.getPosition().y)) {
				Items.unlocked.put(Items.Holding.WAND, true);
				gameObjects.remove(i);
				i--;
			}
	}
	
	public void render(Graphics g) {
		g.drawImage(screen, 0, 0, main.getWidth(), main.getHeight(), null);
		player.render(g);
		gui.render(g);
		
		//DEBUG INFO
		g.setColor(Color.GRAY);
		g.drawString(
				"x = " + Math.floor(player.getPosition().x) + 
				", y = " + Math.floor(player.getPosition().y) +
				", direction = (" + (player.getDirection().x) + ", " + (player.getDirection().y) + ")"
				, 100, 20);
	}

	public Main getMain() {
		return main;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BufferedImage getScreen() {
		return screen;
	}

	public void setScreen(BufferedImage screen) {
		this.screen = screen;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}	
	
	
}
