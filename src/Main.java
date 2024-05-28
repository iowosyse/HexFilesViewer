import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("javav.png")
        ) {
            int byteCount = 0;
            StringBuilder byteString = new StringBuilder();
            StringBuilder charString = new StringBuilder();
            while (true) {
                //reads every byte on the line, cannot be saved in byte, must be integer
                int b = fis.read();
                //fis.read(); returns -1 when reaching the end of the file
                if (b == -1) {
                    break;
                }
                byteCount ++;

                byteString.append("[");
                byteString.append(String.format("%02x", b));
                byteString.append("]");
                //printable + extended ASCII characters
                if (b >= 32 && b <= 254)
                    charString.append((char) b);
                else
                    charString.append(".");

                if (byteCount % 16 == 0) {
                    //if byteCount started on -1 it gives a single byte and a single character, so we subtract 16 in this line
                    System.out.printf("%08x  %s  %s%n", byteCount - 16, byteString, charString);
                    //restart the strings
                    byteString.setLength(0);
                    charString.setLength(0);
                }
            }

            //in case the last line read doesn't take every byte.
            if (byteCount % 16 != 0) {
                //adding the "-" in the formater makes it align to the left
                System.out.printf("%08x  %-64s  %-16s%n", byteCount - (byteCount % 16), byteString, charString);
            }

            //closing stream
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("double rip");
            }
        } catch (IOException e) {
            System.out.println("RIP");
            e.printStackTrace();
        }
    }
}
