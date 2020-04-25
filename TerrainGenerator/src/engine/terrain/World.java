package engine.terrain;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import engine.graphics.Renderer;
import engine.io.Input;
import main.Main;

public class World{
	private List<Chunk> terrain;
	private Renderer renderer;
	boolean hasCached = false;
	public World(Renderer r) {
		terrain = new ArrayList<>();
		renderer = r;
	}
	
	public World(Renderer r, List<Chunk> initialChunks) {
		terrain = (ArrayList<Chunk>)initialChunks;
		renderer = r;
		
		for(Chunk c : terrain)
			c.generateChunk(renderer);
	}
	
	public void render() {
		for(int i = 0; i < terrain.size(); i++) {
			terrain.get(i).renderChunk(renderer, i);
		}
	}

	public void destroy() {
		for(Chunk c : terrain)
			c.destroy();
	}
	
	
}
