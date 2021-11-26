
package com.zhaojian.koom_tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class MyClass {

    public static void main(String[] args) throws IOException {
        // File file = new File("C:\\kika\\KOOM\\material\\mHeapGraph.instances.md");
//        File file = new File("C:\\kika\\KOOM\\material\\instance.superId1.superId4.md");
        File file = new File("C:\\kika\\KOOM\\material\\instance.instanceClassId.md");
        extracted(file);
    }

    private static void extracted(File file) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        HashSet<String> set = new HashSet<>();
        while ((str = br.readLine()) != null) {
            set.add(str.trim());
        }
        for (String s : set) {
            System.out.println(s);
        }

        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        for (String s : set) {
            bw.write(s);
            bw.write("\n");
        }
    }

}