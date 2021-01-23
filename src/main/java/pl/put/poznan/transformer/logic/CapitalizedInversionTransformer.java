package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.ArrayList;

/**
 * CapitalizedInversionTransformer is a subclass (concrete decorator) which takes a text string and returns an inverse string with preserved positions of upper case and lower case letters.
 * Supports UTF-8 encoding.
 * It extends TextDecorator.
 * @see TextDecorator
 */
public class CapitalizedInversionTransformer extends TextDecorator{

    /**
     * Constructor of CapitalizedInversionTransformer.
     * @param text  The text that will be inverted with preserved positions of upper case and lower case letters.
     */
    public CapitalizedInversionTransformer(TextTransformerInterface text){ super(text);}

    /**
     * Main method of the class CapitalizedInversionTransformer.
     * @return Inverted string with preserved positions of upper case and lower case letters.
     */
    @Override
    public String getText(){

        ArrayList<Integer> uppers = findUpperLetters();

        String src_lower = super.getText().toLowerCase();
        String src_upper = super.getText().toUpperCase();

        Integer n = src_lower.length();

        char[] chars = src_lower.toCharArray();
        char[] dst = src_lower.toCharArray();
        char[] keep_uppers = src_upper.toCharArray();
        for(int i = 0; i < n; i++){
            dst[i] = chars[n - 1 - i];

        }


        for(Integer i = 0; i < uppers.size(); i++){
            dst[uppers.get(i)] = keep_uppers[n - 1 - uppers.get(i)];
        }

        return new String(dst);
    }

    private ArrayList<Integer> findUpperLetters(){

        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.clear();

        String temp = super.getText();

        for(Integer i = 0; i < temp.length(); i++){

            if(Character.isUpperCase(temp.charAt(i))){
                indexes.add(i);
            }
        }

        return indexes;
    }
}
