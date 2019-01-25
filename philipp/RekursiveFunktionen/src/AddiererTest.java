import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddiererTest {
    Addierer addierer = new Addierer();

    @Test
    void addierePrimitiv() {
        assertEquals(10, addierer.addierePrimitiv(5, 5), "Ergebnis stimmt nicht");
    }

    @Test
    void addiereGekapselt() {
        assertEquals(10, addierer.addiereGekapselt(5, 5), "Ergebnis ist falsch");
    }

    @Test
    void addiereRekursiv() {
        assertEquals(10, addierer.addiereRekursiv(5, 5), "Falsches Ergebnis");
    }

}