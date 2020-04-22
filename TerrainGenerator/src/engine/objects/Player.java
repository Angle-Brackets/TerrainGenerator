package engine.objects;

import engine.math.Vector3f;

public class Player {
	private static float FOV = 50.0f;
	public static Vector3f playerPos;
	public static Vector3f lastPlayerPos;
	public static Camera camera;
	
	public Player(Camera c) {
		camera = c;
		playerPos = c.getPosition();
		lastPlayerPos = Vector3f.subtract(playerPos, new Vector3f(0.001f, 0, 0)); //This makes sure they're different so the game renders when it loads for the first time
	}
	
	public static void setPosition(Vector3f pos) {
		lastPlayerPos = playerPos;
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
	
	public static Vector3f getLastPlayerPos() {
		return lastPlayerPos;
	}

}
