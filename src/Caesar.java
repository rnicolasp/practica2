import java.util.HashMap;
import java.util.Map;

public class Caesar {
    // Funció per xifrar un text amb el xifratge Cèsar
    static String cypher(String s, int delta) {
        StringBuilder result = new StringBuilder();
        s = s.toUpperCase(); // Converteix el text a majúscules
        delta = ((delta % 26) + 26) % 26; // Normalitza el delta per assegurar que està entre 0 i 25

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') { // Només es xifren els caràcters de l'alfabet
                c = (char) ((c - 'A' + delta) % 26 + 'A'); // Aplica el desplaçament
            }
            result.append(c);
        }
        return result.toString();
    }

    // Funció per desxifrar un text amb el xifratge Cèsar
    static String decypher(String s, int delta) {
        return cypher(s, -delta); // Utilitza la funció cypher amb el delta negatiu
    }

    // Funció per trobar el millor desplaçament basat en la freqüència de paraules
    static String magic(String s, String reference) {
        String[] referenceWords = reference.toUpperCase().split("\\W+"); // Separa el text de referència en paraules
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Compta la freqüència de cada paraula al text de referència
        for (String word : referenceWords) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        int bestDelta = 0;
        int highestScore = 0;

        // Prova tots els desplaçaments possibles (1 a 25)
        for (int delta = 1; delta < 26; delta++) {
            String decoded = decypher(s, delta);
            int score = 0;
            String[] words = decoded.split("\\W+");

            // Calcula la puntuació basada en la freqüència de paraules
            for (String word : words) {
                score += wordFrequency.getOrDefault(word, 0);
            }

            // Actualitza el millor desplaçament si es troba una puntuació més alta
            if (score > highestScore) {
                bestDelta = delta;
                highestScore = score;
            }
        }

        return decypher(s, bestDelta); // Retorna el text desxifrat amb el millor desplaçament
    }
}
