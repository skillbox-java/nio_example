package file_copy_example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileCopySimpleExample {
    public static void main(String[] args) {
        Path inputPath = Path.of("src/file_copy_example/input.txt");
        Path outputPath = Path.of("src/file_copy_example/output.txt");

        try (FileChannel inputChannel = FileChannel.open(inputPath, StandardOpenOption.READ);
             FileChannel outputChannel = FileChannel.open(outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
