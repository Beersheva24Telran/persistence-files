package telran.persistence;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    static final int SPACES_PER_DEPTH_LEVEL=2;
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
    void printingDirectoryTest() throws IOException{
        printDirectoryContent(".",2);
    }
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
   private void printDirectoryContent(String dirPathStr, int depth) throws IOException {
		
		//using FIles.walkFileTree
		Path pathParam = Path.of(dirPathStr);
		if (!Files.isDirectory(pathParam)) {
			throw new IllegalArgumentException("not directory");
		}
		Path path = pathParam.toAbsolutePath().normalize();
		int count = path.getNameCount();
		System.out.println("directory: " + path);
		Files.walkFileTree(path, new HashSet<>(), depth <= 0 ? Integer.MAX_VALUE : depth, new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				if (!Files.isSameFile(path, dir)) {
					printPathWithOffset(dir);
				}
				return FileVisitResult.CONTINUE;
			}

			private void printPathWithOffset(Path path) {
				System.out.printf("%s%s - %s\n", " ".repeat(getSpacesNumber(path)),
						path.getFileName(), Files.isDirectory(path) ? "dir" : "file");
				
			}

			private int getSpacesNumber(Path path) {
				return (path.getNameCount() - count) * SPACES_PER_DEPTH_LEVEL;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				printPathWithOffset(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.err.println("error: " + exc);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc != null) {
					System.err.println("error: " + exc);
				}
				return FileVisitResult.CONTINUE;
			}
		});

}
}
