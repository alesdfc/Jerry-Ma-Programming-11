public class Board {
    enum tileTypes {
        FLAGRIGHT, FLAGWRONG, MINECLICKED, MINENOTCLICKED, NUMBERCLICKED, NUMBERNOTCLICKED, NOTVALID
    }

    // Board representation
    private tileTypes[][] board;
    private int[][] intBoard;

    // Game information
    private int revealed;
    private int flagsLeft;

    // Time
    private long startingTime;
    private long endingTime;

    // Game states
    private boolean first = true;
    private boolean hasWon = false;
    private boolean hasLost = false;

    // Constructor for the Board Object
    Board(int x, int y, int mine) {

        // Initializes variables
        board = new tileTypes[x][y];
        intBoard = new int[x][y];
        revealed = mine;
        flagsLeft = mine;

        // Sets up the board with number tiles
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = tileTypes.NUMBERNOTCLICKED;
            }
        }

        // Randomly generates mines
        while (mine > 0) {

            // Generates a random coordinate to be a mine
            int xVal = (int) (Math.random() * x);
            int yVal = (int) (Math.random() * y);

            // If the tile is not a mine already, place a mine with the randomly generated
            // coordinates
            if (board[xVal][yVal] != tileTypes.MINENOTCLICKED) {
                board[xVal][yVal] = tileTypes.MINENOTCLICKED;
                mine--;
            }
        }
    }

    // Function for if the user left clicks
    public void leftClick(int x, int y) {

        // Starts the time and makes sure the first tile you click cannot be a mine
        if (first) {
            first = false;
            startingTime = System.currentTimeMillis();

            // If the tile clicked is a mine, randomly generate a new coordinate for the
            // mine to be
            if (board[x][y] == tileTypes.MINENOTCLICKED) {
                while (true) {

                    // Randomly generates coordinates
                    int xVal = (int) (Math.random() * x);
                    int yVal = (int) (Math.random() * y);

                    // If the coordinates generated is an open space, set it to be a mine
                    if (board[xVal][yVal] != tileTypes.MINENOTCLICKED) {
                        board[xVal][yVal] = tileTypes.MINENOTCLICKED;
                        board[x][y] = tileTypes.NUMBERNOTCLICKED;
                    }
                    break;
                }
            }
        }

        // Checks the tile type
        if (board[x][y] == tileTypes.MINENOTCLICKED) {
            // Sets the board state to be losing
            board[x][y] = tileTypes.MINECLICKED;
            endingTime = System.currentTimeMillis();
            hasLost = true;
        } else if (board[x][y] == tileTypes.NUMBERNOTCLICKED) {
            // Counts the adjacent number of mines
            int mineCount = countMines(x, y);
            revealed++;
            board[x][y] = tileTypes.NUMBERCLICKED;

            if (mineCount > 0) {
                // Makes the tile clicked have the number of mines in a 1 tile radius if the
                // amount of mines is greater than 1
                intBoard[x][y] = mineCount;
            } else if (mineCount == 0) {
                // Expands the zero if there are no mines adjacent to the tile
                expandZero(x, y);
            }
            // Tile is non-zero number tile
        } else if (intBoard[x][y] > 0 && intBoard[x][y] <= 8) {
            chord(x, y);
        }

        // Sets the game state to have won if every tile has been revealed
        if (revealed == (board.length * board[0].length)) {
            hasWon = true;
            endingTime = System.currentTimeMillis();
        }
    }

    // Function for if the user right clicks
    public void rightClick(int x, int y) {
        // Starts the time if this is the first action in the game
        if (first) {
            first = false;
            startingTime = System.currentTimeMillis();
        }

        // Coordinate is an unrevealed number and flags are available
        if (board[x][y] == tileTypes.NUMBERNOTCLICKED && flagsLeft > 0) {
            // Flags the tile given to be wrong if the tile flagged is actually a number.
            flagsLeft--;
            board[x][y] = tileTypes.FLAGWRONG;

        } else if (board[x][y] == tileTypes.MINENOTCLICKED && flagsLeft > 0) {
            // Flags the tile given to be correct if the tile flagged is a mine
            flagsLeft--;
            board[x][y] = tileTypes.FLAGRIGHT;
        }

        // Removes a flag if the tile right clicked is a flag
        else if (board[x][y] == tileTypes.FLAGRIGHT) {
            flagsLeft++;
            board[x][y] = tileTypes.MINENOTCLICKED;
        } else if (board[x][y] == tileTypes.FLAGWRONG) {
            flagsLeft++;
            board[x][y] = tileTypes.NUMBERNOTCLICKED;
        }
    }

    // Checks the given coordinate to see if it exists on the board
    private tileTypes checkBoard(int x, int y) {

        // If the given X coordinate or Y coordinate is over the board's length for that
        // side, return NOTVALID
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
            return tileTypes.NOTVALID;
        } else { // If the X and Y coordinate exist inside of the board
            return board[x][y];
        }
    }

    // Chords the coordinate given
    public void chord(int x, int y) {
        // Counts the number of flags around the coordinate
        int flagCount = countFlags(x, y);

        // Checks if the number of flags is equal to the number of mines adjacent to the
        // clicked tile
        if (intBoard[x][y] == flagCount) {

            // Holds adjacent tiles if both coordinates are added respectively
            int[] xval = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
            int[] yval = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

            // Clicks on all the numbers around that are not flagged
            for (int i = 0; i < xval.length; i++) {
                if (checkBoard(x + xval[i], y + yval[i]) == tileTypes.NUMBERNOTCLICKED
                        || checkBoard(x + xval[i], y + yval[i]) == tileTypes.MINENOTCLICKED) {
                    leftClick(x + xval[i], y + yval[i]);
                }
            }
        }
    }

    // Left clicks everything around the coordinate if the number has not already
    // been clicked
    private void expandZero(int x, int y) {
        int[] xval = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
        int[] yval = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

        // Left clicks everything around the given coordinate
        for (int i = 0; i < xval.length; i++) {

            // checks whether or not the adjacent coordinate is a number not revealed yet
            if (checkBoard(x + xval[i], y + yval[i]) == tileTypes.NUMBERNOTCLICKED) {
                leftClick(x + xval[i], y + yval[i]);
            }
        }
    }

    // Returns the amount of mines around a coordinate
    private int countMines(int x, int y) {

        // Initializes the needed coordinates and the number of mines next to the input
        // coordinates
        int mineCount = 0;
        int[] xval = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
        int[] yval = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

        // Checks the adjacent tiles next to the input coordinates for a mine not
        // clicked or for a correct flag
        for (int i = 0; i < xval.length; i++) {
            if (checkBoard(x + xval[i], y + yval[i]) == tileTypes.MINENOTCLICKED
                    || checkBoard(x + xval[i], y + yval[i]) == tileTypes.FLAGRIGHT) {
                mineCount++;
            }
        }

        // Returns the number of mines next to the given coordinates
        return mineCount;
    }

    // Counts the flags around a coordinate
    private int countFlags(int x, int y) {
        int flagCount = 0;
        int[] xval = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
        int[] yval = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };

        // Checks the adjacent tiles next to the given coordinates for flags
        for (int i = 0; i < xval.length; i++) {

            // Checks whether or not the adjacent tile is a correct or wrong flag
            if (checkBoard(x + xval[i], y + yval[i]) == tileTypes.FLAGRIGHT
                    || checkBoard(x + xval[i], y + yval[i]) == tileTypes.FLAGWRONG) {
                flagCount++;
            }
        }

        // Returns the number of flags next to the tile
        return flagCount;
    }

    // Getters and Setters

    public long getElapsedTime() {
        // End state is reached
        if (hasWon || hasLost) {
            return endingTime - startingTime;
            // An end state has not been reached
        } else if (!first) {
            return System.currentTimeMillis() - startingTime;
        } else { // the first click has not been done yet
            return 0;
        }
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public boolean getHasLost() {
        return hasLost;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    // UNSAFE
    public tileTypes[][] getTileBoard() {
        return board;
    }

    // UNSAFE
    public int[][] getIntBoard() {
        return intBoard;
    }
}
