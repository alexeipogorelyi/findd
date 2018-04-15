package UI;

import logic.Find;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UI {

    public UI(String... args) {
        launch(args);
    }

    @Argument()
    private String find;

    @Option(name = "-r", usage = "enable/disable validation mode")
    private boolean searchInSubdirectories = false;

    @Option(name = "-d")
    private String directorySearch = "";

    @Argument(index = 1)
    private String fileName = "";

    private List<String> fileNames = new ArrayList<>();

    private void launch(String... commandParts) {

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(commandParts);
        } catch (CmdLineException ex) {
            throw new IllegalArgumentException("Invalid command!");
        }

        this.fileNames = new Find(
                fileName,
                searchInSubdirectories,
                directorySearch.isEmpty() ? new File("").getAbsolutePath() + "/" : directorySearch).getFoundFiles();
        if (!fileNames.isEmpty()) {
            System.out.println("All found files:");
            fileNames.forEach(System.out::println);
        } else {
            System.out.println("Files not found!");
        }
    }



    public List<String> getFileNames() {
        return fileNames;
    }
}
