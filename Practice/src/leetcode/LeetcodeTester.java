package leetcode;

public class LeetcodeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,3,5,7};
		int[] nums1 = {9};
		int target = 6;
		SearchInsertPosition s = new SearchInsertPosition();
		//System.out.println(s.searchInsert(nums, target)); ;
		
		PlusOne p = new PlusOne();
		//System.out.println(p.plusOne(nums));
		for (int i : p.plusOne(nums1)) {
			System.out.println(i);
		}
	}

}
