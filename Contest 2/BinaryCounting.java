import java.io.*;

class Result {

    /*
     * Complete the 'findLastOctal' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    // convert string into ascii then binary
    public static String toBinary(String s) {
        String binary = "";
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
            String temp = Integer.toBinaryString(ascii);
            while (temp.length() < 8) {
                temp = "0" + temp;
            }
            binary += temp;
        }
        return binary;

    }

    // subtract from binary
    public static String subtractBinary(String binary) {
        if (!binary.contains("0")) {
            return "-1";
        }
        int token = 0;
        String tkn_string = Integer.toBinaryString(token);
        while (binary.contains(tkn_string)) {
            int firstIndex = binary.indexOf(tkn_string);
            int lastIndex = binary.lastIndexOf(tkn_string);
            if (firstIndex == lastIndex && firstIndex == -1) {
                return binary;
            }
            if (firstIndex == lastIndex) {
                binary = binary.replace(tkn_string, "");
            } else {
                binary = binary.replaceFirst(tkn_string, "");
                // replace the last occurence of the token
                binary = new StringBuffer(binary).reverse().toString();
                binary = binary.replaceFirst(new StringBuffer(tkn_string).reverse().toString(), "");
                binary = new StringBuffer(binary).reverse().toString();
            }
            token++;
            tkn_string = Integer.toBinaryString(token);
        }
        return binary;
    }

    public static int findLastOctal(String s) {
        String binary = toBinary(s);
        String binary_remain = subtractBinary(binary);
        if (binary_remain == "-1") {
            return -1;
        }
        String octal = toOctal(binary_remain);
        int octal_remain = subtractOctal(octal);
        if (octal_remain == -1) {
            return -1;
        }
        return octal_remain;
    }

    private static String toOctal(String bin) {
        StringBuilder octal = new StringBuilder();
        while (bin.length() >= 3) {
            String oct = bin.substring(bin.length() - 3);
            bin = bin.substring(0, bin.length() - 3);
            octal.append(Integer.parseInt(oct, 2));
        }
        if (bin.length() > 0) octal.append(Integer.parseInt(bin, 2));
        return octal.reverse().toString();
    }

    private static int subtractOctal(String octal) {
        if (!octal.contains("0")) {
            return -1;
        }
        int token = 0;
        String tkn_string = Integer.toOctalString(token);
        while (octal.contains(tkn_string)) {
            int firstIndex = octal.indexOf(tkn_string);
            int lastIndex = octal.lastIndexOf(tkn_string);
            if (firstIndex == lastIndex && firstIndex == -1) {
                return token;
            }
            if (firstIndex == lastIndex) {
                octal = octal.replace(tkn_string, "");
            } else {
                octal = octal.replaceFirst(tkn_string, "");
                // replace the last occurence of the token
                octal = new StringBuffer(octal).reverse().toString();
                octal = octal.replaceFirst(new StringBuffer(tkn_string).reverse().toString(), "");
                octal = new StringBuffer(octal).reverse().toString();
            }
            token++;
            tkn_string = Integer.toOctalString(token);
        }
        token--;
        return token;
    }

}

public class BinaryCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bufferedWriter = new BufferedWriter(new
        // FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int result = Result.findLastOctal(s);
        System.out.println(result);
        // bufferedWriter.write(String.valueOf(result));
        // bufferedWriter.newLine();

        // bufferedReader.close();
        // bufferedWriter.close();
    }
}