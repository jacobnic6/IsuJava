
public class Mystery {

	private int leaf;
	public Mystery(int leaf)
	{
	this.leaf = leaf;
	}
	public int brew(int green) {
	if (green > 0) {
	if (green < 10) {
	green = green + leaf;
	}
	} else {
	green = 0;
	}
	if (green < 20) {
	leaf = leaf * 2;
	}
	return green;
	}
}
