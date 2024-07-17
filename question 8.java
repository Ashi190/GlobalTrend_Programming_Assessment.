package GlobalTrendProgrammingAssessment;

import java.util.Random;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        // Adjust k to 0-based index
        int targetIndex = nums.length - k;
        return quickselect(nums, 0, nums.length - 1, targetIndex);
    }

    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == k) {
            return nums[pivotIndex];
        } else if (pivotIndex < k) {
            return quickselect(nums, pivotIndex + 1, right, k);
        } else {
            return quickselect(nums, left, pivotIndex - 1, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        // Move pivot to end
        swap(nums, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();

        // Example usage:
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int kthLargest = solution.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + kthLargest); // Output: 5
    }
}

