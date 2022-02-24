package datastructure.offer;

public class Test14 {
    public static void reOrder(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            if (tmp % 2 == 0) {
                for (int j = i; j < nums.length - 1; j++) {
                    // 将遇到的偶数移到数组的末尾
                    nums[j] = nums[j + 1];

                }
                nums[nums.length - 1] = tmp;
            }
        }

//        return nums;
    }

    public static void reOrder2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            while (right > left && nums[left] % 2 != 0) {
                left++;
            }
            while (right > left && nums[right] % 2 == 0) {
                right--;
            }
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }

    }

    public static void main(String[] args) {
        int[] nums = { 2, 4, 6, 1, 3, 5}; // 1 3 4 5 6 2 | 1 3 5 6 2 4 | 1 3 5 2 4 6 |
        // 1 5 3 4 2
        // 1 3 5 4 2 6
        // 5 3 1 6 4 2
        reOrder2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}
