package practice.lab5;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;


public class FileTypeParser {
    public static final HashMap<String, String> fileTypes = new HashMap<>();
    public static final String[] hex = new String[4];

    static {
        fileTypes.put("89504e47", "png");
        fileTypes.put("504b0304", "zip or jar");
        fileTypes.put("cafebabe", "class");
    }

    public static String getFileType(String filePath){
        return fileTypes.get(getFileHeader(filePath));
    }

    private static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static String bytesToHexString(byte[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        StringBuilder HexString = new StringBuilder();
        String tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = Integer.toHexString(arr[i] & 0xFF).toLowerCase();
            if (tmp.length() < 2) {
                HexString.append(0);
            }
            HexString.append(tmp);
            hex[i] = tmp;
        }
        return HexString.toString();
    }

    public static void main(String[] args) {
//        String file = args[0];
        for(int i = 1;i<4;i++){
            String file = "src/main/java/practice/lab5/"+ i;
            String type = getFileType(file);
            System.out.println("Filename: "+ file);
            System.out.println("File Header(HEX): " + Arrays.toString(hex));
            System.out.println("File Type:: "+ type);
        }
    }

}
