package files;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NioDemo {
    @SneakyThrows
    public static void main(String[] args) {

        FileInputStream inputStream = new FileInputStream("/home/ranjan/IdeaProjects/demo/src/main/java/files/data.txt");
        ReadableByteChannel source = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("/home/ranjan/IdeaProjects/demo/src/main/java/files/destination.txt");
        WritableByteChannel destination = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (source.read(buffer) != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                destination.write(buffer);
            }
            buffer.clear();
        }
        source.close();
        destination.close();
    }
}
