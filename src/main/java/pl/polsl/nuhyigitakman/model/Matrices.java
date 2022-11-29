/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.nuhyigitakman.model;

import java.util.*;
import java.util.List;

/**
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.1
 */
public class Matrices {


    /**
     * Array Q1 and Q4 for cipher
     */
    List<Character> plot = new ArrayList<>();
    /**
     * Array Q2 for cipher
     */
    List<Character> plotTwo = new ArrayList<>();
    /**
     * Array Q3 for cipher
     */
    List<Character> plotThree = new ArrayList<>();
    /**
     * Removed key for cipher
     */
    char remove = 'Q';

    /**
     * Get Q1 and Q4 arrays
     * @return Q1/Q4 array
     */
    public List<Character> getPlot() {
        return plot;
    }

    /**
     * Get Q2 array
     * @return Q2 array
     */
    public List<Character> getPlotTwo() {
        return plotTwo;
    }

    /**
     * Get Q3 array
     * @return Q3 array
     */
    public List<Character> getPlotThree() {
        return plotThree;
    }

    /**
     * Get removed key
     * @return removed key
     */
    public char getRemove() {
        return remove;
    }

    /**
     * fill array with letter of alphabets.
     * @param plot array that will be filled
     * @param remove letter that will not be used.
     */
    public void plotAlphabets(List<Character> plot, char remove) {
        int cursor = 0;
        //I couldn't use plot.size() because plot has no size in first. Therefore, I used 25.
        for (int i = 0; i < 25; i++) {
            if ((char) ('A' + i) != remove)
            {
                plot.add(i, (char) ('A' + cursor));
            }
            else
            {
                cursor++;
                plot.add(i, (char) ('A' + cursor));
            }
            cursor++;
        }
    }

    /**
     * fill array with specific key
     * if key is not long enough rest of it will be filled with letters of alphabet
     * @param plot array that will be filled
     * @param key the key that will use for fill
     * @param remove letter that will not be used.
     */
    public void plotKey(List<Character> plot, String key, char remove) {
        int cursor = 0;
        key = removeDuplicates(key);
        List<Character> ckey = convertStringToCharList(key);

        for (int i = 0; i < ckey.size(); i++) {
            plot.add(i, ckey.get(i));
        }

        for (int i = ckey.size(); i < 25; i++) {

            if((char) ('A' + cursor) == remove) {
                cursor++;
            }

            int checks = 2;
            for (int j = 0; j < checks; j++) {
                for (Character character : ckey) {
                    if (character == (char) ('A' + cursor)) {
                        cursor++;
                        break;
                    }
                }
            }

            if((char) ('A' + cursor) == remove) { // recheck
                cursor++;
            }

            plot.add(i, (char) ('A' + cursor));
            cursor++;

        }
    }

    /**
     * fill array with randomized key
     * @param plot array that will be filled
     * @param remove letter that will not be used
     */
    public void randomizeKey(List<Character> plot, char remove){
        plotAlphabets(plot, remove);
        Random rand = new Random();

        for (int i = 0; i < 25; i++) {
            int randomIndexToSwap = rand.nextInt(plot.size());
            char temp = plot.get(randomIndexToSwap);
            plot.set(randomIndexToSwap, plot.get(i));
            plot.set(i, temp);
        }
    }

    /**
     * remove the duplicating letters from string if there is any
     * @param string the string to be cleared of duplicates
     * @return string without duplicates
     */
    private String removeDuplicates(String string) {
        string = string.toUpperCase();
        List<Character> characters = convertStringToCharList(string);
        StringBuilder filtered = new StringBuilder();

        for (int i = 0; i < characters.size(); i++) {

            boolean isReapeated = false;
            for (int j = 0; j < i; j++) {
                if(characters.get(i) == characters.get(j)) {
                    isReapeated = true;
                    break;
                }
            }

            if(!isReapeated) {
                filtered.append(characters.get(i));
            }
        }
        return filtered.toString();
    }

    /**
     * Converting string to char list
     * @param text string that wanted to be list
     * @return char list
     */
    private List<Character> convertStringToCharList(String text)
    {

        List<Character> chars = new ArrayList<>();

        for (char ch : text.toCharArray()) {
            chars.add(ch);
        }

        return chars;
    }

}
