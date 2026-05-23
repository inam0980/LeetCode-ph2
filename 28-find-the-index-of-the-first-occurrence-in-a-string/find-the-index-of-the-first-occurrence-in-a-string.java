class Solution {
    public int strStr(String haystack, String needle) {
        // Edge case: if needle is longer than haystack, it can't be a substring
        if (haystack.length() < needle.length()) {
            return -1;
        }
        
        // Loop through haystack, but stop where the needle can no longer physically fit
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            // Check if the substring starting at i matches the needle
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        
        return -1;
    }
}