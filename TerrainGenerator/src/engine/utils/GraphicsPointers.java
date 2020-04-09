package engine.utils;

public enum GraphicsPointers {
	VERTEX_PTR (0),
	COLOR_PTR (1);
	
	private int index;
	GraphicsPointers(int i) {
		index = i;
	}
	
	public int getPointerIndex() {
		return index;
	}

}
