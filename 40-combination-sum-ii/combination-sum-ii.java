
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. Sort the array to handle duplicates efficiently
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain < 0) {
            return; // Exceeded the target sum
        } else if (remain == 0) {
            result.add(new ArrayList<>(tempList)); // Found a valid unique combination
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Optimization: If the current number is greater than the remaining target,
            // since the array is sorted, all subsequent numbers will also be too large.
            if (candidates[i] > remain) {
                break;
            }

            // Skip duplicate elements to avoid duplicate combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            tempList.add(candidates[i]);
            
            // Move to i + 1 because each element can only be used once
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
            
            tempList.remove(tempList.size() - 1); // Backtrack
        }
    }
}