package engine.objects;

import engine.graphics.EnumTexture;
import engine.graphics.Mesh;
import engine.math.Vector3f;

public enum BlockType {
	GRASS_BLOCK(new Block(new Vector3f(0, 0, 0), new Vector3f(0,0,0), new Vector3f(1,1,1), EnumTexture.GRASS_BLOCK)),
	DIRT(new Block(new Vector3f(0, 0, 0), new Vector3f(0,0,0), new Vector3f(1,1,1), EnumTexture.DIRT)),
	STONE_BLOCK(new Block(new Vector3f(0, 0, 0), new Vector3f(0,0,0), new Vector3f(1,1,1), EnumTexture.STONE_BLOCK));
	
	
	
	
	
	
	
	
	
	
	private Block block;
	private String blockID;
	BlockType(Block b){
		block = b;
		block.getMesh().create();
		blockID = b.getModelName();
	}
	
	
	
	public String getBlockID() {
		return blockID;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Mesh getBlockMesh() {
		return block.getMesh();
	}
}
