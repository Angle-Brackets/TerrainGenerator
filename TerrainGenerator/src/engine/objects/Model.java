package engine.objects;

import engine.graphics.EnumTexture;
import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.math.Vector3f;

public class Model {
	private String name;
	private Vector3f position, rotation, scale;
	private boolean toRender;
	private Mesh mesh;
	
	public Model(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, String name) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
		this.name = name;
		toRender = false;
	}
	
	public void update() {
		position.setZ(position.getZ() - 0.05f);
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
	
	public String getModelName() {
		return name;
	}
	
	public float getX() {
		return position.getX();
	}
	
	public float getY() {
		return position.getY();
	}
	
	public float getZ() {
		return position.getZ();
	}
	
	public float getDistance(Vector3f playerPos) {
		return (float)Math.sqrt(Math.pow(this.getPosition().getX() - playerPos.getX(), 2) + Math.pow(this.getPosition().getY() - playerPos.getY(), 2) + Math.pow(this.getPosition().getZ() - playerPos.getZ(), 2));
	}
	
	public Material getMaterial() {
		return mesh.getMaterial();
	}
	
	public boolean getToRender() {
		return toRender;
	}
	
	public void setToRender(boolean b) {
		toRender = b;
	
	}
	
	protected void setMaterial(Material m) {
		mesh.setMaterial(m);
	}

	
}
