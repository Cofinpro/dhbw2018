import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TikTokTest {
        @Test
        public void testTikTok(){
            //arrange
            TikTok tt1 = new TikTok(3);
            TikTok tt2 = new TikTok(5);
            TikTok tt3 = new TikTok(15);

            //act
            String erg1 = tt1.toString(); // act koennte man auch in assert implementieren
            String erg2 = tt2.toString();
            String erg3 = tt3.toString();

            //assert
            assertEquals("tik", erg1, "TikTok ist fehlerhaft");
            assertEquals("tok", erg2, "TikTok ist fehlerhaft");
            assertEquals("tiktok", erg3, "TikTok ist fehlerhaft");
        }
}