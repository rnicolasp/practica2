
public class Caesar {
    static String cypher(String s, int delta) {
        StringBuilder result = new StringBuilder();
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c<65 || c>90) {
                if (Character.isLetter(c)) {
                    result.append(c);
                    continue;
                }
            } else if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + delta) % 26 + base);
            }
            result.append(c);
        }
        return result.toString().toUpperCase();
    }

    static String decypher(String s, int delta) {
        return cypher(s, -delta);
    }

    static String magic(String s, String ss) {

        String[] mostCommonWords = {"el", "la", "de", "que", "i", "a", "en"};
        int bestDelta = 0;
        int highestMatchCount = 0;

        for (int delta = 1; delta < 26; delta++) {
            String decoded = decypher(s, delta);
            int matchCount = 0;
            for (String word : mostCommonWords) {
                if (decoded.contains(word)) {
                    matchCount++;
                }
            }

            if (matchCount > highestMatchCount) {
                bestDelta = delta;
                highestMatchCount = matchCount;
            }
        }

        return decypher(s, bestDelta);

    }
}
