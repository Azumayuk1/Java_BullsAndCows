import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean grade(String secretCode, String guessedCode) {
        // Grading input code
        int bulls = 0;
        int cows = 0;

        if(guessedCode.length() > secretCode.length()) {
            System.out.println("Guess is too long!");
            return false;
        }

        for (int i = 0; i < guessedCode.length(); i++) {
            if (guessedCode.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else if (secretCode.contains("" + guessedCode.charAt(i))) {
                cows++;
            }
        }

        if (bulls == secretCode.length()) {
            System.out.println("Grade: " + secretCode.length() + " bulls");
            System.out.println("Congratulations! You guessed the secret code.");
            return true;
        }



        // Printing grade
        if (bulls != 0 && cows != 0) {
            System.out.print("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
            return false;
        } else if (bulls != 0) {
            System.out.print("Grade: " + bulls + " bull(s).");
            return false;
        } else if (cows != 0) {
            System.out.print("Grade: " + cows + " cow(s).");
            return false;
        } else {
            System.out.print("Grade: None.");
            return false;
        }
    }

//    public static String generateRandomCodeSimple(int requiredLength) {
//        List<Integer> randomListOfIntegers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//        Collections.shuffle(randomListOfIntegers);
//
//        StringBuilder randomCode = new StringBuilder();
//
//        for (int i = 0; i < requiredLength; i++) {
//            randomCode.append(randomListOfIntegers.get(i));
//        }
//
//        return randomCode.toString();
//    }

    public static String generateRandomCodeComplex(int requiredLength, int possibleCharactersAmount) {
        String allCharacters = "0123456789abcdefghijklmnopqrstuvwxyz";
        List<String> randomList = new ArrayList<>(List.of());
        for (int i = 0; i < possibleCharactersAmount; i++) {
            randomList.add(String.valueOf(allCharacters.charAt(i)));
        }
        Collections.shuffle(randomList);

        StringBuilder randomCode = new StringBuilder();

        for (int i = 0; i < requiredLength; i++) {
            randomCode.append(randomList.get(i));
        }

        return randomCode.toString();
    }

    public static void outputSecretCode(String secretCode, int possibleSymbolsAmount) {
        String characters = "abcdefghijklmnopqrstuvwxyz";

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < secretCode.length(); i++) System.out.print("*");
        if (possibleSymbolsAmount <= 10) {
            System.out.print(" (0-" + (possibleSymbolsAmount - 1) + ").");
        } else {
            System.out.print(" (0-9, a-" + characters.charAt(possibleSymbolsAmount - 11) + ").");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length: ");
        int randomNumberLength = 0;

        try {
            randomNumberLength = scanner.nextInt();
            if(randomNumberLength < 1) {
                System.out.println("Error: code length is too small.");
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: not a valid number.");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error: invalid input.");
            System.exit(-1);
        }

        String secretCode = "";

        if (randomNumberLength > 36) {
            System.out.println("Error: can't generate a secret number with a length of "
                    + randomNumberLength + " because there aren't enough unique symbols (max is 36: 0-9, a-z).");
            System.exit(-1);
        } else {
            System.out.println("Input the number of possible symbols in the code:");

            int possibleSymbolsAmount = 0;
            try {
                possibleSymbolsAmount = scanner.nextInt();

                if (possibleSymbolsAmount < randomNumberLength)
                    throw new Exception("Error: length is smaller than number of unique symbols.");

                if(possibleSymbolsAmount > 36) {
                    throw new Exception("Error: not enough unique symbols (max is 36: 0-9, a-z).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: not a valid number.");
                System.exit(-1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }

            secretCode = generateRandomCodeComplex(randomNumberLength, possibleSymbolsAmount);
            outputSecretCode(secretCode, possibleSymbolsAmount);
        }

        System.out.println("Okay, let's start a game!");
        int turnCounter = 0;

        while (true) {
            turnCounter++;

            System.out.println("\nTurn " + turnCounter + ":");

            String userGuess = scanner.next();
            if (grade(secretCode, userGuess)) {
                break;
            }
        }
    }
}