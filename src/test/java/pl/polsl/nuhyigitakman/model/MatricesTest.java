package pl.polsl.nuhyigitakman.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.1
 */
public class MatricesTest {
    /**
     * Matrices class
     */
    private Matrices matrices;
    /**
     * Array with filled alphabet
     */

    private List<Character> alphabet;
    /**
     * Array that is not filled as an alphabet
     */
    private List<Character> notAlphabet;
    /**
     * Plot array for test methods.
     */
    private List<Character> plot;
    /**
     * Expected plot array after adding key to plot
     */
    private List<Character> keyPlot;
    /**
     * Not the expected array after adding key to plot
     */
    private List<Character> notKeyPlot;

    /**
     * preparation for test
     */
    @BeforeEach
    public void setUp(){
        matrices = new Matrices();
        alphabet = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','W','X','Y','Z');
        notAlphabet = Arrays.asList('N','O','T','A','L','P','H','A','B','E','T');
        keyPlot = Arrays.asList('R','A','N','D','O','M','B','C','E','F','G','H','I','J','K','L','O','P','S','T','U','V','W','X','Y');
        notKeyPlot = Arrays.asList('N','O','T','K','E','Y','P','L','O','T');
    }

    /**
     * testing if plot is filling with alphabet without remove char
     * @param remove letter that will not be used for fillment.
     */
    @ParameterizedTest
    @ValueSource(chars = {'Q'})
    public void testPlotAlphabets(char remove){
        try{
            plot = new ArrayList<>();
            matrices.plotAlphabets(plot, remove);
            assertEquals(alphabet, plot);
        }catch (Exception e){
            fail("Plot is not alphabet plot");
        }
        try {
            plot = new ArrayList<>();
            matrices.plotAlphabets(plot, remove);
            assertNotEquals(notAlphabet, plot);
        }catch (Exception e){
            fail("Plot is alphabet plot");
        }
    }

    /**
     * testing if plot is filling with given key
     * @param key given key
     */
    @ParameterizedTest
    @ValueSource(strings = {"RANDOM"})
    public void testPlotKey(String key){
        try{
            plot = new ArrayList<>();
            matrices.plotKey(plot, key, 'Q');
            assertEquals(keyPlot, plot);
        }catch (Exception e){
            fail("Plot is not filled properly");
        }
        try {
            plot = new ArrayList<>();
            matrices.plotKey(plot, key, 'Q');
            assertNotEquals(notKeyPlot, plot);
        }catch (Exception e){
            fail("Plot is filled properly");
        }
    }

    /**
     * Testing if randomized array is contains same elements with alphabet and the size of it is same.
     */
    @Test
    public void testRandomizeKey(){
        try{
            plot = new ArrayList<>();
            matrices.randomizeKey(plot, 'Q');
            for (Character character : plot) {
                assertFalse(!alphabet.contains(character));
            }
        }catch (Exception e){
            fail("It should not be alphabet");
        }
        try {
            plot = new ArrayList<>();
            matrices.randomizeKey(plot, 'Q');
            assertEquals(alphabet.size(),plot.size());
        }catch (Exception e){
            fail("The size should be same");
        }
    }
}