import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TikTokTest {
    @Test
    public void testTikTok(){
        //arrange
        TikTok tikTok = new TikTok();

        //act
        String erg1 = tikTok.checkNumber(3); // act koennte man auch in assert implementieren
        String erg2 = tikTok.checkNumber(5);
        String erg3 = tikTok.checkNumber(15);

        //assert
        assertEquals("tik", erg1, "TikTok ist fehlerhaft");
        assertEquals("tok", erg2, "TikTok ist fehlerhaft");
        assertEquals("tiktok", erg3, "TikTok ist fehlerhaft");
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(TikTakKleinerNullException.class, () -> {
            throw new TikTakKleinerNullException();
        });
        assertEquals("Die Zahl ist kleiner als Null", exception.getMessage());
    }
}