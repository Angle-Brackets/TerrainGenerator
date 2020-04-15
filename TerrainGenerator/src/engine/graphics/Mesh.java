package engine.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import engine.utils.GraphicsPointers;

public class Mesh {
	private Vertex[] verticies;
	private Material material;
	private int[] indicies;
	private int vao, vbo, ibo, tbo; //Vertex Array Obj, Vertex Buffer Obj, Indices Buffer Obj, Texture Buffer Obj.
	
	public Mesh(Vertex[] verts, int[] indic, Material mat) {
		verticies = verts;
		indicies = indic;
		material = mat;
	}
	
	public void create() {
		material.create();
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		FloatBuffer verticiesBuffer = MemoryUtil.memAllocFloat(verticies.length * 3);
		float[] positionData = new float[verticies.length * 3];
		
		for(int i = 0; i < verticies.length; i++) {
			positionData[i*3] = verticies[i].getPosition().getX();
			positionData[i*3 + 1] = verticies[i].getPosition().getY();
			positionData[i*3 + 2] = verticies[i].getPosition().getZ();
		}
		verticiesBuffer.put(positionData).flip();
		
		vbo = storeData(verticiesBuffer, GraphicsPointers.VERTEX_PTR.getPointerIndex(), 3);
		
		FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(verticies.length * 2);
		float[] textureData = new float[verticies.length * 2];
		for (int i = 0; i < verticies.length; i++) {
			textureData[i * 2] = verticies[i].getTextureCoord().getX();
			textureData[i * 2 + 1] = verticies[i].getTextureCoord().getY();
		}
		textureBuffer.put(textureData).flip();
		
		tbo = storeData(textureBuffer, GraphicsPointers.TEXTURE_PTR.getPointerIndex(), 2);
		
		
		IntBuffer indiciesBuffer = MemoryUtil.memAllocInt(indicies.length);
		indiciesBuffer.put(indicies).flip();
		
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indiciesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private int storeData(FloatBuffer buffer, int index, int size) {
		int bufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return bufferID;
	}
	
	public void destroy() {
		GL15.glDeleteBuffers(vbo);
		GL15.glDeleteBuffers(ibo);
		GL15.glDeleteBuffers(tbo);
		
		GL30.glDeleteVertexArrays(vao);
		
		material.destroy();
	}

	public Vertex[] getVerticies() {
		return verticies;
	}

	public int[] getIndicies() {
		return indicies;
	}

	public int getVAO() {
		return vao;
	}

	public int getVBO() {
		return vbo;
	}

	public int getIBO() {
		return ibo;
	}
	
	public int getTBO() {
		return tbo;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material m) {
		material = m;
	}
	
	
}
