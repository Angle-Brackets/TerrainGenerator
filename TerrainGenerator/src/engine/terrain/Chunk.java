package engine.terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import engine.graphics.EnumTexture;
import engine.graphics.Renderer;
import engine.math.Vector3f;
import engine.objects.Block;
import engine.objects.BlockType;
import engine.objects.Camera;
import engine.objects.Player;
import main.Main;

public class Chunk {
	public static final float seaLevel = 60.0f;
	private BlockType[][][] chunkTerrain;
	private boolean[][][] chunkCull;
	private List<BlockType> blocksInChunk; //List of all of the blocks in a chunk for batch rendering.
	private Vector3f startingPos;
	private boolean active;
	
	public Chunk(Vector3f sP) {
		if(chunkTerrain == null) {
			chunkTerrain = new BlockType[16][16][128];
			chunkCull = new boolean[16][16][128];
			startingPos = sP;
			blocksInChunk = new ArrayList<>();
			active = true;
		}
	}
	
	public void generateChunk(Renderer renderer) {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if (y < seaLevel) {
						chunkTerrain[x][z][y] = y == 59 ? BlockType.GRASS_BLOCK : y > 56 ? BlockType.DIRT : BlockType.STONE_BLOCK;
						
						if(chunkTerrain[x][z][y] != null && !blocksInChunk.contains(chunkTerrain[x][z][y])) {
							blocksInChunk.add(chunkTerrain[x][z][y]);
						}
					}
				}
			}
		}

		for(int x = 0; x < chunkCull.length; x++) {
			for(int z = 0; z < chunkCull[x].length; z++) {
				for(int y = 0; y < chunkCull[x][z].length; y++) {
					chunkCull[x][z][y] = surrounded(x,z,y);
				}
			}
		}
	}

	public void destroy() {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if(chunkTerrain[x][z][y] != null)
						chunkTerrain[x][z][y].getBlockMesh().destroy();
				}
			}
		}
		chunkTerrain = null;
	}
	
	public BlockType get(int x, int z, int y) {
		return chunkTerrain[x][z][y];
	}
	
	public List<BlockType> getBlockTypesInChunk(){
		return blocksInChunk;
	}
	
	public BlockType[][][] getChunk(){
		return chunkTerrain;
	}
	
	public Vector3f getStartingPos() {
		return startingPos;
	}
	
	public Vector3f indexFromPos(Block b) {
		Vector3f pos = b.getPosition();
		return new Vector3f(pos.getX() - startingPos.getX(), pos.getY() - startingPos.getY(), pos.getZ() - startingPos.getZ());
	}
	
	public Vector3f posFromIndex(int x, int z, int y) {
		return new Vector3f(startingPos.getX() + x, (startingPos.getY() + y), startingPos.getZ() + z);
	}
	
	public boolean surrounded(int x, int z, int y) {
		if(!(x > 0 && z > 0 && x < 15 && z < 15 && y > 0 && y < 127))
			return false;	
		return !(chunkTerrain[x - 1][z][y] == null || chunkTerrain[x + 1][z][y] == null || chunkTerrain[x][z - 1][y] == null || chunkTerrain[x][z + 1][y] == null || chunkTerrain[x][z][y - 1] == null || chunkTerrain[x][z][y + 1] == null);
	}
	
	public boolean cullBlock(int x, int z, int y) {
		return chunkCull[x][z][y];
	}
	
	private boolean isVisible(Vector3f blockPos) {
		Vector3f diffVector = Vector3f.subtract(blockPos, Player.getPosition());
		
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
