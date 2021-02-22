import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<ReadThread> threads = new ArrayList<ReadThread>();
        String s;
        while (true) {
            s = reader.readLine();
            if (s.equals("exit")) break;
            list.add(s);
        }
        for (int i = 0; i < list.size(); i++) {
            threads.add(new ReadThread(list.get(i)));
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {

        }
    }


    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) throws Exception {
            this.fileName = fileName;
        }

        public synchronized void run() {
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                int[] array = new int[256];
                while (inputStream.available() > 0) {
                    int data = inputStream.read();
                    array[data]++;
                }
                int max = array[0];

                for (int i = 0; i < array.length; i++) {
                    if (array[i] > max) max = array[i];
                }
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == max) resultMap.put(fileName, i);
                }
                inputStream.close();

            } catch (Exception e) {

            }
        }

    }
}
