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
import engine.math.Vector3f;
import engine.objects.Block;
import engine.objects.BlockType;
import engine.objects.Camera;
import engine.objects.Model;
import engine.objects.Player;
import engine.terrain.Chunk;
import engine.utils.GraphicsPointers;

public class Renderer{
	private Shader shader;
	private Window window;
	
	public Renderer(Window w, Shader s) {
		window = w;
		shader = s;
	}
	
	public void renderChunks(List<Chunk> chunks) {
		for(Chunk chunk : chunks) {
			List<BlockType> blocksInChunk = chunk.getBlockTypesInChunk();
			Mesh baseMesh;
			String blockID;
			for(BlockType b : blocksInChunk) {
				baseMesh = b.getBlockMesh();
				blockID = b.getBlockID();
				GL30.glBindVertexArray(baseMesh.getVAO());
				GL30.glEnableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
				GL30.glEnableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
				GL30.glEnableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
				GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, baseMesh.getIBO());
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL13.glBindTexture(GL11.GL_TEXTURE_2D, baseMesh.getMaterial().getTextureID());	
				shader.bind();
	
				for(int x = 0; x < 16; x++) {
					for(int z = 0; z < 16; z++) {
						for(int y = 0; y < 128; y++) {
							BlockType currentBlockType = chunk.get(x, z, y);
							Vector3f pos = chunk.posFromIndex(x, z, y);
							if(currentBlockType != null && !chunk.cullBlock(x, z, y) && currentBlockType.getBlockID().equals(blockID) && Vector3f.getDistance(pos, Player.getPosition()) <= 50.0f) {
								shader.setUniform("model", Matrix4f.transform(pos, currentBlockType.getBlock().getRotation(), new Vector3f(1,1,1)));
								shader.setUniform("projection", window.getProjectionMatrix());
								shader.setUniform("view", Matrix4f.view(Player.getCamera().getPosition(), Player.getCamera().getRotation()));
								GL11.glDrawElements(GL11.GL_TRIANGLES, baseMesh.getIndicies().length, GL11.GL_UNSIGNED_INT, 0);
							}
						}
					}
				}
			}
		}
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
		GL30.glDisableVertexAttribArray(GraphicsPointers.COLOR_PTR.getPointerIndex());
		GL30.glDisableVertexAttribArray(GraphicsPointers.TEXTURE_PTR.getPointerIndex());
		GL30.glBindVertexArray(GraphicsPointers.VERTEX_PTR.getPointerIndex());
	}
}

