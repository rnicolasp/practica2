import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pnegre on 20/11/16.
 */
public class TestTotal {
    @Test
    public void testTotal() {
        String m = "L'amic ha de ser com els diners, que abans de necessitar-lo, se sap el valor que té.";
        String c1 = Vigenere.encode(m, "ordinador");
        String c2 = Caesar.cypher(c1, 66);
        String c3 = Transposition.cypher(c2, 5);
        String c4 = Transposition.cypher(c3, "LICEU");
        assertEquals("GK 'OP Z  SIR. KGJKC ,-WOKDVH FTSWEHU WQB DWEBIJBWOOGKO UEW RKYFRKSP   K YGFKSQSW X,", c4);

        String c5 = Transposition.decypher(c4, "LICEU");
        String c6 = Transposition.decypher(c5, 5);
        String c7 = Caesar.decypher(c6, 66);
        String c8 = Vigenere.decode(c7, "ordinador");
        assertEquals("L'AMIC HA DE SER COM ELS DINERS, QUE ABANS DE NECESSITAR-LO, SE SAP EL VALOR QUE TE.", c8);
    }
}