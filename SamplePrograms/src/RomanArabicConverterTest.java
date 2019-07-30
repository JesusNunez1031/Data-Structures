import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RomanArabicConverterTest {
    @Test
    public void given2018Roman_WhenConvertingToArabic_ThenReturn2018() {

        String roman2018 = "MMXVIII";

        int result = RomanArabicConverter.romanToArabic(roman2018);

        assertTrue(result == 2018);
    }

    @Test
    public void given1999Arabic_WhenConvertingToRoman_ThenReturnMCMXCIX() {
        int arabic1999 = 1999;

        String result = RomanArabicConverter.arabicToRoman(arabic1999);

        assertTrue(result.equals("MCMXCIX"));
    }
}
