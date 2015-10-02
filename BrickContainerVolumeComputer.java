package org.yupii.misc;

/**
 * This class implements the solution for computing volume for the brick container.
 * 
 * @author vivekm
 *
 */
public class BrickContainerVolumeComputer {
	
	public int compute(final int[] brickStructure){
		int leftBoundaryPosition = 0;
		int rightBoundaryPosition = (brickStructure.length - 1);
		int volume = 0;

		for(int currentIdx = 0; currentIdx < brickStructure.length; currentIdx++){

			rightBoundaryPosition = getNextBoundaryPosition(currentIdx, brickStructure);
			volume += computeVolume(leftBoundaryPosition, rightBoundaryPosition, brickStructure);
			leftBoundaryPosition = rightBoundaryPosition;
			currentIdx = rightBoundaryPosition;
		}
		return volume;
	}
	
	private int computeVolume(final int leftBoundaryPosition, final int rightBoundaryPosition, final int[] brickStructure) {
		int volume = 0;
		int hiddenBrickCount = 0;
		int waterLevel = Math.min(brickStructure[leftBoundaryPosition], brickStructure[rightBoundaryPosition]);
		
		int totalCapacity = waterLevel * (rightBoundaryPosition - leftBoundaryPosition -1);
		
		for (int i = (leftBoundaryPosition + 1); i < rightBoundaryPosition; i++){
			hiddenBrickCount +=  brickStructure[i];
		}
		volume = (totalCapacity - hiddenBrickCount);
		return volume;
	}

	private int getNextBoundaryPosition(final int currPos, final int[] brickStructure){
		int newPos = brickStructure.length -1;
		int currentHighestWallPos = brickStructure.length -1;
		int globalMinHeightDiff = -1;
		
		for(int i = currPos + 1; i < brickStructure.length; i++){
			if(brickStructure[i] >= brickStructure[currPos]){
				//Found a higher or wall with same height , break with new pos
				newPos = i;
				return newPos;
			}else{
				//Find the closest low wall whose height is next to current wall
				int currHeightDiff = (brickStructure[currPos] - brickStructure[i]);
				
				if(currHeightDiff < globalMinHeightDiff){
					currentHighestWallPos = i;
					globalMinHeightDiff = currHeightDiff;
				} 
			}
		}
		return currentHighestWallPos;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] brickStructure = {4,1,2,1,5,1,6,2,4}; //14
		System.out.println("Final volume : " + new BrickContainerVolumeComputer().compute(brickStructure));
	}	
}
