package Util;

import org.languagetool.tokenizers.uk.UkrainianWordTokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class MainСut {
    private static UkrainianWordTokenizer tokenizer = new UkrainianWordTokenizer();

    //Ділимо набори рядків по знакам пунктуації
    public static List<String> toCutCharPunctuat(List<String> stringList){
        if(stringList == null)
            return null;

        List<String> resultList = new ArrayList<String>();

        String dividers = ReadFile.getCharPunctuat();

        for(String str: stringList){
            String[] strings = str.split(dividers);
            resultList.addAll( Arrays.asList(strings));
        }
        return resultList;
    }

    //Ділимо набори рядків  на списки, які мають набори окремих слів
    public static List<List<String>> cutIntoWords(List<String> list){
        List<List<String>> result = new ArrayList<List<String>>();

        for(String str: list) {
            List<String> strings = tokenizer.tokenize(str);

            if(strings.isEmpty())
                continue;

            int size = strings.size();
            for(int i = 0; i < size; i++)
                if(!isWord(strings.get(i))) {
                    strings.remove(i);

                    i--;
                    size--;
                }

            result.add(strings);
        }

        return result;
    }

    private static boolean isWord(final String token) {
        for (int i = 0; i < token.length(); i++) {
            final char c = token.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
