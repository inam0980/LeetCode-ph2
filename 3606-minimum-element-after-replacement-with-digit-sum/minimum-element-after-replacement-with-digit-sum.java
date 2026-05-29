class Solution {
    public int minElement(int[] nums) {
        // Initialize minOverall to the largest possible integer value
        // to ensure any digit sum found will be smaller.
        int minOverall = Integer.MAX_VALUE;

        // Iterate through each number in the input array.
        for (int num : nums) {
            int currentNum = num; // Use a temporary variable to avoid modifying the loop variable directly
            int currentDigitSum = 0; // Initialize sum for the current number

            // Calculate the sum of digits for the current number.
            // This loop continues as long as currentNum has digits remaining.
            while (currentNum > 0) {
                currentDigitSum += currentNum % 10; // Add the last digit to the sum
                currentNum /= 10; // Remove the last digit
            }
            
            // After calculating the digit sum for the current number,
            // update minOverall if this sum is smaller.
            minOverall = Math.min(minOverall, currentDigitSum);
        }

        // Return the minimum digit sum found across all numbers.
        return minOverall;
    }
}

// Time complexity: O(N * log(max_val)), where N