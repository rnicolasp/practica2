import java.util.*;

public class Transposition {

    // Funció per xifrar un text utilitzant una transposició per columnes amb una dimensió fixa
    static String cypher(String s, int dim) {
        StringBuilder result = new StringBuilder(); // StringBuilder per emmagatzemar el resultat
        int length = s.length(); // Longitud del text
        int rows = (int) Math.ceil((double) length / dim); // Nombre de files necessàries

        // Recorre cada columna
        for (int col = 0; col < dim; col++) {
            // Recorre cada fila
            for (int row = 0; row < rows; row++) {
                int index = row * dim + col; // Calcula l'índex del caràcter original
                if (index < length) { // Si l'índex és vàlid
                    result.append(s.charAt(index)); // Afegeix el caràcter al resultat
                }
            }
        }
        return result.toString(); // Retorna el text xifrat
    }

    // Funció per desxifrar un text utilitzant una transposició per columnes amb una dimensió fixa
    static String decypher(String s, int dim) {
        StringBuilder result = new StringBuilder(); // StringBuilder per emmagatzemar el resultat
        int length = s.length(); // Longitud del text
        int rows = (int) Math.ceil((double) length / dim); // Nombre de files necessàries
        int fullCols = length % dim; // Nombre de columnes completes
        if (fullCols == 0) fullCols = dim; // Si no hi ha columnes incompletes, totes són completes

        // Recorre cada fila
        for (int row = 0; row < rows; row++) {
            // Recorre cada columna
            for (int col = 0; col < dim; col++) {
                int index;
                if (col < fullCols) { // Si la columna és completa
                    index = col * rows + row; // Calcula l'índex del caràcter xifrat
                } else { // Si la columna és incompleta
                    if (row >= rows - 1) continue; // Ignora la fila extra en columnes incompletes
                    index = fullCols * rows + (col - fullCols) * (rows - 1) + row; // Calcula l'índex
                }
                if (index < length) { // Si l'índex és vàlid
                    result.append(s.charAt(index)); // Afegeix el caràcter al resultat
                }
            }
        }
        return result.toString(); // Retorna el text desxifrat
    }

    // Funció per xifrar un text utilitzant una transposició per columnes amb una clau
    static String cypher(String s, String key) {
        List<Integer> order = getColumnOrder(key); // Obtenir l'ordre de les columnes basat en la clau
        int dim = key.length(); // Nombre de columnes (longitud de la clau)
        int length = s.length(); // Longitud del text
        StringBuilder result = new StringBuilder(); // StringBuilder per emmagatzemar el resultat

        // Recorre les columnes en l'ordre definit per la clau
        for (int col : order) {
            // Recorre cada fila
            for (int row = 0; row < Math.ceil((double) length / dim); row++) {
                int index = row * dim + col; // Calcula l'índex del caràcter original
                if (index < length) { // Si l'índex és vàlid
                    result.append(s.charAt(index)); // Afegeix el caràcter al resultat
                }
            }
        }
        return result.toString(); // Retorna el text xifrat
    }

    // Funció per desxifrar un text utilitzant una transposició per columnes amb una clau
    static String decypher(String s, String key) {
        int dim = key.length(); // Nombre de columnes (longitud de la clau)
        int len = s.length(); // Longitud del text
        List<Integer> order = getColumnOrder(key); // Obtenir l'ordre de les columnes basat en la clau
        int rows = (int) Math.ceil((double) len / dim); // Nombre de files necessàries
        int fullCols = len % dim; // Nombre de columnes completes
        if (fullCols == 0) fullCols = dim; // Si no hi ha columnes incompletes, totes són completes

        // Array per emmagatzemar la longitud original de cada columna
        int[] originalColLengths = new int[dim];
        for (int i = 0; i < dim; i++) {
            originalColLengths[i] = (i < fullCols) ? rows : rows - 1; // Assigna la longitud corresponent
        }

        // Llista per emmagatzemar les columnes ordenades
        List<String> sortedColumns = new ArrayList<>();
        int current = 0; // Índex actual en el text xifrat

        // Separa el text xifrat en columnes segons l'ordre definit per la clau
        for (int col : order) {
            int colLength = originalColLengths[col]; // Longitud de la columna actual
            int end = Math.min(current + colLength, len); // Final de la columna actual
            sortedColumns.add(s.substring(current, end)); // Afegeix la columna a la llista
            current = end; // Actualitza l'índex actual
        }

        // Array per reconstruir les columnes originals
        String[] originalColumns = new String[dim];
        for (int i = 0; i < order.size(); i++) {
            originalColumns[order.get(i)] = sortedColumns.get(i); // Assigna les columnes al seu lloc original
        }

        // StringBuilder per reconstruir el text desxifrat
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < dim; col++) {
                String colStr = originalColumns[col]; // Columna actual
                if (colStr != null && row < colStr.length()) { // Si la columna existeix i té caràcters
                    result.append(colStr.charAt(row)); // Afegeix el caràcter al resultat
                }
            }
        }

        // Retorna el text desxifrat, eliminant espais en blanc al final
        return result.toString().replaceAll("\\s+$", "");
    }

    // Funció per obtenir l'ordre de les columnes basat en una clau
    private static List<Integer> getColumnOrder(String key) {
        List<Integer> indices = new ArrayList<>(); // Llista per emmagatzemar els índexs de les columnes
        for (int i = 0; i < key.length(); i++) {
            indices.add(i); // Afegeix l'índex de cada columna
        }
        // Ordena els índexs segons l'ordre alfabètic dels caràcters de la clau
        indices.sort(Comparator.comparingInt(a -> key.charAt(a)));
        return indices; // Retorna l'ordre de les columnes
    }
}