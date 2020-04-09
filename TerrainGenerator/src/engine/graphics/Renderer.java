package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import engine.utils.GraphicsPointers;

public class Renderer {
	private Shader shader;
	
	public Renderer(Shader shader) {
		this.shader = shader;
	}
	
	public void renderMesh(Mesh mesh) {
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		GL30.glEnableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		shader.bind();
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndicies().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		GL30.glDisableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
		GL30.glBindVertexArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
	}
}