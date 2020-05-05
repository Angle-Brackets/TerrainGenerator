package engine.terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import engine.graphics.EnumTexture;
import engine.graphics.Renderer;
import engine.math.OpenSimplexNoise;
import engine.math.Vector3f;
import engine.objects.Block;
import engine.objects.BlockType;
import engine.objects.Camera;
import engine.objects.Player;
import main.Main;

public class Chunk {
	public static final float maxHeight = 127.0f;
	private static OpenSimplexNoise simplex = new OpenSimplexNoise((long)(Math.random() * Long.MAX_VALUE));
	private BlockType[][][] chunkTerrain;
	private Vector3f[][][] positions;
	private boolean[][][] chunkCull; //N, E, S, W order.
	private int lowestPoint;
	private List<BlockType> blocksInChunk; //List of all of the blocks in a chunk for batch rendering.
	private Vector3f startingPos;
	private boolean loaded;
	
	public Chunk(Vector3f sP) {
		if(chunkTerrain == null) {
			chunkTerrain = new BlockType[16][16][128];
			positions = new Vector3f[16][16][128];
			chunkCull = new boolean[16][16][128];
			startingPos = sP;
			blocksInChunk = new ArrayList<>();
			lowestPoint = 127;
			loaded = Vector3f.getDistance(this.getStartingPos(), Player.getPosition()) >= Player.getFOV();
		}
	}
	//0.007 is default
	public void generateChunk(Renderer renderer) {
		for(int x = 0; x < chunkTerrain.length; x++) {
			for(int z = 0; z < chunkTerrain[x].length; z++) {
				for(int y = 0; y < chunkTerrain[x][z].length; y++) {
					if (y < maxHeight) {
						chunkTerrain[x][z][y] = BlockType.GRASS_BLOCK;
						
						if(chunkTerrain[x][z][y] != null) {
							positions[x][z][y] = new Vector3f(startingPos.getX() + x, Math.round(sumOctives(16, startingPos.getX() + x, startingPos.getZ() + z, 0.5f, 0.007f, 0, 1) * (startingPos.getY() + y)), startingPos.getZ() + z);
						}
						
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
					if(y < 127 && chunkTerrain[x][z][y] != null && y < lowestPoint && positions[x][z][y + 1] == null) {
						lowestPoint = y < lowestPoint ? y : lowestPoint;
					}

					if(y < lowestPoint) {
						chunkCull[x][z][y] = true;
					}
				}
			}
		}
	}
	
	private float sumOctives(int iterations, float x, float z, float persistence, float scale, int low, int high) {
		float maxAmp = 0.0f;
		float amp = 1;
		float freq = scale;
		float noise = 0.0f;
		
		for(int i = 0; i < iterations; i++) {
			noise += simplex.eval(x * freq, z * freq) * amp;
			maxAmp += amp;
			amp *= persistence;
			freq *= 2;
		}
		
		noise /= maxAmp;
		noise = noise * (high - low) / 2.0f + (high + low) / 2.0f;
		
		return noise;
	}
	
	public void setLoaded(boolean l) {
		loaded = l;
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
	
	public boolean getLoaded() {
		return loaded;
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
		return positions[x][z][y];
	}
	
	public boolean surrounded(int x, int z, int y) {
		if(!(x > 0 && z > 0 && x < 15 && z < 15 && y > 0 && y < 127))
			return false;	
		return !(chunkTerrain[x - 1][z][y] == null || chunkTerrain[x + 1][z][y] == null || chunkTerrain[x][z - 1][y] == null || chunkTerrain[x][z + 1][y] == null || chunkTerrain[x][z][y - 1] == null || chunkTerrain[x][z][y + 1] == null);
	}
	
	public boolean cullBlock(int x, int z, int y) {
		return chunkCull[x][z][y];
	}
	
	public boolean isBlockVisible(Vector3f blockPos) {
		//1.22173 and 0.34202059099
		double angle = (double)Vector3f.dot(blockPos, Player.getRotation()) / (Vector3f.magnitude(blockPos) * Vector3f.magnitude(Player.getRotation()));
		//System.out.println(angle);
		return !(angle > 0.34202059099);
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
