package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.Arrays;
import java.util.Hashtable;

public class NumberToTextTransformer extends TextDecorator {
    public NumberToTextTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return String.join(" ",
                Arrays.stream(super.getText().split(" ")).map(this::transformWord).toArray(String[]::new)
        );
    }

    private String transformWord(String word) {
        try {
            return translate(Double.parseDouble(word.replace(",", ".")));
        } catch (NumberFormatException nfe) {
            return word;
        }
    }
    private String translate(double liczba_x) {
        String[] parts = String.valueOf(liczba_x).split("\\.");
        String[] translations = {
                "dziesiątych",
                "setnych",
                "tysięcznych",
        };

        String[] translations_number_one = {
                "dziesiąta",
                "setna",
                "tysięczna",
        };

        String[] translations_number_two_four = {
                "dziesiąte",
                "setne",
                "tysięczne",
        };

        String result = translate_((int)liczba_x);
        int i = Integer.parseInt(parts[1]);
        if(i != 0){
            result += " i ";

            if((int)liczba_x == 0){
                result = "";
            }

            if( i == 1){
                result += "jedna " + translations_number_one[parts[1].length()-1];
            }else if (i == 2){
                result += "dwie " + translations_number_two_four[parts[1].length()-1];
            }else if (i == 3 || i == 4){
                result += translate_(i) + " " + translations_number_two_four[parts[1].length()-1];
            }else{

                result += translate_(i);
                result += " " + translations[parts[1].length()-1];
            }
        }


        return result;
    }


    private String translate_(long liczba) {
        String[] jednosci = { "", "jeden ", "dwa ", "trzy ", "cztery ",
                "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ", };

        String[] nastki = { "", "jedenaście ", "dwanaście ", "trzynaście ",
                "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ",
                "osiemnaście ", "dziewiętnaście ", };

        String[] dziesiatki = { "", "dziesięć ", "dwadzieścia ",
                "trzydzieści ", "czterdzieści ", "pięćdziesiąt ",
                "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ",
                "dziewięćdziesiąt ", };

        String[] setki = { "", "sto ", "dwieście ", "trzysta ", "czterysta ",
                "pięćset ", "sześćset ", "siedemset ", "osiemset ",
                "dziewięćset ", };

        String[][] grupy = { { "", "", "" },
                { "tysiąc ", "tysiące ", "tysięcy " },
                { "milion ", "miliony ", "milionów " },
                { "miliard ", "miliardy ", "miliardów " },
                { "bilion ", "biliony ", "bilionów " },
                { "biliard ", "biliardy ", "biliardów " },
                { "trylion ", "tryliony ", "trylionów " }, };

// INICJACJA ZMIENNYCH
        long j = 0/* jednosci */, n = 0/* nastki */, d = 0/* dziesiatki */, s = 0/* setki */, g = 0/* grupy */, k = 0/* końcówwki */;
        String slownie = "";
        String znak = "";

// OPERACJA DOTYCZąCA ZNAKU

        if (liczba < 0) {
            znak = "minus ";
            liczba = -liczba; // bezwględna wartość ponieważ, jeśli będziemy
// operować na liczbie z minusem tablica będzie
// przyjmowała wartości ujemne i zwróci nam błąd
        }
        if (liczba == 0) {
            znak = "zero";
        }

// PĘTLA GŁÓWNA
        while (liczba != 0) {
            s = liczba % 1000 / 100;
            d = liczba % 100 / 10;
            j = liczba % 10;

            if (d == 1 & j > 0) // if zajmujący się nastkami
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }

// <---- KOŃCÓWKI

            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0) // jeśli nie będzie dziesiątek ani setek, wtedy otrzymamy samą grupę
                { // przykładowo 1000 - wyświetli nam się "tysiąc", jeśli
// zakomentujemy tego if'a to otrzymamy "jeden tysiąc"
                    j = 0;
                    slownie = grupy[(int) g][(int) k] + slownie;
                }
            } else if (j == 2) {
                k = 1;
            } else if (j == 3) {
                k = 1;
            } else if (j == 4) {
                k = 1;
            } else {
                k = 2;
            }

// KONIEC KOŃCÓWEK -->

            if (s+d+n+j > 0) {
                slownie = setki[(int) s] + dziesiatki[(int) d] + nastki[(int) n]
                        + jednosci[(int) j] + grupy[(int) g][(int) k] + slownie;
            }

// POZBYWAMY SIĘ TYCH LICZBY KTÓRE JUŻ PRZEROBILIŚMY czyli
// przykładowo z 132132 zostaje nam 132 do obróbki
            liczba = liczba / 1000;
// ORAZ ZWIĘKSZAMY G KTÓRE ODPOWIEDZIALNE JEST ZA NUMER POLA W
// TABLICY WIELOWYMIAROWEJ
            g = g + 1;
        }
// KONIEC PĘTLI GŁÓWNEJ

// DODANIE ZNAKU I ZWRÓCENIE METODY
        slownie = znak + slownie;
        return slownie.strip();
    }
}
