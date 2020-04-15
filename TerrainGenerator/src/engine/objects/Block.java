package engine.objects;

import engine.graphics.EnumTexture;
import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.graphics.Textures;
import engine.graphics.Vertex;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class Block extends Model{
	private String name;
	private Vector3f position;
	private Vector3f rotation;
	private boolean toRender;
	private static final int[] indices = new int[] {
			//Back face
			0, 1, 3,	
			3, 1, 2,	
			
			//Front face
			4, 5, 7,
			7, 5, 6,
			
			//Right face
			8, 9, 11,
			11, 9, 10,
			
			//Left face
			12, 13, 15,
			15, 13, 14,
			
			//Top face
			16, 17, 19,
			19, 17, 18,
			
			//Bottom face
			20, 21, 23,
			23, 21, 22
	};
	
	public Block(Vector3f position, Vector3f rotation, Vector3f scale, EnumTexture m) {
		super(position, rotation, scale, getBlockModel(Textures.get(m), m.getNumTextures()));
		name = m.name();
		this.position = position;
		this.rotation = rotation;
		toRender = false;
	}
	
	private static Mesh getBlockModel(Material m, int textureNum) {
		
		switch (textureNum) {
			case 3:
				return new Mesh(new Vertex[] {
						//Back face
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.5f, 0.0f)),
						
						//Front face
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(0.5f, 0.0f)),
						
						//Right face
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(0.5f, 0.0f)),
						
						//Left face
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.5f, 0.0f)),
						
						//Top face
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(0.5f, 1.0f)),
						
						//Bottom face
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.5f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.5f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
					}, indices, m);
			default:
				return new Mesh(new Vertex[] {
						//Back face
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 0.0f)),
						
						//Front face
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
						
						//Right face
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
						
						//Left face
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
						
						//Top face
						new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
						
						//Bottom face
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
					}, indices, m);
		}
		
		
		
	}
	
	public String getBlockName() {
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
	
	public void setRender(boolean b) {
		toRender = b;
	}
	
	public boolean getRender() {
		return toRender;
	}
}
