package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hard {

    char aiPlayer;

    char huPlayer;

    public Hard(char aiPlayer, char huPlayer) {
        this.huPlayer = huPlayer;
        this.aiPlayer = aiPlayer;
    }

    public static ArrayList<Integer> emptyCells(String board) {
        ArrayList<Integer> freeCells = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            if (board.charAt(i) == ' ') {
                freeCells.add(i);
            }
        }
        return freeCells;
    }

    // the main minimax function
    public Map<String, Integer> minimax(String board, char player) {
        //available spots
        var availSpots = emptyCells(board);
        // checks for the terminal states such as win, lose, and tie
        //and returning a value accordingly
        if (getWinner(board, aiPlayer)) {
            return Map.of("score", 10);
        } else if (getWinner(board, huPlayer)) {
            return Map.of("score", -10);
        } else if (availSpots.size() == 0) {
            return Map.of("score", 0);
        }
        // an array to collect all the objects
        ArrayList<Map<String, Integer>> moves = new ArrayList<>();

        // loop through available spots
        for (int i = 0; i < availSpots.size(); i++) {
            //create an object for each and store the index of that spot
            Map<String, Integer> move = new HashMap<>();
            move.put("index", availSpots.get(i));
            // set the empty spot to the current player
            board = board.substring(0, availSpots.get(i)) + player + board.substring(availSpots.get(i) + 1);
             /*collect the score resulted from calling minimax
                 on the opponent of the current player*/
            Map<String, Integer> result;
            if (player == aiPlayer) {
                result = minimax(board, huPlayer);
            } else {
                result = minimax(board, aiPlayer);
            }
            move.put("score", result.get("score"));

            // reset the spot to empty
            board = board.substring(0, move.get("index")) + ' ' + board.substring(move.get("index") + 1);
            // push the object to the array
            moves.add(move);

        }
        // if it is the computer's turn loop over the moves and choose the move with the highest score
        Map<String, Integer> bestMove = null;
        if (player == aiPlayer) {
            var bestScore = Integer.MIN_VALUE;
            for (Map<String, Integer> move : moves) {
                if (move.get("score") > bestScore) {
                    bestScore = move.get("score");
                    bestMove = move;
                }

            }
            // if it is the human's turn loop over the moves and choose the move with the lowest score
        } else {
            var bestScore = Integer.MAX_VALUE;
            for (Map<String, Integer> move : moves) {
                if (move.get("score") < bestScore) {
                    bestScore = move.get("score");
                    bestMove = move;
                }

            }
        }


//        return bestMove.get("index");
        return bestMove;
    }

    public static boolean getWinner(String board, char player) {

        return (board.charAt(0) == player && board.charAt(1) == player && board.charAt(2) == player) ||
                (board.charAt(3) == player && board.charAt(4) == player && board.charAt(5) == player) ||
                (board.charAt(6) == player && board.charAt(7) == player && board.charAt(8) == player) ||
                (board.charAt(0) == player && board.charAt(3) == player && board.charAt(6) == player) ||
                (board.charAt(1) == player && board.charAt(4) == player && board.charAt(7) == player) ||
                (board.charAt(2) == player && board.charAt(5) == player && board.charAt(8) == player) ||
                (board.charAt(0) == player && board.charAt(4) == player && board.charAt(8) == player) ||
                (board.charAt(2) == player && board.charAt(4) == player && board.charAt(6) == player);


    }
}
