package main;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.Textures;
import engine.graphics.EnumTexture;
import engine.graphics.Vertex;
import engine.io.Input;
import engine.io.Window;
import engine.math.Vector3f;
import engine.objects.Camera;
import engine.objects.Block;
import engine.objects.Model;
import engine.objects.Player;
import engine.terrain.Chunk;
import engine.math.Vector2f;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public Renderer renderer;
	public Shader shader;
	public static final int WIDTH = 1280, HEIGHT = 760;
	public Camera camera = new Camera(new Vector3f(7.5f,70,7.5f), new Vector3f(0,0,0));
	public Player player = new Player(camera);
	
	Chunk chungus = new Chunk(new Vector3f(0,0,0));
	Chunk chungus2 = new Chunk(new Vector3f(16, 0, 0));
//	Chunk chungus3 = new Chunk(new Vector3f(32, 0, 0));
//	Chunk chungus4 = new Chunk(new Vector3f(48, 0, 0));
//	Chunk chungus5 = new Chunk(new Vector3f(64, 0, 0));
//	Chunk chungus6 = new Chunk(new Vector3f(76, 0, 0));
	
	Chunk[] chunks = {chungus, chungus2};
	
	public void start() {
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		shader = new Shader("/shaders/mainVertex.glsl","/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		window.setBackgroundColor(0.6f, 0.8f, 1.0f);
		window.create();

		for(Chunk c : chunks)
			c.generateChunk();
		
		shader.create();
	}
	
	public void run() {
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			update();
			render();
			if(Input.isKeyDown(GLFW.GLFW_KEY_F11))
				window.setFullscreen(!window.isFullscreen());
			if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
				window.mouseState(true);
		} 
		close();
	}
	
	private void update() {
		window.update();
		camera.update();
	}
	
	private void render() {
		for(Chunk c : chunks)
			c.renderChunk(renderer, camera);
		
		window.swapBuffers();
	}
	
	private void close() {
		for(Chunk c : chunks)
			c.destroy();
		shader.destroy();
	}
	
	public static void main(String[] args) {
		Textures.loadTextures();
		new Main().start();
	}
}