package com.challenge.arts.week31.nio;

import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_READ;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * nio 服务端
 */
public class NIOSocketServer {
    // 服务端Channel
    private ServerSocketChannel serverSocketChannel;
    // 选择器
    private Selector selector;
    // 客户端梳理
    private int clientNums;
    // buffer
    private ByteBuffer byteBuffer;
    // buffer大小
    private static final int BUFFER_SIZE = 1024;
    /**
     * 构造函数
     * @param port 监听端口
     */
    public NIOSocketServer(int port) {
        try {
            initChannel(port);
        } catch (IOException e) {
            System.out.printf("初始化Channel异常", e);
        }
    }

    /**
     * 初始化Channel
     */
    private void initChannel(int port) throws IOException {
        // 打开一个Channel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.printf("开启端口：%d", port);
        // 创建选择器
        selector = Selector.open();
        // 向选择器注册Channel
        serverSocketChannel.register(selector, OP_ACCEPT);
        // 分配缓冲器大小
        byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    public static void main(String[] args) {
        try {
            NIOSocketServer nioSocketServer = new NIOSocketServer(9099);
            nioSocketServer.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 监听事件
     */
    private void listen() throws Exception{
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                switch (selectionKey.interestOps()){
                    case OP_ACCEPT :
                        // 服务端的Channel
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        // 接收一个客户端
                        SocketChannel clientChannel = serverChannel.accept();
                        if (clientChannel == null) {
                            break;
                        }
                        clientChannel.configureBlocking(false);
                        // 将客户端注册到Selector
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        // 客户端数+1
                        clientNums++;
                        System.out.printf("客户端连接： 当前客户端数量:%d \n", clientNums);
                        // 向客户端发送欢迎消息
                        writeToClient(clientChannel, "Hello Client ! Welcome to Server !\n");
                        break;
                    case OP_READ:
                        // 读取内容
                        readMessage(selectionKey);
                        break;
                }
                iterator.remove();
            }
        }
    }

    private void readMessage(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        byteBuffer.clear();
        int count;
        while ((count = channel.read(byteBuffer))>0 ) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                CharBuffer decode = StandardCharsets.UTF_8.decode(byteBuffer);
                System.out.printf(decode.toString());
            }
            byteBuffer.clear();
        }
        if (count <0) {
            channel.close();
        }
    }

    private void writeToClient(SocketChannel clientChannel, String message) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        clientChannel.write(byteBuffer);
    }


}
