package engine.graphics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Textures {
	public static Map<EnumTexture, Material> textureMap = new HashMap<EnumTexture, Material>();
	
	public static Material get(EnumTexture e) {
		return textureMap.get(e);
	}
	
	public static void loadTextures() {
		EnumTexture[] arr = EnumTexture.values();
		for(int i = 0; i < arr.length; i++)
			textureMap.put(arr[i], new Material(arr[i].getPath()));
	}
}
