import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class TableEntry implements Comparable<TableEntry> {
    String word;
    String wordsbefore = "";
    String wordsafter = "";
    int index;

    public TableEntry(String word, int index) {
        this.word = word;
        this.index = index;
    }

    @Override
    public int compareTo(TableEntry other) {
        if (this.word.compareTo(other.word) == 0) {
            return this.index - other.index;
        }
        return this.word.toLowerCase().compareTo(other.word.toLowerCase());
    }
}

class Result {

    /*
     * Complete the 'findARow' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. STRING original
     * 2. STRING unused
     * 3. STRING rows
     */
    /*
     * //TODO:cycle each word
     * 
     */
    // check if the string is a punctuation
    public static boolean hasPunctuation(String s) {
        return s.trim().matches(".*[.,?!;:]");
    }

    public static String RemovePunctuation(String s) {
        while (hasPunctuation(s)) {
            s = s.substring(0, s.length() - 1);
        }
        return s.trim();
    }

    public static boolean isUnimportant(String s) {
        return Arrays.asList(unimportants).contains(RemovePunctuation(s).toLowerCase());
    }

    public static String threeWordsBefore(int index) {
        String words = "";
        String word = "";
        int i = index;
        while (i > 0 && i > index - 3) {
            i--;
            word = originalArray[i];
            if (hasPunctuation(word)) {
                break;
            }
            word = RemovePunctuation(word);
            // check if word is unimportant
            if (isUnimportant(word)) {
                break;
            }
            words = word + " " + words;
        }

        return words.trim();
    }

    public static String threeWordsAfter(int index) {
        String words = "";
        String word = "";
        int i = index;
        boolean punct = false;
        while (i < originalArray.length - 1 && i < index + 3) {
            i++;
            word = originalArray[i];
            if (hasPunctuation(word)) {
                punct = true;
            }
            word = RemovePunctuation(word);
            // check if word is unimportant
            if (isUnimportant(word)) {
                break;
            }
            words = words + " " + word;
            if (punct) {
                break;
            }
        }

        return words.trim();
    }

    static String[] unimportants;
    static int[] rows_range;
    static String[] originalArray;
    static TableEntry[] originalTable;

    public static String findARow(String original, String unused, String rows) {
        int longest_before = 0;
        int longest_after = 0;
        int longest_word = 0;
        String curr_word;
        String curr_before;
        String curr_after;
        unimportants = unused.split(" ");
        rows_range = Arrays.stream(rows.split(" ")).mapToInt(Integer::parseInt).toArray();
        originalArray = original.split(" ");
        originalTable = new TableEntry[originalArray.length];
        for (int i = 0; i < originalArray.length; i++) {
            if (isUnimportant(originalArray[i])) {
                continue;
            }
            if (hasPunctuation(originalArray[i])) {
                curr_after = "";
            } else
                curr_after = threeWordsAfter(i);
            curr_word = RemovePunctuation(originalArray[i]);
            curr_before = threeWordsBefore(i);
            System.out.print(longest_after + " " + longest_before + " " + longest_word + " ");
            longest_after = Math.max(longest_after, curr_after.length());
            longest_before = Math.max(longest_before, curr_before.length());
            longest_word = Math.max(longest_word, curr_word.length());
            System.out.println(longest_after + " " + longest_before + " " + longest_word + " ");
            System.out.println(curr_before + " | " + curr_word + " | " + curr_after);
            originalTable[i] = new TableEntry(curr_word, i);
            originalTable[i].wordsbefore = curr_before;
            originalTable[i].wordsafter = curr_after;
        }
        originalTable = Arrays.stream(originalTable).filter(Objects::nonNull).toArray(TableEntry[]::new);

        // sort the table while keeping the same words in relative index order
        for (TableEntry entry : originalTable) {
            System.out.println(entry.wordsbefore + " | " + entry.word + " | " +
                    entry.wordsafter);
        }
        Arrays.sort(originalTable);
        // print table
        // for (TableEntry entry : originalTable) {
        //     System.out.println(entry.wordsbefore + " | " + entry.word + " | " +
        //             entry.wordsafter);
        // }
        // find the row with shortest word in the range
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = rows_range[0] - 1; i <= rows_range[1] - 1; i++) {
            // System.out.println(i);
            if ((originalTable[i].word.length() + originalTable[i].wordsafter.length()
                    + originalTable[i].wordsbefore.length()) > max) {
                max = originalTable[i].word.length() + originalTable[i].wordsafter.length()
                        + originalTable[i].wordsbefore.length();
                maxIndex = i;
                // System.out.println(i);
            }
        }
        String result = "";
        for (int i = 0; i < longest_before - originalTable[maxIndex].wordsbefore.length(); i++) {
            result += "-";
        }
        result = result + originalTable[maxIndex].wordsbefore + " <" + originalTable[maxIndex].word;
        for (int i = 0; i < longest_word - originalTable[maxIndex].word.length(); i++) {
            result += "-";
        }
        result = result + "> " + originalTable[maxIndex].wordsafter;
        for (int i = 0; i < longest_after - originalTable[maxIndex].wordsafter.length(); i++) {
            result += "-";
        }
        return result;
    }

}

public class KWIC {
    public static void main(String[] args) throws IOException {

        System.out.println(Result.findARow(
                "KWIC is an acronym for Key Word In Context, the most common format for concordance lines which is used for indexing in context.",
                "for in the", "7 15"));

    }
}


