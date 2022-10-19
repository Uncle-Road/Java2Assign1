package practice.lab6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class jarread {
    public static void main(String[] args) throws IOException {
        JarFile JarFile = new JarFile("D:\\code\\java\\22F-CS209A-Labs\\src\\main\\resources\\rt.jar");
        Enumeration<? extends JarEntry> entries = JarFile.entries();
        ArrayList<String> res = new ArrayList<>();
        while(entries.hasMoreElements()){
            JarEntry entry = entries.nextElement();
            if(entry.getName().contains("java")&&(entry.getName().contains("java/nio")||entry.getName().contains("java/io"))){
                res.add(entry.getName());
            }
        }
        System.out.println("jar of .java files in java.io/java.nio: "+res.size());
        res.forEach(s -> System.out.println(s));
    }
}
