package pl.polsl.nuhyigitakman.history;

import java.util.Date;

/**
 * @author Nuh Yigit Akman
 * @version 1.2
 */
public class History {

    /**
     * user encrypt/decrypt choice
     */
    private final String userChoice;
    /**
     * key one for four-square cipher
     */
    private final String keyOne;
    /**
     * key two for four-square cipher
     */
    private final String keyTwo;
    /**
     * user input text for encryption/decryption
     */
    private final String userText;
    /**
     * text result of encryption/decryption
     */
    private final String output;

    /**
     * date of the encryption/decryption
     */
    private final Date date;


    /**
     * Constructor of History class for assign string variables
     *
     * @param userChoice  user encrypt/decrypt choice
     * @param keyOneInput user key one for four-square cipher
     * @param keyTwoInput user key two for four-square cipher
     * @param userText    user input text for encryption/decryption
     * @param output      text result of encryption/decryption
     */
    public History(String userChoice, String keyOneInput, String keyTwoInput, String userText, String output, Date date) {
        this.userChoice = userChoice;
        this.keyOne = keyOneInput;
        this.keyTwo = keyTwoInput;
        this.userText = userText;
        this.output = output;
        this.date = date;
    }

    /**
     * get userChoice
     *
     * @return userChoice
     */
    public String getUserChoice() {
        return userChoice;
    }

    /**
     * get keyOne
     *
     * @return keyOne
     */
    public String getKeyOne() {
        return keyOne;
    }

    /**
     * get keyTwo
     *
     * @return keyTwo
     */
    public String getKeyTwo() {
        return keyTwo;
    }

    /**
     * get userText
     *
     * @return userText
     */
    public String getUserText() {
        return userText;
    }

    /**
     * get output
     *
     * @return output
     */
    public String getOutput() {
        return output;
    }

    /**
     * get date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
