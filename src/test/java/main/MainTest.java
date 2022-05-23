package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testChangeInfo() {
        ArrayList<String> arrayList1 = new ArrayList<>(List.of("some", "array"));
        ArrayList<String> arrayList2 = new ArrayList<>(List.of("another", "array2"));

        ArrayList<String> expectList1 = new ArrayList<>(List.of("another", "array2"));
        ArrayList<String> expectList2 = new ArrayList<>(List.of("some", "array"));

        main.changeInfo(arrayList1, arrayList2);

        assertEquals(arrayList1, expectList1);
        assertEquals(arrayList2, expectList2);
    }

    @Test
    void testGetStringSet() {
        Set<String> expected = new HashSet<>(Arrays.asList("other", "instance", "be", "classes", "for", "used", "that", "can",
                "and", "by", "only", "should", "create", "class", "you", "which", "all", "a", "or",
                "created", "want", "suppose", "single", "to", "object"));
        Set<String> actual = new HashSet<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("fileEnglish.txt"))) {
            main.getStringSet(actual, bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, actual);


    }


    @Test
    void testGetDirectories() {
        List<String> actual = new ArrayList<>();
        List<String> expected = new ArrayList<>(List.of("main","java","main","Main.java","resources","test",
                                                         "java","main","MainTest.java"));
        File file = new File("C:\\Users\\yatsenko\\IdeaProjects\\Task4\\src");

        main.getDirectories(file,actual);

        assertEquals(expected,actual);
    }

    @Test
    void testSortDefault() {
        List<String> actual = new ArrayList<>();
        List<String> expected = new ArrayList(List.of("message", "some", "text"));

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("file.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                actual.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        main.sortDefault(actual);

        assertEquals(expected, actual);

    }
}