public class Task4 {

    public static void main(String[] args) {
        // Define a Sudoku puzzle with 0s representing empty cells
        int[][] sudokuGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku Puzzle:");
        printGrid(sudokuGrid);

        if (solveSudoku(sudokuGrid)) {
            System.out.println("Solved Sudoku Puzzle:");
            printGrid(sudokuGrid);
        } else {
            System.out.println("No solution exists");
        }
    }

    // Function to print the Sudoku grid
    public static void printGrid(int[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print((grid[r][c] != 0 ? grid[r][c] : ".") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to check if placing num at grid[row][col] is valid
    public static boolean isValid(int[][] grid, int row, int col, int num) {
        // Check if num is not repeated in the current row
        for (int c = 0; c < 9; c++) {
            if (grid[row][c] == num) {
                return false;
            }
        }

        // Check if num is not repeated in the current column
        for (int r = 0; r < 9; r++) {
            if (grid[r][col] == num) {
                return false;
            }
        }

        // Check if num is not repeated in the 3x3 sub-grid
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (grid[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to solve the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] grid) {
        int[] emptyLocation = findEmptyLocation(grid);
        if (emptyLocation == null) {
            return true; // Puzzle solved
        }

        int row = emptyLocation[0];
        int col = emptyLocation[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid)) {
                    return true;
                }
                grid[row][col] = 0; // Undo the move
            }
        }

        return false;
    }

    // Function to find an empty location in the grid
    public static int[] findEmptyLocation(int[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (grid[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
}

