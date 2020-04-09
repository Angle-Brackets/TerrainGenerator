package engine.graphics;

import engine.math.Vector3f;

public class Vertex {
	private Vector3f position, color;
	
	public Vertex(Vector3f pos, Vector3f c) {
		position = pos;
		color = c;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getColor() {
		return color;
	}
}
