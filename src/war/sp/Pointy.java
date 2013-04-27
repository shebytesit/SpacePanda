package war.sp;

import java.awt.geom.Point2D;

public class Pointy extends Point2D.Double {
	int id;
	int sequence;
	
	public Pointy(){
	}
	public Pointy(int id, int sequence, double x, double y) {
		super(x, y);
		this.id = id;
		this.sequence = sequence;
	}
	public int getID(){
		return id;
	}
	public int getSeq(){
		return sequence;
	}
}
