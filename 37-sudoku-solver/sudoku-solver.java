class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Find an empty cell
                if (board[i][j] == '.') {
                    // Try placing digits from '1' to '9'
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // Put c on the board
                            
                            // Recursively solve the rest of the board
                            if (solve(board)) {
                                return true; 
                            } else {
                                board[i][j] = '.'; // Backtrack
                            }
                        }
                    }
                    // If no number from 1-9 works, this path is invalid
                    return false;
                }
            }
        }
        return true; // Entire board is filled successfully
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // Check row constraint
            if (board[row][i] == c) return false;
            
            // Check column constraint
            if (board[i][col] == c) return false;
            
            // Check 3x3 sub-box constraint
            // 3 * (row / 3) finds the starting row index of the block
            // 3 * (col / 3) finds the starting column index of the block
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}