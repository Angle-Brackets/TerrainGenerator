package engine.objects;

import engine.graphics.Mesh;
import engine.math.Vector3f;

public class GameObject {
	private Vector3f position, rotation, scale;
	private Mesh mesh;
	private double temp;
	private final float TWO_PI = (float)(Math.PI*2);
	
	public GameObject(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}
	
	public void update() {
		temp += 0.02;
		position.setX((float) Math.sin(temp));
		rotation.set((float) Math.sin(temp) * TWO_PI, (float) Math.sin(temp) * TWO_PI, (float) Math.sin(temp) * TWO_PI);
		scale.set((float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp));
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public Mesh getMesh() {
		return mesh;
	}
	
}
