package UI;

import logic.Find;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    private String command = "";
    private List<String> fileNames = new ArrayList<>();

    public void launch() {
        if (command.isEmpty()) this.command = new Scanner(System.in).nextLine();
        Parser parser = new Parser(command);
        this.fileNames = new Find(
                parser.getFileName(),
                parser.isSearchInSubdirectories(),
                parser.getDirectorySearch())
                .getFoundFiles();
        if (!fileNames.isEmpty()) {
            System.out.println("All found files:");
            fileNames.forEach(System.out::println);
        } else {
            System.out.println("Files not found");
        }
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
