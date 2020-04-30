package engine.objects;

import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.math.Vector3f;
import engine.objects.Player;

public class Camera {
	private Vector3f position, rotation;
	private float moveSpeed = 0.5f, mouseSensitivity = 0.005f;
	private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

	public Camera(Vector3f p, Vector3f r) {
		position = p;
		rotation = r;
	}
	
	public void update() {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float x = (float)Math.sin(rotation.getY()) * moveSpeed;
		float z = (float)Math.cos(rotation.getY()) * moveSpeed;
		
		if(Input.isKeyDown(GLFW.GLFW_KEY_A)) 
			position = Vector3f.add(position, new Vector3f(-z, 0, x));
		if(Input.isKeyDown(GLFW.GLFW_KEY_D)) 
			position = Vector3f.add(position, new Vector3f(z, 0, -x));
		if(Input.isKeyDown(GLFW.GLFW_KEY_W)) 
			position = Vector3f.add(position, new Vector3f(-x, 0, -z));
		if(Input.isKeyDown(GLFW.GLFW_KEY_S)) 
			position = Vector3f.add(position, new Vector3f(x, 0, z));
		if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) 
			position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
		if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) 
			position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));
		if(Input.isKeyDown(GLFW.GLFW_KEY_F3))
			System.out.println(position);
	
		float dx = (float)(newMouseX - oldMouseX);
		float dy = (float)(newMouseY - oldMouseY);
		
		rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0));
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
		
		Player.setPosition(position);
		Player.setRotation(rotation);
		Player.setCamera(this);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}
	
	
	
}
