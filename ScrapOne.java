import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ScrapOne {

    
    
    protected static void archiveEncryptedFile(String targetFile)
            throws FileNotFoundException, IOException, InterruptedException {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(
                targetFile.replace(".txt", "copy.txt")));
                FileInputStream fileInputStream = new FileInputStream(targetFile);) {

            ZipEntry zipEntry = new ZipEntry(targetFile);
            zipOutputStream.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
            }
            zipOutputStream.closeEntry();

        }
    }
    private static void removeContentsKeepHeader(String filePath) throws IOException {
        
        File file = new File(filePath);
        File tempFile = null;
        String line = null;
        String newName = file.getName();
        
        BufferedReader buffer;
        BufferedWriter bufferedWriter = null;
        
            if(file.exists()) {
                buffer = new BufferedReader(new FileReader(file));
                
                line = buffer.readLine();
                
                if(!line.isEmpty() && !line.isBlank()) {
                    try {
                       
                        buffer.close();
                        Files.delete(file.toPath());
                      System.out.println("Deleted contents of file: " + file.getName() + " at " + file.getAbsolutePath());
                  //      log.debug("Deleted contents of file: " + file.getName() + " at " + file.getAbsolutePath());

                        tempFile = new File(newName);
                        bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
                        bufferedWriter.write(line);  
                        
                  //      log.debug("Writing header to file: " + tempFile.getAbsolutePath());
                        System.out.println("Writing header to file: " + tempFile.getAbsolutePath());
                    }catch(IOException ioe){
     
                       // log.error(ioe.getMessage());
                        
                        }finally {
                            bufferedWriter.close();                           
                    }
                }
            } 
        }
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

       //     archiveEncryptedFile("test.txt");
            removeFileContents("3.txt");
    }
}




