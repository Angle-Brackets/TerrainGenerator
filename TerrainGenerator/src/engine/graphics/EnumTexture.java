package engine.graphics;

public enum EnumTexture {
	UNDEFINED ("/textures/errorTexture.png", 1),
	GRASS_BLOCK ("/textures/grassblock.png", 3),
	STONE ("/textures/stone.png", 1);
	
	
	
	
	
	
	
	
	
	
	
	
	private String path;
	private int textureCount;
	EnumTexture(String p, int texC){
		path = p;
		textureCount = texC;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getNumTextures() {
		return textureCount;
	}
	
}
