package karat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

///给一个word list 和最大的长度，要求把这些word用 - 串联起来，但不能超过最大的长度。
//# We are building a word processor and we would like to implement a "word-wrap" functionality.
//        #Given a maximum number of characters in a line followed by a list of words, return a collection of strings where each string element represents
//        a line that contains as many words as possible, with the words in each line being concatenated with a single '-' (representing a space,
//        but easier to see for testing). The length of each string must not exceed the maximum character length per line.
//        # Your function should take in the maximum characters per line and return a data structure representing all lines in the indicated max length.
//        # Note: built-in functions like Python textwrap module should not be used as solutions to this problem.
//        # Examples:
//        # words1 = [ "The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame" ]
//        # wrapLines(words1, 13) ... "wrap words1 to line length 13" =>
//        [ "The-day-began", "as-still-as", "the-night", "abruptly", "lighted-with", "brilliant", "flame" ]
//
//        # wrapLines(words1, 20) ... "wrap words1 to line length 20" =>
//        [ "The-day-began-as", "still-as-the-night", "abruptly-lighted", "with-brilliant-flame" ]
//
//        # words2 = [ "Hello" ]
//        # wrapLines(words2, 5) ..‍‌‌‌‍‌‌‍‍‍‌‌‌‍‍‍‍‍‌. "wrap words2 to line length 5" =>
//        [ "Hello" ]
//
//        # words3 = [ "Hello", "world" ]
//        # wrapLines(words3, 5) ... "wrap words3 to line length 5" =>
//        [ "Hello", "world" ]
//        # n = number of words / total characters
public class Solution7WordConcat {
     // words1 = [ "The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame" ]
     // wrapLines(words1, 13) ... "wrap words1 to line length 13" =>
     // [ "The-day-began", "as-still-as", "the-night", "abruptly", "lighted-with", "brilliant", "flame" ]

    public static List<String> wordWrap(String[] words, int maxLen) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            String curString = res.get(res.size() - 1);
            if (curString.length() + words[i].length() + 1 <= maxLen) {
                curString += "-" + words[i];
                res.set(res.size() - 1, curString);
            } else {
                res.add(words[i]);
            }
        }
        return res;
    }

//    public static List<String> wrap(String[] words,int maxLen){
//        List<String> res=new ArrayList<>();
//        res.add(words[0]);
//        for(int i = 1; i < words.length; i++) {
//            String lastWord = res.get(res.size()-1);
//            if (lastWord.length() + words[i].length() + 1 <= maxLen) {
//                lastWord += "-" + words[i];
//                res.set( res.size()-1, lastWord);
//            }else {
//                res.add(words[i]);
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] words1 = { "The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame" };
        int maxLen1=13;
        System.out.println(wordWrap(words1,maxLen1));
        String[] words2= {"Hello","world"};
        int maxLen2=5;
        System.out.println(wordWrap(words2,maxLen2));
    }
}
