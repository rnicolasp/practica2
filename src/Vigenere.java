public class Vigenere {

    // Funció per xifrar un missatge utilitzant el xifratge de Vigenère
    public static String encode(String message, String password) {
        // Crida a la funció processText amb el paràmetre encode = true
        return processText(message, password, true);
    }

    // Funció per desxifrar un missatge utilitzant el xifratge de Vigenère
    public static String decode(String message, String password) {
        // Crida a la funció processText amb el paràmetre encode = false
        return processText(message, password, false);
    }

    // Funció principal que realitza el xifratge o desxifratge
    private static String processText(String text, String password, boolean encode) {
        // Normalitza el text i la clau (converteix a majúscules i substitueix caràcters especials)
        text = normalitzar(text);
        password = normalitzar(password);

        // StringBuilder per emmagatzemar el resultat
        StringBuilder result = new StringBuilder();
        int passwordLength = password.length(); // Longitud de la clau

        // Bucle per processar cada caràcter del text
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i); // Caràcter actual del text

            // Si el caràcter és una lletra de l'alfabet
            if (c >= 'A' && c <= 'Z') {
                // Obtenir el caràcter corresponent de la clau (cíclic amb j % passwordLength)
                char pChar = password.charAt(j % passwordLength);
                // Calcular el desplaçament basat en el caràcter de la clau
                int shift = (pChar - 'A' + 1);

                // Si s'està desxifrant, invertir el desplaçament
                if (!encode) {
                    shift = -shift;
                }

                // Aplicar el desplaçament i assegurar que estigui dins de l'alfabet (0-25)
                int shifted = (c - 'A' + shift + 26) % 26;
                // Convertir el resultat a un caràcter
                char shiftedChar = (char) ('A' + shifted);
                // Afegir el caràcter xifrat/desxifrat al resultat
                result.append(shiftedChar);

                j++; // Incrementar l'índex de la clau
            } else {
                // Si no és una lletra de l'alfabet, afegir el caràcter sense modificar
                result.append(c);
            }
        }

        // Retornar el resultat com a String
        return result.toString();
    }

    // Funció per normalitzar el text (convertir a majúscules i substituir caràcters especials)
    private static String normalitzar(String text) {
        // Convertir el text a majúscules
        text = text.toUpperCase()
                // Substituir caràcters especials per les seves equivalents sense accents
                .replace("À", "A")
                .replace("È", "E")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ò", "O")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("Ü", "U")
                .replace("Ç", "C");
        return text; // Retornar el text normalitzat
    }
}