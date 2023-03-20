package client_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NetworkServerExample {
    private static final int PORT = 8088;

    public static void main(String[] args) throws IOException {
        // если ваш порт занят, укажите свободный порт
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        System.out.println("Server started on port " + PORT);

        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Client connected: " + socketChannel.getRemoteAddress());

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buffer);
        String message = new String(buffer.array(), 0, bytesRead);
        System.out.println("Received message from client: " + message);

        socketChannel.close();
        serverSocketChannel.close();
    }
}
