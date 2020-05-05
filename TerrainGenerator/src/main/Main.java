package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import engine.terrain.World;
import engine.math.Vector2f;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public Renderer renderer;
	public Shader shader;
	public static World world;
	public static final int WIDTH = 1280, HEIGHT = 760;
	public Camera camera = new Camera(new Vector3f(5f,80.5f,5f), new Vector3f(0,0,0));
	public Player player = new Player(camera);
	public static long start;
	public static boolean startedRender = false;
	
	List<Chunk> chunks = new ArrayList<>();
	
	
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
		
		for(int x = 0; x < 16; x+=16) {
			for(int z = 0; z < 16; z+=16) {
				chunks.add(new Chunk(new Vector3f(x, 0.0f, z)));
			}
		}
		
		world = new World(renderer, chunks);
		shader.create();
	}
	
	public void run() {
		init();
		System.out.println("Elapsed Time: " + (System.currentTimeMillis() - start) + "ms");
		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			//System.out.println(Player.getRotation() + ", " + Player.getDirection());
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
		world.render();
		window.swapBuffers();
	}
	
	private void close() {
		shader.destroy();
		world.destroy();
	}
	
	public static void main(String[] args) {
		Textures.loadTextures();
		new Main().start();
		start = System.currentTimeMillis();
	}
}