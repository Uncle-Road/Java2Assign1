package practice.lab6;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class zipread {
    public static void main(String[] args) throws IOException, URISyntaxException {

        ZipFile zipFile = new ZipFile("D:\\code\\java\\22F-CS209A-Labs\\src\\main\\resources\\src.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        ArrayList<String> res = new ArrayList<>();
        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            if(entry.getName().contains("java")&&(entry.getName().contains("java/nio")||entry.getName().contains("java/io"))){
                res.add(entry.getName());
            }
        }
        System.out.println("zip of .java files in java.io/java.nio: "+res.size());
        res.forEach(s -> System.out.println(s));
    }
}
