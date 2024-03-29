package by.red.chap9.nio.selectorserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Server {
    private static final int BUFFER_SIZE = 1024;
    private static String[] messages ={"aaaaaaaadfghfjgkhljk"};

    public static void main(String[] args) {
        logger("Starting Server...");
        try {
            int port = 9999;
            InetAddress hostIP = InetAddress.getLocalHost();
            InetSocketAddress myAddress =
                    new InetSocketAddress(hostIP, port);
            SocketChannel myClient = SocketChannel.open(myAddress);
            logger(String.format("Trying to connect to %s:%d...",
                    myAddress.getHostName(), myAddress.getPort()));
            for (String msg: messages) {
                ByteBuffer myBuffer=ByteBuffer.allocate(BUFFER_SIZE);
                myBuffer.put(msg.getBytes());
                myBuffer.flip();
                int bytesWritten = myClient.write(myBuffer);
                logger(String
                        .format("Sending Message...: %s\nbytesWritten...: %d",
                                msg, bytesWritten));
            }
            logger("Closing Client connection...");
            myClient.close();
        } catch (IOException e) {
            logger(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void logger(String msg) {
        System.out.println(msg);
    }
}