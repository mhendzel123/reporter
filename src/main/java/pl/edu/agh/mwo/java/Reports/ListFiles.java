package pl.edu.agh.mwo.java.Reports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ListFiles {

public List<File> listXlsFiles(String path) throws IOException {
        File dir = new File(path);
        String[] extensions = new String[] { "xls" };
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);

        return files;
}

public String printEmployeeNames(File fileName) {
    String myStr;
    myStr=fileName.toString();    
    String[] tokens = myStr.split("\\\\");
    String[] fileNameTokens = tokens[tokens.length - 1].split("\\.");
    String fileNameStr = "";
    for(int i = 0; i < fileNameTokens.length - 1; i++) {
        fileNameStr += fileNameTokens[i] + ".";
    }
    fileNameStr = fileNameStr.substring(0, fileNameStr.length() - 1);
    return fileNameStr;
}



}
