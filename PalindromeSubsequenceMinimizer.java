class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] result = new int[2];
        int n = nums.length - 2;
        int[] count = new int[n];
        
        for (int num : nums) {
            count[num]++;
            if (count[num] == 2) {
                if (result[0] == 0) {
                    result[0] = num;
                } else {
                    result[1] = num;
                    break;
                }
            }
        }
        
        return result;
    }
}