package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.ArrayList;

public class CapitalizedInversionTransformer extends TextDecorator{

    public CapitalizedInversionTransformer(TextTransformerInterface text){ super(text);}

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
