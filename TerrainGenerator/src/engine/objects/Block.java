package engine.objects;

import org.lwjgl.opengl.GL13;

import engine.graphics.EnumTexture;
import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.graphics.Textures;
import engine.graphics.Vertex;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class Block extends Model{
	private Vector3f[] normals;
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
		super(position, rotation, scale, getBlockModel(Textures.get(m), m.getNumTextures()), m.name());
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
						new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.5f, 1.0f)),
						new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(0.5f, 0.5f)),
						
						//Bottom face
						new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.5f, 0.0f)),
						new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 0.5f)),
						new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
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
	
	private Vector3f[] calculateNormals() {
		Vertex[] verts = this.getMesh().getVerticies();
		Vector3f[] normals = new Vector3f[6];
		normals[0] = Vector3f.cross(verts[1].getPosition(), verts[2].getPosition());
		normals[1] = Vector3f.cross(verts[5].getPosition(), verts[6].getPosition());
		normals[2] = Vector3f.cross(verts[9].getPosition(), verts[10].getPosition());
		normals[3] = Vector3f.cross(verts[13].getPosition(), verts[14].getPosition());
		normals[4] = Vector3f.cross(verts[17].getPosition(), verts[18].getPosition());
		normals[5] = Vector3f.cross(verts[21].getPosition(), verts[22].getPosition());

		return normals;
	}
	
	public Vector3f[] getNormals() {
		return normals;
	}
	
	
}
