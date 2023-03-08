public class test {
    public static void main(String[] args) {
        // program that directily converts binary string to octal
        String bin = "10100100001010000010000000010110001000000111010001101001011011010110010100100000011100110110000101100110010100100000010010";
        StringBuilder octal = new StringBuilder();
        while (bin.length() >= 3) {
            String oct = bin.substring(bin.length() - 3);
            bin = bin.substring(0, bin.length() - 3);
            octal.append(Integer.parseInt(oct, 2));
        }
        octal.append(Integer.parseInt(bin, 2));
        System.out.println(octal.reverse().toString());

    }
}