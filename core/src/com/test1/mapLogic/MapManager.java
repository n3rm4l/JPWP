package com.test1.mapLogic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.test1.Constants;

public class MapManager {

	TmxMapLoader mapLoader = new TmxMapLoader();
	TiledMap map = mapLoader.load("Map/mapa1.tmx");
	OrthogonalTiledMapRenderer mapRenderer = new OrthogonalTiledMapRenderer(map, 2);	//do modyfikacji w przypadku użycia kilku map
	//TiledMapTileLayer colisionLayer = (TiledMapTileLayer) map.getLayers().get("walls");
	OrthographicCamera camera= new OrthographicCamera();
	private int[][] collisionMatrix;
    
    
	/*
	public TiledMap loadMap(String fileName) {
	    TmxMapLoader mapLoader = new TmxMapLoader();
	    return mapLoader.load(fileName);
	}
	//kod rozwojowy - wykorzystanie więcej niż 1 mapy
	*/
    public MapManager(){
    	camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
    	camera.update();
    	processCollision((TiledMapTileLayer) map.getLayers().get("walls"));
    	mapRenderer.setView(camera);
    	

    }
    private void processCollision(TiledMapTileLayer layer) {
	    // Przetwarzanie mapy zw z ustto awianiem kolizji 
    	collisionMatrix = new int[layer.getWidth()][layer.getHeight()];
    	 for (int row = 0; row < layer.getHeight(); row++) {
    	        for (int col = 0; col < layer.getWidth(); col++) {
    	            TiledMapTileLayer.Cell cell = layer.getCell(col, row);
    	            if (cell != null) {
    	                collisionMatrix[col][row] = 1;
    	            } else {
    	                collisionMatrix[col][row] = 0;
    	            }
    	        }
    	    }
    	 
			/*
			 * System.out.println("Test kolizji"); for (int row = collisionMatrix[0].length;
			 * row > 0; row--) { for (int col = 0; col < collisionMatrix.length; col++) {
			 * System.out.print(collisionMatrix[col][row-1] + "\t"); } System.out.println();
			 * }
			 */
		
	}
    public int collisionControl(int x, int y) {
	    return collisionMatrix[x][y];
    }
    
	public void renderMap() {
	    mapRenderer.render();
	}
	
	public void renderLayer() {
		mapRenderer.setMap(map);
		mapRenderer.getBatch().begin();
		//mapRenderer.renderTileLayer(); // Renderuj tylko jedną warstwę -> podaj jej nazwę
		mapRenderer.getBatch().end();
	}
}
