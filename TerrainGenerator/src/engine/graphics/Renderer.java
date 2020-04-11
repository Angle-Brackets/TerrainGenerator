package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import engine.math.Matrix4f;
import engine.objects.GameObject;
import engine.utils.GraphicsPointers;

public class Renderer {
	private Shader shader;
	
	public Renderer(Shader shader) {
		this.shader = shader;
	}
	
	public void renderMesh(GameObject obj) {
		Mesh mesh = obj.getMesh();
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		GL30.glEnableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
		GL30.glEnableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());		
		shader.bind();
		shader.setUniform("model", Matrix4f.transform(obj.getPosition(), obj.getRotation(), obj.getScale()));
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndicies().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		GL30.glDisableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
		GL30.glDisableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
		GL30.glBindVertexArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
	}
}