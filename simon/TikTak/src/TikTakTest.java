import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class TikTakTest {

    @Test
    public void testTik() {
        //Prepare
        TikTak sut = new TikTak(9);

        //Act
        String result = sut.toString();

        //Test
        Assert.assertThat(result, is("tik"));
    }

    @Test
    public void testTak() {
        //Prepare
        TikTak sut = new TikTak(35);

        //Act
        String result = sut.toString();

        //Test
        Assert.assertThat(result, is("tak"));
    }

    @Test
    public void testTikTak() {
        //Prepare
        TikTak sut = new TikTak(45);

        //Act
        String result = sut.toString();

        //Test
        Assert.assertThat(result, is("tiktak"));
    }

    @Test(expected = TikTakKleinerNullException.class)
    public void testNegativeInput() {
        //Prepare
        TikTak sut = new TikTak(-1);
    }
}