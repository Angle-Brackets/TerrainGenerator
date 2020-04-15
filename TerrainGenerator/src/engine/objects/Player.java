package engine.objects;

import engine.math.Vector3f;

public class Player {
	private static float FOV = 50.0f;
	public static Vector3f playerPos;
	public static Camera camera;
	
	public Player(Camera c) {
		camera = c;
		playerPos = c.getPosition();
	}
	
	public static void setPosition(Vector3f pos) {
		playerPos = pos;
	}
	
	public static void setCamera(Camera c) {
		camera = c;
	}
	
	public static Vector3f getPosition() {
		return playerPos;
	}
	
	public static float getFOV() {
		return FOV;
	}
	
	public static Camera getCamera() {
		return camera;
	}

}
