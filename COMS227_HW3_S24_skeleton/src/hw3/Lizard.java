package hw3;

import static api.Direction.*;

import java.util.ArrayList;

import api.BodySegment;
import api.Cell;
import api.Direction;

/**
 * Represents a Lizard as a collection of body segments.
 */
public class Lizard {
	private ArrayList<BodySegment> segments;//ordered tail to head, 1st element is tail
	
	/**
	 * Constructs a Lizard object.
	 */
	public Lizard() {
		// TODO: method stub
		
		segments = new ArrayList<BodySegment>();
	}

	/**
	 * Sets the segments of the lizard. Segments should be ordered from tail to
	 * head.
	 * 
	 * @param segments list of segments ordered from tail to head
	 */
	public void setSegments(ArrayList<BodySegment> segments) {
		// TODO: method stub
		for (BodySegment bodySegment : segments) {
			this.segments.add(bodySegment);
		}
	}

	/**
	 * Gets the segments of the lizard. Segments are ordered from tail to head.
	 * 
	 * @return a list of segments ordered from tail to head
	 */
	public ArrayList<BodySegment> getSegments() {
		// TODO: method stub
		return segments;
	}

	/**
	 * Gets the head segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the head segment
	 */
	public BodySegment getHeadSegment() {
		// TODO: method stub
		return this.segments.get(segments.size() - 1);
	}

	/**
	 * Gets the tail segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the tail segment
	 */
	public BodySegment getTailSegment() {
		// TODO: method stub
		return this.segments.get(0);
	}

	/**
	 * Gets the segment that is located at a given cell or null if there is no
	 * segment at that cell.
	 * 
	 * @param cell to look for lizard
	 * @return the segment that is on the cell or null if there is none
	 */
	public BodySegment getSegmentAt(Cell cell) {
		// TODO: method stub
		for (BodySegment bodySegment : segments) {
			if(bodySegment.getCell().equals(cell)) {
				return bodySegment;
			}
		}
		
		return null;
	}

	/**
	 * Get the segment that is in front of (closer to the head segment than) the
	 * given segment. Returns null if there is no segment ahead.
	 * 
	 * @param segment the starting segment
	 * @return the segment in front of the given segment or null
	 */
	public BodySegment getSegmentAhead(BodySegment segment) {
		// TODO: method stub
		int index = segments.indexOf(segment);
			if(index != -1 && index < segments.size() - 1 ) {
				
				return segments.get(index + 1);
			}
		return null;
	}

	/**
	 * Get the segment that is behind (closer to the tail segment than) the given
	 * segment. Returns null if there is not segment behind.
	 * 
	 * @param segment the starting segment
	 * @return the segment behind of the given segment or null
	 */
	public BodySegment getSegmentBehind(BodySegment segment) {
		// TODO: method stub
		int index = segments.indexOf(segment);
		if(index > 0) {
			
			return segments.get(index -1);
		}
		return null;
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment ahead (in front of) of it. Returns null if there is no segment ahead
	 * of the given segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment ahead of the given segment or null
	 */
	public Direction getDirectionToSegmentAhead(BodySegment segment) {
		// TODO: method stub
		int index = segments.indexOf(segment);
		if(index >= segments.size()-1) {
			return null;
		}
			Cell currCell = segment.getCell();
			Cell aheadCell = segments.get(index+1).getCell();
			int col = currCell.getCol() - aheadCell.getCol();
			int row = currCell.getRow()- aheadCell.getRow();
			if(col == 0) {
				if(row < 0) {
					return DOWN;
				}else {
					return UP;
				}
			}else {
				if(col < 0) {
					return RIGHT;
				}else if(col > 0) {
					return LEFT;
				}
			}
			
			
		
		return null;
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment behind it. Returns null if there is no segment behind of the given
	 * segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment behind of the given segment or null
	 */
	public Direction getDirectionToSegmentBehind(BodySegment segment) {
		// TODO: method stub
		return null;
	
	}

	/**
	 * Gets the direction in which the head segment is pointing. This is the
	 * direction formed by going from the segment behind the head segment to the
	 * head segment. A lizard that does not have more than one segment has no
	 * defined head direction and returns null.
	 * 
	 * @return the direction in which the head segment is pointing or null
	 */
	public Direction getHeadDirection() {
		// TODO: method stub
		
		return null;
	}

	/**
	 * Gets the direction in which the tail segment is pointing. This is the
	 * direction formed by going from the segment ahead of the tail segment to the
	 * tail segment. A lizard that does not have more than one segment has no
	 * defined tail direction and returns null.
	 * 
	 * @return the direction in which the tail segment is pointing or null
	 */
	public Direction getTailDirection() {
		// TODO: method stub
		
		
		return null;
	}

	@Override
	public String toString() {
		String result = "";
		for (BodySegment seg : getSegments()) {
			result += seg + " ";
		}
		return result;
	}
}
