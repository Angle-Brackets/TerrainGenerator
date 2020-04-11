package main;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.Vertex;
import engine.io.Input;
import engine.io.Window;
import engine.math.Vector3f;
import engine.objects.GameObject;
import engine.math.Vector2f;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public Renderer renderer;
	public Shader shader;
	public static final int WIDTH = 1280, HEIGHT = 760;
	
	public Mesh mesh = new Mesh(new Vertex[] {
			new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f,0.0f)),
			new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f,1.0f)),
			new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f,1.0f)),
			new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.5f, 0.5f, 0.5f), new Vector2f(1.0f,0.0f)),
			
	}, new int[] {
		0, 1, 2,
		0, 3, 2,
	}, new Material("/textures/discordpicWinter.png"));
	
	public GameObject object = new GameObject(mesh, new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1));
	
	public void start() {
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		shader = new Shader("/shaders/mainVertex.glsl","/shaders/mainFragment.glsl");
		renderer = new Renderer(shader);
		window.setBackgroundColor((float)Math.random(), (float)Math.random(), (float)Math.random());
		window.create();
		mesh.create();
		shader.create();
	}
	
	public void run() {
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			update();
			render();
			if(Input.isKeyDown(GLFW.GLFW_KEY_F11))
				window.setFullscreen(!window.isFullscreen());
		} 
		close();
	}
	
	private void update() {
		window.update();
		object.update();
		if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
			System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
	}
	
	private void render() {
		renderer.renderMesh(object);
		window.swapBuffers();
	}
	
	private void close() {
		window.destroy();
		mesh.destroy();
		shader.destroy();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}