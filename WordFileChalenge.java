/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 *
 * @author heman
 */
public class WordFileChalenge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String webFile = "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>";
        webFile += "<meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Result Word Counter</title>";
        webFile += "<style>td {border: 1px solid black;padding: 8px;}th {padding: 8px;background: #ccc;}</style></head><body>";
        webFile += "<table><tr><th>WordFound</th><th>Occurance</th></tr>";
        Scanner s = new Scanner(new File("test.txt")).useDelimiter("[^a-zA-Z]+");
        Map<String, Integer> m = new HashMap<String, Integer>();
        while (s.hasNext()) {
            String letter = s.next();
            if (m.containsKey(letter)) {
                m.put(letter, m.get(letter) + 1);
            } else {
                m.put(letter, 1);
            }
        }

        List<Map.Entry<String, Integer>> occurance = new ArrayList<Entry<String, Integer>>(m.entrySet());
        int totalSize = m.size();
        Collections.sort(occurance, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> x, Map.Entry<String, Integer> y) {
                return x.getValue().compareTo(y.getValue());
            }
        });
        System.out.println(occurance);
        for (int i = 0; i < totalSize; i++) {
            String temp = "<tr>";
            temp += "<td>" + occurance.get(occurance.size() - i - 1).getKey() + "</td>";
            temp += "<td>" + occurance.get(occurance.size() - i - 1).getValue() + "</td>";
            temp += "</tr>";
            webFile += temp;
        }
        webFile += "</table></body></html>";
        
        try {
            FileWriter myWriter = new FileWriter("html_report.html");
            myWriter.write(webFile);
            myWriter.close();
            System.out.println("HTML Ready");
        } catch (IOException e) {
            System.out.println("Error while computing result HTML");
            e.printStackTrace();
        }
    }

}
