class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        
        String result = "1";
        
        // Build the sequence iteratively up to n
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            
            // Traverse the previous string to find runs of identical characters
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    // Append frequency followed by the digit character
                    sb.append(count).append(result.charAt(j - 1));
                    count = 1; // Reset count for the new character
                }
            }
            // Append the final group
            sb.append(count).append(result.charAt(result.length() - 1));
            result = sb.toString();
        }
        
        return result;
    }
}