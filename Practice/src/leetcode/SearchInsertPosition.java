package leetcode;

public class SearchInsertPosition {

public int searchInsert(int[] nums, int target) {
	
	int low = 0, high = nums.length - 1;
	
	while(low <= high) {
		int midPoint = low + (high - low) / 2;
		if(nums[midPoint] == target) {
			return midPoint;
		}
		if(nums[midPoint] > target) {
			high = midPoint -1;
		}
		else {
			low = midPoint + 1;
		}
		
	}
	return low;
        
    }
}
