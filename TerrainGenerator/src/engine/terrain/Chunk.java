package engine.terrain;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import engine.graphics.EnumTexture;
import engine.graphics.Renderer;
import engine.math.Vector3f;
import engine.objects.Block;
import engine.objects.Camera;
import engine.objects.Player;
import main.Main;

public class Chunk {
	public static final float seaLevel = 60.0f;
	private Block[][][] chunkTerrain;
	private Vector3f startingPos;
	private boolean active;
	private boolean initialized;
	
	public Chunk(Vector3f sP) {
		if(chunkTerrain == null) {
			chunkTerrain = new Block[16][16][128];
			startingPos = sP;
			active = true;
			initialized = false; //If it has ever been rendered.
		}
	}
	
	public void generateChunk(Renderer renderer) {
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
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if(chunkTerrain[x][z][y] != null && surrounded(x, z, y) && chunkTerrain[x][z][y].getDistance(Player.getPosition()) <= Player.getFOV()) {
						chunkTerrain[x][z][y].setToRender(true);
						renderer.updateBatch(chunkTerrain[x][z][y]);
						
					}
				}
			}
		}
	}
	
	public void renderChunk(Renderer renderer, int index) {
		if(active) {
			for(int x = 0; x < chunkTerrain.length; x++) {
				for(int z = 0; z < chunkTerrain[x].length; z++) {
					for(int y = 0; y < chunkTerrain[x][z].length; y++) {
						if(chunkTerrain[x][z][y] != null && surrounded(x, z, y) && chunkTerrain[x][z][y].getDistance(Player.getPosition()) <= Player.getFOV()) {
							chunkTerrain[x][z][y].setToRender(true);
							renderer.updateBatch(chunkTerrain[x][z][y]);
						}
						else if(chunkTerrain[x][z][y] != null){
							chunkTerrain[x][z][y].setToRender(false);
							renderer.updateBatch(chunkTerrain[x][z][y]);
						}
					}
				}
			}
			renderer.render();
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
	
	public Block[][][] getChunk(){
		return chunkTerrain;
	}
	
	public Vector3f getStartingPos() {
		return startingPos;
	}
	
	private Vector3f indexFromPos(Block b) {
		Vector3f pos = b.getPosition();
		return new Vector3f(pos.getX() - startingPos.getX(), pos.getY() - startingPos.getY(), pos.getZ() - startingPos.getZ());
	}
	
	private boolean surrounded(int x, int z, int y) {
		if(!(x > 0 && z > 0 && x < 15 && z < 15 && y > 0 && y < 127))
			return true;	
		return (chunkTerrain[x - 1][z][y] == null || chunkTerrain[x + 1][z][y] == null || chunkTerrain[x][z - 1][y] == null || chunkTerrain[x][z + 1][y] == null || chunkTerrain[x][z][y - 1] == null || chunkTerrain[x][z][y + 1] == null);
	}
	
	private boolean isVisible(Vector3f blockPos) {
		Vector3f diffVector = Vector3f.subtract(blockPos, Player.getPosition());
		//System.out.println((Vector3f.dot(Player.getPosition(), blockPos) / (Vector3f.magnitude(Player.getPosition()) * Vector3f.magnitude(blockPos)) > 0));
		//Check angle between player and block (greater than 70 it shouldn't be rendered)
		return diffVector.getX() > 0 && diffVector.getZ() > 0;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chunk other = (Chunk) obj;
		if (!Arrays.deepEquals(chunkTerrain, other.chunkTerrain))
			return false;
		if (startingPos == null) {
			if (other.startingPos != null)
				return false;
		} else if (!startingPos.equals(other.startingPos))
			return false;
		return true;
	}
	
	
	
}
