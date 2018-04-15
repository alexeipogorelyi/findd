package logic;


import UI.UI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

class FindTest {

    private String projectDir = new File("").getAbsolutePath();
    private List<String> filesPaths = Arrays.asList(
            projectDir + "\\fileName.txt",
            projectDir + "\\src\\fileName.txt",
            projectDir + "\\src\\main\\java\\fileName.txt",
            projectDir + "\\src\\main\\otherfile.txt");

    @Test
    void getFoundFiles() {
        UI ui = new UI();
        ui.setCommand("find -r fileName.txt");
        ui.launch();
        assertEquals(filesPaths.subList(0, 3), ui.getFileNames());
        ui.setCommand("find -r -d " + projectDir + "\\src\\ fileName.txt");
        ui.launch();
        assertEquals(filesPaths.subList(1, 3), ui.getFileNames());
        ui.setCommand("find fileName.txt");
        ui.launch();
        assertEquals(filesPaths.subList(0, 1), ui.getFileNames());
    }
}
