package UI;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class Parser {

    Parser(String command) {
        String[] commandParts = command.split("\\s+");

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(commandParts);
        } catch (CmdLineException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Argument()
    private String find;

    @Option(name = "-r", usage = "enable/disable validation mode")
    private boolean searchInSubdirectories = false;

    @Option(name = "-d")
    private String directorySearch = "";

    @Argument(index = 1)
    private String fileName = "";

    public boolean isSearchInSubdirectories() {
        return searchInSubdirectories;
    }

    public String getDirectorySearch() {
        return directorySearch.isEmpty() ? new File("").getAbsolutePath() + "/" : directorySearch;
    }

    public String getFileName() {
        return fileName;
    }
}
