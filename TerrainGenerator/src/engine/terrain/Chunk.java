package engine.terrain;

import engine.graphics.EnumTexture;
import engine.graphics.Renderer;
import engine.math.Vector3f;
import engine.objects.Block;
import engine.objects.Camera;
import engine.objects.Player;

public class Chunk {
	public static final float seaLevel = 60.0f;
	private Block[][][] chunkTerrain;
	private Vector3f startingPos;
	
	public Chunk(Vector3f sP) {
		if(chunkTerrain == null) {
			chunkTerrain = new Block[16][16][128];
			startingPos = sP;
		}
	}
	
	public void generateChunk() {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if(y < seaLevel) {
						chunkTerrain[x][z][y] = new Block(new Vector3f(startingPos.getX() + x, startingPos.getY() + y, startingPos.getZ() + z), new Vector3f(0,0,0), new Vector3f(1,1,1), EnumTexture.STONE);
						chunkTerrain[x][z][y].getMesh().create();
					}
				}
			}
		}
	}
	
	public void renderChunk(Renderer renderer, Camera camera) {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if(chunkTerrain[x][z][y] != null && surrounded(x, z, y) && chunkTerrain[x][z][y].getDistance(Player.getPosition()) <= Player.getFOV())
						renderer.renderMesh(chunkTerrain[x][z][y], camera);						
				}
			}
		}
	}
	
	public void destroy() {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if(chunkTerrain[x][z][y] != null)
						chunkTerrain[x][z][y].getMesh().destroy();
				}
			}
		}
		chunkTerrain = null;
	}
	
	public boolean surrounded(int x, int z, int y) {
		if(!(x > 0 && z > 0 && x < 15 && z < 15 && y > 0 && y < 127))
			return true;	
		return (chunkTerrain[x - 1][z][y] == null || chunkTerrain[x + 1][z][y] == null || chunkTerrain[x][z - 1][y] == null || chunkTerrain[x][z + 1][y] == null || chunkTerrain[x][z][y - 1] == null || chunkTerrain[x][z][y + 1] == null);
	}
	
}
