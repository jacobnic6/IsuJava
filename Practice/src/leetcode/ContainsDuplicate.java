package leetcode;

public class ContainsDuplicate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3,1};
		int[] nums1 ={0,4,5,0,3,6};
		System.out.println(findDuplicate(nums));
		System.out.println(findDuplicate(nums1));
	}
	
	public static boolean findDuplicate(int[] nums) {
		if(nums.length == 1) {
			return false;
		}
		int i = 0; 
		int endPoint = nums.length-1;
		for ( i = 0; i <= endPoint; i++) {
			if(nums[i]== nums[endPoint]) {
				return true;
			}
			endPoint -=1;
		}
		return false;
	}

}
