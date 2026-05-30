import java.util.*;

class Solution {
    // A Max Fenwick Tree (Binary Indexed Tree) to maintain max gap sizes
    class FenwickTree {
        int[] vals;

        public FenwickTree(int n) {
            vals = new int[n + 1];
        }

        // Update the BIT with the maximum value seen so far
        public void updateMax(int i, int val) {
            while (i < vals.length) {
                vals[i] = Math.max(vals[i], val);
                i += (i & -i);
            }
        }

        // Query the maximum gap size in the range [1, i]
        public int getMax(int i) {
            int res = 0;
            while (i > 0) {
                res = Math.max(res, vals[i]);
                i -= (i & -i);
            }
            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        // Constraints say x maxes out at min(50000, 3 * queries.length)
        final int maxRange = Math.min(50000, queries.length * 3);
        
        List<Boolean> ans = new ArrayList<>();
        FenwickTree tree = new FenwickTree(maxRange + 1);
        
        // TreeSet keeps track of active obstacles sorted. 
        // 0 and maxRange act as essential boundary sentinels.
        TreeSet<Integer> obstacles = new TreeSet<>(Arrays.asList(0, maxRange));

        // Step 1: Collect all obstacles from Type 1 queries to find the final state
        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        // Step 2: Build the initial Fenwick tree based on the final obstacle configuration
        Iterator<Integer> it = obstacles.iterator();
        int prevObstacle = it.next();
        while (it.hasNext()) {
            int currObstacle = it.next();
            tree.updateMax(currObstacle, currObstacle - prevObstacle);
            prevObstacle = currObstacle;
        }

        // Step 3: Process queries in reverse order (Turning obstacle placement into removal)
        for (int i = queries.length - 1; i >= 0; --i) {
            int type = queries[i][0];
            
            if (type == 1) {
                int targetX = queries[i][1];
                // Remove the obstacle and patch the gap between its neighbors
                Integer nextObstacle = obstacles.higher(targetX);
                Integer prevObstacleNode = obstacles.lower(targetX);
                
                if (nextObstacle != null && prevObstacleNode != null) {
                    tree.updateMax(nextObstacle, nextObstacle - prevObstacleNode);
                }
                obstacles.remove(targetX);
                
            } else {
                int targetX = queries[i][1];
                int sz = queries[i][2];
                
                // Find the closest obstacle strictly <= targetX
                int lastObstacleBeforeX = obstacles.floor(targetX);
                
                // Check two possibilities:
                // 1. Is there a large enough gap completely before the last obstacle?
                // 2. Is the remaining gap between the last obstacle and targetX large enough?
                boolean canPlace = tree.getMax(lastObstacleBeforeX) >= sz || (targetX - lastObstacleBeforeX) >= sz;
                ans.add(canPlace);
            }
        }

        // Since we processed backward, reverse the result list to match original query order
        Collections.reverse(ans);
        return ans;
    }
}