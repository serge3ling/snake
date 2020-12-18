/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tret
 */
public class RecordSave {
    public static final String FILE = "snake-records.txt";
    public static int TOP_COUNT = 50;
    
    private Set<Record> records = new TreeSet<>();
    private Set<String> users = new TreeSet<>();
    
    public Record[] makeTopRecords() {
        records.clear();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE),
                        "UTF8")
            );

            String s = reader.readLine();
            while (s != null) {
                int splitIndex = s.indexOf(" ");
                if (splitIndex >= 0) {
                    try {
                        int points = Integer.parseInt(s.substring(0, splitIndex));
                        records.add(new Record(s.substring(splitIndex + 1), points));
                    } catch (NumberFormatException numberException) {
                    }
                }
                s = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
        }
        
        return sortAndTrimTopRecords();
    }
    
    
    
    private Record[] sortAndTrimTopRecords() {
        Record[] allRecordsArray = records.toArray(new Record[records.size()]);
        Arrays.sort(allRecordsArray);
        
        users.clear();
        for (int i = 0; i < allRecordsArray.length; i++) {
            if (!allRecordsArray[i].user.endsWith("anonymous")) {
                users.add(allRecordsArray[i].user);
            }
        }
        
        Record[] recordsArray = allRecordsArray;
        if (allRecordsArray.length > TOP_COUNT) {
            recordsArray = new Record[TOP_COUNT];
            records.clear();
            for (int i = 0; i < TOP_COUNT; i++) {
                records.add(allRecordsArray[i]);
                recordsArray[i] = allRecordsArray[i];
            }
            saveRecords();
        }
        return recordsArray;
    }
    
    private void saveRecords() {
        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter(new File(FILE))
            );
            for (Record record : records) {
                writer.write("" + record.points + " " + record.user + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }
    
    public void save(Record record) {
        records.add(record);
        saveRecords();
    }
    
    public String[] readUsers() {
        makeTopRecords();
        return users.toArray(new String[users.size()]);
    }
}
