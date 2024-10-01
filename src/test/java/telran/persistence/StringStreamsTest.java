package telran.persistence;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    @Test
    @Disabled
    void printStreamTest() throws Exception{
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("HELLO");
        printStream.close();
    }
    @Test
    @Disabled
    void printWriterTest() throws Exception{
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("HELLO");
        printWriter.close();
    }
    @Test
    @Disabled
    void bufferedReaderTest() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("HELLO",reader.readLine());
        reader.close();
    }
    @Test
    void printingDirectoryTest(){
        printDirectoryContent("\\",3);
    }
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
    void printDirectoryContent(String path, int depth) {
        //TODO
        //dir1
          //dir11
            //file
            //dir111
          //dir12
        //Consider class Path
        //Consider class Files
        //Consider method walk https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/Files.html#walk(java.nio.file.Path,java.nio.file.FileVisitOption...)  
    }

}
