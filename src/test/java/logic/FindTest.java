package logic;


import UI.UI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindTest {

    private String projectDir = new File("").getAbsolutePath();
    private List<String> filesPaths = Arrays.asList(
            projectDir + "\\fileName.txt",
            projectDir + "\\src\\fileName.txt",
            projectDir + "\\src\\main\\java\\fileName.txt",
            projectDir + "\\src\\main\\otherfile.txt");

    @Test
    void getFoundFiles() {
        UI ui = new UI("find", "-r", "fileName.txt");
        assertEquals(filesPaths.subList(0, 3), ui.getFileNames());
        UI ui2 = new UI("find", "-r", "-d", projectDir + "\\src\\", "fileName.txt");
        assertEquals(filesPaths.subList(1, 3), ui2.getFileNames());
        UI ui3 = new UI("find", "fileName.txt");
        assertEquals(filesPaths.subList(0, 1), ui3.getFileNames());
        UI ui4 = new UI("find", "notExistFile.txt");
        assertEquals(Collections.emptyList(), ui4.getFileNames());
    }

    @Test
    void testErrors() {
        assertThrows(IllegalArgumentException.class, () -> {
            UI ui = new UI("find", "-a", "FileName.txt");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UI ui = new UI("find", "-r", "-d", "trololo\\", "FileName.txt");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UI ui = new UI("find", "-d", "fileName.txt", "FileName.txt");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            UI ui = new UI("find", "-r", "");
        });
    }
}
