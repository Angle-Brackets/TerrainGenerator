package engine.graphics;

import engine.math.Vector2f;
import engine.math.Vector3f;

public class Vertex {
	private Vector3f position, color;
	private Vector2f textureCoord;
	
	public Vertex(Vector3f pos, Vector3f c, Vector2f tC) {
		position = pos;
		color = c;
		textureCoord = tC;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public Vector2f getTextureCoord() {
		return textureCoord;
	}
}
