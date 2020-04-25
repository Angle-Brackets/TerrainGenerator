package engine.math;

public class Vector3f {
	private float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3f add(Vector3f v1, Vector3f v2) {
		return new Vector3f(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
	}
	
	public static Vector3f subtract(Vector3f v1, Vector3f v2) {
		return new Vector3f(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
	}
	
	public static Vector3f multiply(Vector3f v1, Vector3f v2) {
		return new Vector3f(v1.getX() * v2.getX(), v1.getY() * v2.getY(), v1.getZ() * v2.getZ());
	}
	
	public static Vector3f divide(Vector3f v1, Vector3f v2) {
		return new Vector3f(v1.getX() / v2.getX(), v1.getY() / v2.getY(), v1.getZ() / v2.getZ());
	}
	
	public static float magnitude(Vector3f vector) {
		return (float)Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
	}
	
	public static Vector3f normalization(Vector3f vector) {
		float mag = Vector3f.magnitude(vector);
		return Vector3f.divide(vector, new Vector3f(mag, mag, mag));
	}
	
	public static float dot(Vector3f v1, Vector3f v2) {
		return (v1.getX() * v2.getX()) + (v1.getY() * v2.getY()) + (v1.getZ() * v2.getZ());
	}
	
	public static Vector3f cross(Vector3f v1, Vector3f v2) {
		Vector3f result = new Vector3f();
		result.x = v1.y * v2.z - v2.y * v1.z;
		result.y = v1.z * v2.x - v2.z * v1.x;
		result.z = v1.x * v2.y - v2.x * v1.y;
		
		return result;	
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}
	
	public String toString() {
		return getX() + ", " + getY() + ", " + getZ();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3f other = (Vector3f) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public String getLargestComponent() {
		if(x > y && x > z)
			return "X";
		else if(y > x && y > z)
			return "Y";
		return "Z";
	}
}
