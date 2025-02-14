import java.util.*;

public class PlayFair {

    private static char[][] generateKeySquare(String key) {
        String keyString = key.toUpperCase().replaceAll("[^A-Z]", "");
        keyString = keyString.replace("J", "I");
        keyString += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        keyString = keyString.chars().distinct().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        char[][] keySquare = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keySquare[i][j] = keyString.charAt(i * 5 + j);
            }
        }
        return keySquare;
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        text = text.replace("J", "I");
        StringBuilder preparedText = new StringBuilder(text);
        for (int i = 0; i < preparedText.length() - 1; i += 2) {
            if (preparedText.charAt(i) == preparedText.charAt(i + 1)) {
                preparedText.insert(i + 1, 'X');
            }
        }
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    private static String encryptDigraph(String digraph, char[][] keySquare) {
        int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keySquare[i][j] == digraph.charAt(0)) {
                    row1 = i;
                    col1 = j;
                }
                if (keySquare[i][j] == digraph.charAt(1)) {
                    row2 = i;
                    col2 = j;
                }
            }
        }

        if (row1 == row2) {
            return "" + keySquare[row1][(col1 + 1) % 5] + keySquare[row2][(col2 + 1) % 5];
        } else if (col1 == col2) {
            return "" + keySquare[(row1 + 1) % 5][col1] + keySquare[(row2 + 1) % 5][col2];
        } else {
            return "" + keySquare[row1][col2] + keySquare[row2][col1];
        }
    }

    private static String decryptDigraph(String digraph, char[][] keySquare) {
        int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keySquare[i][j] == digraph.charAt(0)) {
                    row1 = i;
                    col1 = j;
                }
                if (keySquare[i][j] == digraph.charAt(1)) {
                    row2 = i;
                    col2 = j;
                }
            }
        }

        if (row1 == row2) {
            return "" + keySquare[row1][(col1 - 1 + 5) % 5] + keySquare[row2][(col2 - 1 + 5) % 5];
        } else if (col1 == col2) {
            return "" + keySquare[(row1 - 1 + 5) % 5][col1] + keySquare[(row2 - 1 + 5) % 5][col2];
        } else {
            return "" + keySquare[row1][col2] + keySquare[row2][col1];
        }
    }

    public static String encrypt(String text, String pass) {
        char[][] keySquare = generateKeySquare(pass);
        String preparedText = prepareText(text);
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) {
            String digraph = preparedText.substring(i, i + 2);
            encryptedText.append(encryptDigraph(digraph, keySquare)).append(" ");
        }
        return encryptedText.toString().trim();
    }

    public static String decrypt(String text, String pass) {
        char[][] keySquare = generateKeySquare(pass);
        String preparedText = prepareText(text);
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) {
            String digraph = preparedText.substring(i, i + 2);
            decryptedText.append(decryptDigraph(digraph, keySquare)).append(" ");
        }
        return decryptedText.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("MALETA", "LICEU")); // Output: HD IU PG
        System.out.println(decrypt("HD IU PG", "LICEU")); // Output: MA LE TA
    }
}