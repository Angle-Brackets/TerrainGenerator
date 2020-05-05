package engine.objects;

import engine.math.Vector3f;

public class Player {
	private static float FOV = 128.0f;
	private static Vector3f playerPos;
	private static Vector3f playerRot;
	private static Camera camera;
	
	public Player(Camera c) {
		camera = c;
		playerPos = c.getPosition();
		playerRot = c.getRotation();
	}
	
	public static void setPosition(Vector3f pos) {
		playerPos = pos;
	}
	
	public static void setRotation(Vector3f rot) {
		playerRot = rot;
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
	
	public static Vector3f getRotation() {
		return playerRot;
	}
	
	public static String getDirection() {
		return playerRot.getLargestComponent();
	}
}
