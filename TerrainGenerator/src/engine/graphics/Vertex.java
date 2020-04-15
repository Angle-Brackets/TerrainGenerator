package engine.graphics;

import engine.math.Vector2f;
import engine.math.Vector3f;

public class Vertex {
	private Vector3f position;
	private Vector2f textureCoord;
	
	public Vertex(Vector3f pos, Vector2f tC) {
		position = pos;
		textureCoord = tC;
	}
	
	public Vector3f getPosition() {
		return position;
	}	
	
	public Vector2f getTextureCoord() {
		return textureCoord;
	}
}
