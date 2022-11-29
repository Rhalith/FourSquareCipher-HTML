package pl.polsl.nuhyigitakman.model;


import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.counting;

/**
 *
 * @author Nuh Yigit Akman
 *
 * @version 1.1
 */
public class Coder {
    /**
     * Encoding by choosing encryption or decryption
     * @param plot Q1/Q2/Q3/Q4 arrays
     * @param text text that want to be encode
     * @param isEncryption choose of encryption or decryption
     * @return encoded string
     */

    @SafeVarargs
    public final String encodeText(String text, boolean isEncryption, List<Character>... plot){

        try {
            if(isEncryption){
                return encrypt(text, plot[0], plot[1], plot[2]);
            }
            else{
                return decrypt(text, plot[0], plot[1], plot[2]);
            }
        } catch (Exception e){
            return e.toString();
        }

    }
    /**
     * Decrypting the encrypted string by using key arrays
     * @param plot arrays for four-square cipher
     * @param encryptedText encrypted text for decryption
     * @return decrypted string
     */
    @SafeVarargs
    private String decrypt(String encryptedText, List<Character>... plot) {
        List<Character> pairs = new ArrayList<>();
        StringBuilder decryptedText = new StringBuilder();
        List<String> pairsOfEncryptedText = new ArrayList<>();

        //For set the size of list.
        for (int i = 0; i < encryptedText.length() / 2; i++) {
            pairsOfEncryptedText.add("");
        }
        for (int i = 0; i < 2; i++) {
            pairs.add('a');
        }


        int cursor = 0;
        for (int i = 0; i < pairsOfEncryptedText.stream()
                .collect(counting()); i++) {
            pairsOfEncryptedText.set(i, "" + encryptedText.charAt(cursor) + encryptedText.charAt(cursor + 1));
            cursor += 2;
        }

        //stream olarak dene
        pairsOfEncryptedText.stream().forEach(s -> {
            int column_a = 0;
            int row_a = 0;
            int column_b = 0;
            int row_b = 0;

            // get the pairs
            pairs.set(0, s.charAt(0));
            pairs.set(1, s.charAt(1));
            // find the pairs on key plot fixed alphabet
            for (int j = 0; j < plot[0].stream()
                    .collect(counting()); j++) {
                if (pairs.get(0) == plot[1].get(j)) {
                    row_a = (j / 5) * 5;
                    column_a = j % 5;
                }

                if (pairs.get(1) == plot[2].get(j)) {
                    row_b = (j / 5) * 5;
                    column_b = j % 5;
                }
            }

            decryptedText.append(plot[0].get(row_a + column_b)); // find in the key plot 1 Q1
            decryptedText.append(plot[0].get(row_b + column_a));  // find in the key plot 2 Q4
        });

        return decryptedText.toString();
    }
    /**
     * Encrypting the decrypted string by using key arrays
     * @param plot arrays for four-square cipher
     * @param plainText decrypted text for decryption
     * @return encrypted string
     */
    @SafeVarargs
    private String encrypt(String plainText, List<Character>... plot) {
        List<Character> pairs = new ArrayList<>();
        StringBuilder encryptedText = new StringBuilder();
        plainText = plainText.replaceAll("\\s", "");
        List<String> pairsOfEncryptText = new ArrayList<>();

        //For set the size of list.
        for (int i = 0; i < plainText.length() / 2; i++) {
            pairsOfEncryptText.add("");
        }
        for (int i = 0; i < 2; i++) {
            pairs.add('a');
        }

        int cursor = 0;
        for (int i = 0; i < pairsOfEncryptText.stream()
                .collect(counting()); i++) {
            pairsOfEncryptText.set(i, "" + plainText.charAt(cursor) + plainText.charAt(cursor + 1));
            cursor = cursor + 2;
        }

        pairsOfEncryptText.stream().forEach(s -> {
            int column_a = 0;
            int row_a = 0;
            int column_b = 0;
            int row_b = 0;

            // get the pairs
            pairs.set(0, s.charAt(0));
            pairs.set(1, s.charAt(1));

            // find the pairs on key fixed alphabet
            for (int j = 0; j < plot[0].stream()
                    .collect(counting()); j++) {
                if (pairs.get(0) == plot[0].get(j)) {
                    row_a = (j / 5) * 5;
                    column_a = j % 5;
                }

                if (pairs.get(1) == plot[0].get(j)) {
                    row_b = (j / 5) * 5;
                    column_b = j % 5;
                }
            }
            encryptedText.append(plot[1].get(row_a + column_b)); // find in the key plot 1 Q2
            encryptedText.append(plot[2].get(row_b + column_a));  // find in the key plot 2 Q3
        });

        return encryptedText.toString();
    }
}
