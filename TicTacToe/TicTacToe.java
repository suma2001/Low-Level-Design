package TicTacToe;

import TicTacToe.models.*;

import java.util.*;

public class TicTacToe {
    Deque<Player> players;
    Board gameBoard;

    public void initializeGame() {
        // Create 2 players
        players = new LinkedList<>();
        PlayingPiece crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece);

        PlayingPiece noughtsPiece = new PlayingPieceO();
        Player player2 = new Player("Player2", noughtsPiece);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;

        while(noWinner) {
            // Pick the player with next turn
            Player playerTurn = players.removeFirst();

            // Print board
            gameBoard.printBoard();

            // Get free spaces
            List<Pair> freeSpaces = gameBoard.getFreeCells();
            if(freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.println(freeSpaces.toString());

            // Take user input
            System.out.print(STR."Player:\{playerTurn.getName()} Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            // Place piece
            boolean pieceAdded = gameBoard.addPiece(inputRow, inputColumn, playerTurn.getPlayingPiece());
            if(!pieceAdded) {
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isWinner(inputRow, inputColumn, playerTurn.getPlayingPiece().pieceType);
            if(winner) {
                return playerTurn.getName();
            }
        }
        return "TIE";
    }

    boolean isWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.getSize();i++) {

            if(gameBoard.getBoard()[row][i] == null || gameBoard.getBoard()[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.getSize();i++) {

            if(gameBoard.getBoard()[i][column] == null || gameBoard.getBoard()[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.getSize();i++,j++) {
            if (gameBoard.getBoard()[i][j] == null || gameBoard.getBoard()[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize();i++,j--) {
            if (gameBoard.getBoard()[i][j] == null || gameBoard.getBoard()[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
