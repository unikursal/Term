package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 29.09.2016.
 */
public class ReadFile {
    private final int  maxReadLine = 50;
    private static Pattern patern = Pattern.compile("^.*" + Pattern.quote(".") + "\\s*$");


    private String path = "D:\\onpu\\test11.txt";

    public List<String> getListWithText(){
        StringBuffer bufStr = new StringBuffer();

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        }catch (FileNotFoundException e){
            ShowText.show(e.getMessage());
            return null;
        }

        List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        String str = null;

        for(int i = 0; i < maxReadLine; i++) {
            try {
                str = bufferedReader.readLine();

                if(str == null) {
                    if(bufStr.length() != 0)
                        list.add(bufStr.toString());
                    break;
                }

                if(str.length() == 0)
                    continue;

                Matcher matcher = patern.matcher(str);

                // Перевірка чи останій символ є крапкою
                if(matcher.matches()){
                    if(bufStr.length() != 0) {
                        str = bufStr.append(" " + str).toString();
                        bufStr.delete(0, bufStr.length());
                    }
                }else {
                    if(bufStr.length() != 0)
                        bufStr.append(" ");
                    bufStr.append(str);
                    continue;
                }

                list.add(str);
            } catch (IOException e) {
                ShowText.show(e.getMessage());
                return null;
            }
        }

        return list;
    }

    public static String getCharPunctuat(){
        BufferedReader bufferedReader;

        bufferedReader = new BufferedReader(new InputStreamReader(MainСut.class.getResourceAsStream("/CharPunctuation")));

        String str = null;
         try {
             str = bufferedReader.readLine();
         } catch (IOException e) {
             ShowText.show(e.getMessage());
             return null;
         }
        return str;
    }
}
