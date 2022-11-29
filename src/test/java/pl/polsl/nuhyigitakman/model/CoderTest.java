package pl.polsl.nuhyigitakman.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.1
 */
@TestMethodOrder(OrderAnnotation.class)
public class CoderTest {

    /**
     * coder class for encoding/decoding
     */
    private Coder coder;
    /**
     * Array Q1 and Q4 for cipher
     */
    private List<Character> plot;
    /**
     * Array Q2 for cipher
     */
    private List<Character> plotTwo;
    /**
     * Array Q3 for cipher
     */
    private List<Character> plotThree;
    /**
     * preparation for test
     */
    @BeforeEach
    public void setUp() {
        coder = new Coder();
        plot = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','W','X','Y','Z');
        plotTwo = Arrays.asList('E','X','A','M','P','L','B','C','D','F','G','H','I','J','K','N','O','R','S','T','U','V','W','Y','Z');
        plotThree = Arrays.asList('K','E','Y','W','O','R','D','A','B','C','F','G','H','I','J','L','M','N','P','S','T','U','V','X','Z');
    }

    /**
     * Get a correct encrypted text
     * @param plainText text that wanted to be encrypt
     * @param expectedEncryptedText text that expected to be get from encryption
     */
    @Order(0)
    @ParameterizedTest
    @CsvSource(value = {"HELLOWORLD:FYHGHZHSJE"}, delimiter = ':')
    public void testValidEncryption(String plainText, String expectedEncryptedText){
        String encryptedText = coder.encodeText(plainText, true, plot, plotTwo, plotThree);
        assertEquals(expectedEncryptedText, encryptedText);
    }

    /**
     * Get a wrong encrypted text
     * @param plainText text that wanted to be encrypt
     * @param expectedEncryptedText text that expected to be not get from encryption
     */
    @Order(0)
    @ParameterizedTest
    @CsvSource(value = {"HELLOWORLD:FAILENCRYPT"}, delimiter = ':')
    public void testInvalidEncryption(String plainText, String expectedEncryptedText){
        String encryptedText = coder.encodeText(plainText, true, plot, plotTwo, plotThree);
        assertNotEquals(expectedEncryptedText, encryptedText);
    }

    /**
     * Get a correct decrypted text
     * @param plainText text that wanted to be decrypt
     * @param expectedDecryptedText text that expected to be get from decryption
     */
    @Order(2)
    @ParameterizedTest
    @CsvSource(value = {"FYHGHZHSJE:HELLOWORLD"}, delimiter = ':')
    public void testValidDecryption(String plainText, String expectedDecryptedText){
        String decryptedText = coder.encodeText(plainText, false, plot, plotTwo, plotThree);
        assertEquals(expectedDecryptedText, decryptedText);
    }
    /**
     * Get a wrong decrypted text
     * @param plainText text that wanted to be decrypt
     * @param expectedDecryptedText text that expected to be not get from decryption
     */
    @Order(3)
    @ParameterizedTest
    @CsvSource(value = {"FYHGHZHSJE:HELLOWORLD"}, delimiter = ':')
    public void testInvalidDecryption(String plainText, String expectedDecryptedText){
        String decryptedText = coder.encodeText(plainText, false, plot, plotTwo, plotThree);
        assertEquals(expectedDecryptedText, decryptedText);
    }
}