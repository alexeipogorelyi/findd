package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Find {

    private List<String> foundFiles = new ArrayList<>();
    private String fileName;

    public Find(String fileName, boolean searchInSubdirectories, String directoryPath) {
        this.fileName = fileName;
        System.out.println(directoryPath);
        searchFiles(new File(directoryPath), searchInSubdirectories);
    }

    private void searchFiles(File folder, boolean searchInSubdirectories) {
        File[] folderFiles = folder.listFiles();
        if (folderFiles != null) {
            for (File entry : folderFiles) {
                if (entry.isDirectory() && searchInSubdirectories) {
                    searchFiles(entry, true);
                } else {
                    if (entry.getName().equals(this.fileName)) {
                        foundFiles.add(entry.getPath());
                    }
                }
            }
        }
    }

    public List<String> getFoundFiles() {
        return foundFiles;
    }
}
