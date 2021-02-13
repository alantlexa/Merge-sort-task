package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            BufferedInputStream src1 = new BufferedInputStream(new FileInputStream
                    ("C:\\Users\\alant\\IdeaProjects\\MergeT\\src\\com\\company\\src1.txt"));
            BufferedInputStream src2 = new BufferedInputStream(new FileInputStream
                    ("C:\\Users\\alant\\IdeaProjects\\MergeT\\src\\com\\company\\src2.txt"));
            BufferedInputStream src3 = new BufferedInputStream(new FileInputStream
                    ("C:\\Users\\alant\\IdeaProjects\\MergeT\\src\\com\\company\\src3.txt"));

            Scanner scan1 = new Scanner(src1);
            Scanner scan2 = new Scanner(src2);
            Scanner scan3 = new Scanner(src3);
            // сохраняем значения всех переданных файлов в один общий массив
            ArrayList<Integer> combi = new ArrayList<>();

            while (scan1.hasNextInt()) {
                combi.add(scan1.nextInt());
            }
            while (scan2.hasNextInt()) {
                combi.add(scan2.nextInt());
            }
            while (scan3.hasNextInt()) {
                combi.add(scan3.nextInt());
            }
            scan1.close();
            scan2.close();
            scan3.close();

            sort(combi);

            FileWriter writer = new FileWriter
                    ("C:\\Users\\alant\\IdeaProjects\\MergeT\\src\\com\\company\\out.txt");

            for (Integer i : combi) {
                String s = i.toString();
                writer.write(s);
                writer.flush();
                writer.write(System.lineSeparator());
            }
            writer.close();

            try {
                String arg1 = args[0];
                String arg2 = args[1];
                String arg3 = args[2];
                String arg4 = args[3];
                if (arg1.equals("-i") && arg2.equals("-a") || arg2.equals("") && arg3.equals("out.txt")
                        && arg4.equals("src1.txt") || arg4.equals("src2.txt") || arg4.equals("src3.txt")) {
                    for (int i : combi) {
                        System.out.println(i);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Set the parameters.");
            }

        } catch (FileNotFoundException f) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> combi) {
    // метод разбивает массив на части и сравнивает по 2 элемента,
    // после чего возвращает отсортированный по возрастанию массив
        if (combi.size() == 1) {
            return combi;
        }
        else {
            int div = combi.size() / 2;
            ArrayList<Integer> left = new ArrayList<>(div);
            ArrayList<Integer> right = new ArrayList<>(combi.size() - div);

            for (int i = 0; i < div; i++) {
                left.add(combi.get(i));
            }

            for (int i = div; i < combi.size(); i++) {
                right.add(combi.get(i));
            }

            sort(left);
            sort(right);
            merge(left, right, combi);
        }
        return combi;
    }


    public static void merge (ArrayList<Integer>left, ArrayList<Integer>right, ArrayList<Integer>combi)
    {
        // метод самой сортировки, путем замещения
        int indL = 0;
        int indR = 0;

        for (int i = 0; i < combi.size(); i++) {

            if (indL == left.size()) {
                combi.set(i, right.get(indR));
                indR++;
            }
            else {
                if (indR == right.size()) {
                    combi.set(i, left.get(indL));
                    indL++;
                }
                else {
                    if (left.get(indL) <= right.get(indR)) {
                        combi.set(i, left.get(indL));
                        indL++;
                    }
                    else {
                        if (left.get(indL) >= right.get(indR)) {
                            combi.set(i, right.get(indR));
                            indR++;
                        }
                    }
                }
            }

        }
    }
}


