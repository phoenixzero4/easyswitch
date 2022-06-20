import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileOps {
    
    
    protected static void removeContents(String fileName) throws IOException{
        
        File file = new File(fileName);
        File tempFile = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        String headerLine = "";
        try {
            if(file.exists()) {
                System.err.println("File: " + file.getName() + " exists at " + file.getAbsolutePath());
                
                reader = new BufferedReader(new FileReader(file));
                headerLine = reader.readLine();
                
                
                tempFile = new File(file.getName() + ".tmp");
                System.err.println("tempFile: " + tempFile.getName());            
                
                
                
                if(!headerLine.isBlank()) {
                    writer = new BufferedWriter(new FileWriter(tempFile));
                    writer.write(headerLine);
                }
                reader.close();
                writer.close();
                Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE);
            }
        }catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }finally {
            reader.close();
            writer.close();
        }
    }
}


