package engine.terrain;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import engine.graphics.Renderer;
import engine.io.Input;
import engine.math.OpenSimplexNoise;
import main.Main;

public class World{
	private List<Chunk> terrain;
	private Renderer renderer;;
	
	public World(Renderer r) {
		terrain = new ArrayList<>();
		renderer = r;
	}
	
	public World(Renderer r, List<Chunk> initialChunks) {
		terrain = initialChunks;
		renderer = r;

		for(Chunk c : terrain)
			c.generateChunk(renderer);
	}
	
	public void render() {
		renderer.renderChunks(terrain);
	}

	public void destroy() {
		for(Chunk c : terrain)
			c.destroy();
	}
	
	
}
