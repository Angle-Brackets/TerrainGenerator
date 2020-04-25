package engine.graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.io.Input;
import engine.io.Window;
import engine.math.Matrix4f;
import engine.objects.Block;
import engine.objects.Camera;
import engine.objects.Model;
import engine.objects.Player;
import engine.utils.GraphicsPointers;

public class Renderer{
	private Shader shader;
	private Window window;
	private Map<String, List<Model>> batchMap = new HashMap<>();
	private Map<String, Mesh> meshCache = new HashMap<>();
	private long start;

	
	public Renderer(Window w, Shader s) {
		window = w;
		shader = s;
	}
	
	public void render() {
		start = System.currentTimeMillis();
		for(String blockID : batchMap.keySet()) {
			Mesh baseMesh = meshCache.get(blockID);
			if(baseMesh == null)
				break;
			
			GL30.glBindVertexArray(baseMesh.getVAO());
			GL30.glEnableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
			GL30.glEnableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
			GL30.glEnableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, baseMesh.getIBO());
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL13.glBindTexture(GL11.GL_TEXTURE_2D, baseMesh.getMaterial().getTextureID());	
			shader.bind();
			
			for(Model m : batchMap.get(blockID)) {
				shader.setUniform("model", Matrix4f.transform(m.getPosition(), m.getRotation(), m.getScale()));
				shader.setUniform("projection", window.getProjectionMatrix());
				shader.setUniform("view", Matrix4f.view(Player.getCamera().getPosition(), Player.getCamera().getRotation()));
				GL11.glDrawElements(GL11.GL_TRIANGLES, baseMesh.getIndicies().length, GL11.GL_UNSIGNED_INT, 0);
			}
			shader.unbind();
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
			GL30.glDisableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
			GL30.glDisableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
			GL30.glDisableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
			GL30.glBindVertexArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		}
	}
	
	public void updateBatch(Model b) {
		String blockName = b.getModelName();
		
		if(b.getToRender()) {
			if(batchMap.get(blockName) != null && !batchMap.get(blockName).contains(b)) {
				batchMap.get(blockName).add(b);
			}
			else {
				batchMap.put(blockName, new ArrayList<Model>());
				batchMap.get(blockName).add(b);
			}
		}
		else if(batchMap.get(blockName) != null){
			batchMap.get(blockName).remove(b);
		}
		
		if(batchMap.get(blockName).size() == 0)
			meshCache.remove(blockName);
	}
	
	public void updateMeshCache(Model m) {
		meshCache.put(m.getModelName(), m.getMesh());
	}
	
	public boolean meshExists(String blockID) {
		if(meshCache.containsKey(blockID))
			return true;
		return false;
	}
	
	
}

