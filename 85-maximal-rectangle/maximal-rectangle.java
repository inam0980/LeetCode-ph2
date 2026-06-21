class Solution {
    public int maximalRectangle(char[][] m) {
        int n = m[0].length, ans = 0, h[] = new int[n];
        for (char[] r : m) {
            for (int i = 0; i < n; i++)
                h[i] = r[i] == '1' ? h[i] + 1 : 0;

            Stack<Integer> s = new Stack<>();
            for (int i = 0; i <= n; i++) {
                while (!s.isEmpty() && (i == n || h[s.peek()] > h[i])) {
                    int ht = h[s.pop()];
                    ans = Math.max(ans, ht * (s.isEmpty() ? i : i - s.peek() - 1));
                }
                s.push(i);
            }
        }
        return ans;
    }
}