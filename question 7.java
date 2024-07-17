package GlobalTrendProgrammingAssessment;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = minHeight * (right - left);
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Example usage:
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = solution.maxArea(heights);
        System.out.println("Maximum area: " + maxArea); // Output: 49
    }
}

