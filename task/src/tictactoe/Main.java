package tictactoe;

import java.util.*;

public class Main {

    static String board;
    static int coordinate1;
    static int coordinate2;
    static final int GRID = 3;
    static List<Integer> givenList;
    static Integer index;
    static Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);

    /**
     * the main method handles the user input and calls other method
     *
     */
    public static void main(String[] args) {
        givenList = new LinkedList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        board = "         ";
        boolean exitLoop = false;
        while (!exitLoop) {
            exitLoop = true;
            System.out.println("Input command: ");
            String userCommand = scanner.nextLine();
            String[] temp = userCommand.trim().split(" ");

            if (temp[0].equals("exit")) {
                System.exit(0);
            } else if (temp.length != 3) {
                System.out.println("Bad parameters h");
                exitLoop = false;

            } else if (temp[0].equals("start")) {
                switch (temp[1]) {
                    case "easy" -> {
                        switch (temp[2]) {
                            case "easy" -> aiVsAi("easy","easy");
                            case "medium" -> aiVsAi("easy","medium");
                            case "hard" -> aiVsAi("easy","hard"); //
                            case "user" -> userVsAi('O', "easy");
                            default -> {
                                System.out.println("Bad parameters");
                                exitLoop = false;
                            }
                        }
                    }
                    case "medium" -> {
                        switch (temp[2]) {
                            case "easy" -> aiVsAi("medium","easy");
                            case "medium" -> aiVsAi("medium","medium");
                            case "hard" -> aiVsAi("medium","hard");//
                            case "user" -> userVsAi('O', "medium");
                            default -> {
                                System.out.println("Bad parameters");
                                exitLoop = false;
                            }
                        }
                    }
                    case "hard" -> {
                        switch (temp[2]) {
                            case "easy" -> aiVsAi("hard","easy");
                            case "medium" -> aiVsAi("hard","medium");
                            case "hard" -> aiVsAi("hard","hard");//
                            case "user" -> userVsAi('O', "hard");
                            default -> {
                                System.out.println("Bad parameters");
                                exitLoop = false;
                            }
                        }
                    }
                    case "user" -> {
                        switch (temp[2]) {
                            case "easy" -> userVsAi('X', "easy");
                            case "medium" -> userVsAi('X', "medium");
                            case "hard" -> userVsAi('X', "hard");
                            case "user" -> userVsUser();
                            default -> {
                                System.out.println("Bad parameters");
                                exitLoop = false;
                            }
                        }
                    }
                    default -> {
                        System.out.println("Bad parameters");
                        exitLoop = false;
                    }
                }
            } else {
                System.out.println("Bad parameters");
                exitLoop = false;
            }
        }
    }

    /**
     * play takes either X or O, puts it on the board depending on class variable index location  and prints the board
     * ends the game if there is a winner or a draw
     * @param ch
     */
    public static void play(char ch) {
        board = board.substring(0, index) + ch + board.substring(index + 1);
        givenList.remove(index);
        printBoard();
        int winner = getWinner(board);
        if (winner == 10) {
            System.out.println(ch + " wins");
            System.exit(0);
        } else if (winner == 0) {
            System.out.println("Draw");
            System.exit(0);
        }
    }

    public static void printBoard() {
        System.out.println("---------");
        System.out.println("| " + board.charAt(0) + " " + board.charAt(1) + " " + board.charAt(2) + " |");
        System.out.println("| " + board.charAt(3) + " " + board.charAt(4) + " " + board.charAt(5) + " |");
        System.out.println("| " + board.charAt(6) + " " + board.charAt(7) + " " + board.charAt(8) + " |");
        System.out.println("---------");
    }


    public static int getWinner(String board) {

        boolean topRow = board.charAt(0) == board.charAt(1) && board.charAt(0) == board.charAt(2) && board.charAt(0) != ' ';
        boolean middleRow = board.charAt(3) == board.charAt(4) && board.charAt(3) == board.charAt(5) && board.charAt(5) != ' ';
        boolean bottomRow = board.charAt(6) == board.charAt(7) && board.charAt(6) == board.charAt(8) && board.charAt(6) != ' ';
        boolean diagonal = board.charAt(6) == board.charAt(4) && board.charAt(6) == board.charAt(2) && board.charAt(2) != ' ';
        boolean diagonal2 = board.charAt(0) == board.charAt(4) && board.charAt(0) == board.charAt(8) && board.charAt(0) != ' ';
        boolean verticalLeft = board.charAt(0) == board.charAt(3) && board.charAt(0) == board.charAt(6) && board.charAt(0) != ' ';
        boolean verticalMiddle = board.charAt(1) == board.charAt(4) && board.charAt(1) == board.charAt(7) && board.charAt(7) != ' ';
        boolean verticalRight = board.charAt(2) == board.charAt(5) && board.charAt(2) == board.charAt(8) && board.charAt(2) != ' ';

        if (topRow || verticalLeft || middleRow || diagonal || diagonal2 || verticalMiddle || bottomRow || verticalRight) {
            return 10;
        } else if (board.indexOf(' ') == -1) return 0;
         else return -10;

    }

    public static void aiVsAi(String mode,String secondMode) {
        printBoard();
        while (true) {
            try {
                char istLetter = 'X';
                char secondLetter = 'O';
                switch (mode) {
                    case "easy" -> aiEasyMove(istLetter);
                    case "medium" -> aiMediumMove(istLetter);
                    case "hard" -> aiHardMove(istLetter,secondLetter);
                }
//                Thread.sleep(3000);
                istLetter = 'O';
                secondLetter = 'X';
                switch (secondMode) {
                    case "easy" -> aiEasyMove(istLetter);
                    case "medium" -> aiMediumMove(istLetter);
                    case "hard" -> aiHardMove(istLetter,secondLetter);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void userVsAi(char userLetter, String mode) {
        printBoard();
        char aiLetter = userLetter == 'X' ? 'O' : 'X';
        while (true) {
            if (userLetter == 'X') {
                getUserMove();
                play(userLetter);
                switch (mode) {
                    case "easy" -> aiEasyMove(aiLetter);
                    case "medium" -> aiMediumMove(aiLetter);
                    case "hard"  -> aiHardMove(aiLetter,userLetter);
                }

            } else {
                switch (mode) {
                    case "easy" -> aiEasyMove(aiLetter);
                    case "medium" -> aiMediumMove(aiLetter);
                    case "hard"  -> aiHardMove(aiLetter,userLetter);
                }
                getUserMove();
                play(userLetter);
            }
        }
    }

    public static void userVsUser() {
        printBoard();
        while (true) {
            char ch = 'X';
            getUserMove();
            play(ch);
            ch = 'O';
            getUserMove();
            play(ch);
        }
    }

    public static void getUserMove() {
        do {
            try {
                index = 20; //exit condition
                System.out.println("Enter the coordinates: ");
                coordinate1 = scanner.nextInt() - 1; // - 1 here is part of the algorithm to calculate index position
                coordinate2 = scanner.nextInt() - 1;
                boolean checkNumber = 0 <= coordinate1 && coordinate1 <= 2 && 0 <= coordinate2 && coordinate2 <= 2;
                if (!checkNumber) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                index = (coordinate1 * GRID) + coordinate2; //this calculates the position on the board
                if (!givenList.contains(index)) {
                    index = 20;
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException ie) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        } while (index == 20);

    }


    public static void aiEasyMove(char ch) {
        System.out.println("Making move level \"easy\"");
        index = givenList.get(rand.nextInt(givenList.size()));
        play(ch);
    }

    public static void aiMediumMove(char ch) {

        boolean topRow = (board.charAt(0) == board.charAt(1) || board.charAt(0) == board.charAt(2) || board.charAt(1) == board.charAt(2));
        boolean middleRow = (board.charAt(3) == board.charAt(4) || board.charAt(3) == board.charAt(5) || board.charAt(4) == board.charAt(5));
        boolean bottomRow = (board.charAt(6) == board.charAt(7) || board.charAt(6) == board.charAt(8) || board.charAt(7) == board.charAt(8));
        boolean diagonal = (board.charAt(6) == board.charAt(4) || board.charAt(6) == board.charAt(2) || board.charAt(4) == board.charAt(2));
        boolean diagonal2 = (board.charAt(0) == board.charAt(4) || board.charAt(0) == board.charAt(8) || board.charAt(4) == board.charAt(8));
        boolean verticalLeft = (board.charAt(0) == board.charAt(3) || board.charAt(0) == board.charAt(6) || board.charAt(3) == board.charAt(6));
        boolean verticalMiddle = (board.charAt(1) == board.charAt(4) || board.charAt(1) == board.charAt(7) || board.charAt(4) == board.charAt(7));
        boolean verticalRight = (board.charAt(2) == board.charAt(5) || board.charAt(2) == board.charAt(8) || board.charAt(5) == board.charAt(8));

        //getPosition(array) != -1 here is used to check that there is an empty row in the line
        if (topRow && getFreeCell(new int[]{0, 1, 2}) != -1) {
            index = getFreeCell(new int[]{0, 1, 2});
        } else if (middleRow && getFreeCell(new int[]{3, 4, 5}) != -1) {
            index = getFreeCell(new int[]{3, 4, 5});
        } else if (bottomRow && getFreeCell(new int[]{6, 7, 8}) != -1) {
            index = getFreeCell(new int[]{6, 7, 8});
        } else if (diagonal && getFreeCell(new int[]{2, 6, 4}) != -1) {
            index = getFreeCell(new int[]{2, 6, 4});
        } else if (diagonal2 && getFreeCell(new int[]{0, 8, 4}) != -1) {
            index = getFreeCell(new int[]{0, 8, 4});
        } else if (verticalLeft && getFreeCell(new int[]{0, 3, 6}) != -1) {
            index = getFreeCell(new int[]{0, 3, 6});
        } else if (verticalMiddle && getFreeCell(new int[]{1, 4, 7}) != -1) {
            index = getFreeCell(new int[]{1, 4, 7});
        } else if (verticalRight && getFreeCell(new int[]{2, 5, 8}) != -1) {
            index = getFreeCell(new int[]{2, 5, 8});
        } else {
            index = givenList.get(rand.nextInt(givenList.size()));
        }
        System.out.println("Making move level \"medium\"");
        play(ch);

    }
    public static void aiHardMove(char aiLetter, char userLetter) {
        System.out.println("Making move level \"hard\"");
        Hard hard = new Hard(aiLetter,userLetter);
        index = hard.minimax(board,aiLetter).get("index");
//        System.out.println(index);
        play(aiLetter);
    }

    /**
     * this method is used by the aiMediumMove method to determine a free cell
     * @param possibleCells
     * @return int
     */
    public static int getFreeCell(int[] possibleCells) {
        int freeCell = -1;
        int countOfEmptyCell = 0;
        for (int i : possibleCells) {
            if (board.charAt(i) == ' ') {
                countOfEmptyCell++;
                freeCell = i;
            }
        }
        if (countOfEmptyCell > 1) {  //this checks that the matching cells are not empty
            freeCell = -1;
        }
        return freeCell;
    }

}