class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] count = new int[128];

        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        int required = t.length();

        while (right < s.length()) {
            char c = s.charAt(right);

            if (count[c] > 0) {
                required--;
            }

            count[c]--;
            right++;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char ch = s.charAt(left);
                count[ch]++;

                if (count[ch] > 0) {
                    required++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }
}