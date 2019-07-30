import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SentenceTest {

    @Test
    public void givenHellotodayisgood_WhenCheckingForValid_ThenReturnTrue(){
        String phrase = "Hellotodayisgood";

        boolean result = Sentence.validSentence(phrase,0);

        assertTrue(result == true);
    }

}
