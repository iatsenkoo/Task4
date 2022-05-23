package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        task1();
        task3();
        task12();
        task13();
        task6();

    }

    private static void task6() {
        //6. Задать два стека, поменять информацию местами.

        ArrayList<String> arrayList1 = new ArrayList<>(List.of("some", "array"));
        ArrayList<String> arrayList2 = new ArrayList<>(List.of("another", "array2"));

        changeInfo(arrayList1,arrayList2);

        System.out.println(arrayList1);
        System.out.println(arrayList2);

    }

    public static void changeInfo(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> temp = new ArrayList<>(list1);
        list1.removeAll(list1);
        list1.addAll(list2);
        list2.removeAll(list2);
        list2.addAll(temp);
    }

    private static void task13() {
        //13. Задан файл с текстом на английском языке. Выделить все различные слова. Слова,
        //отличающиеся только регистром букв, считать одинаковыми.
        Set<String> stringSet = new HashSet<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("fileEnglish.txt"))) {
            getStringSet(stringSet,bufferedReader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(stringSet);
    }

    public static void getStringSet(Set<String> stringSet, BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine().toLowerCase(Locale.ROOT);
        while (line != null){
            String[] arr = line.split("\\W+");
            stringSet.addAll(List.of(arr));
            line = bufferedReader.readLine();
        }

    }

    private static void task3() {
        //3. Создать список из элементов каталога и его подкаталогов.
        List<String> listFiles = new ArrayList<>();
        File file = new File("C:\\Users\\yatsenko\\IdeaProjects\\Task4\\src");
        getDirectories(file,listFiles);

        System.out.println(listFiles);

    }

    public static void getDirectories(File file, List<String> listFiles) {
        File[] files = file.listFiles();
        for (File f : files) {
            listFiles.add(f.getName());
            if(f.isDirectory()){
                getDirectories(f, listFiles);
            }
        }
    }

    private static void task12() {
        //12. Ввести строки из файла, записать в список ArrayList. Выполнить сортировку строк,
        //используяметод sort() из класса Collections.
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("file.txt"))){
            String line = bufferedReader.readLine();
            while (line != null){
                list.add(line);
                line = bufferedReader.readLine();
            }
            System.out.println(list);
            sortDefault(list);
            System.out.println(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sortDefault(List<String> list) {
        Collections.sort(list);
    }

    private static void task1() {
        //1. Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
        ArrayList<String> arrayList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of("file.txt"))) {
            String line = br.readLine();
            while (line != null){
                arrayList.add(line);
                line = br.readLine();
            }

            try(BufferedWriter bw = Files.newBufferedWriter(Path.of("outputFile.txt"))){
                for (int i = arrayList.size()-1; i >= 0; i--) {
                    bw.write(arrayList.get(i));
                    bw.newLine();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}