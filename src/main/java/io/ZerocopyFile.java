package com.smarter.user;

/**
 * @author 玄策
 * @version 1.0
 * @date 2021/2/19 1:46 下午
 * @description:
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * disk-disk零拷贝
 */
class ZerocopyFile {
    @SuppressWarnings("resource")
    public static void transferToDemo(String from, String to) throws IOException {
        FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
        FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);

        fromChannel.close();
        toChannel.close();
    }

    @SuppressWarnings("resource")
    public static void transferFromDemo(String from, String to) throws IOException {
        FileChannel fromChannel = new FileInputStream(from).getChannel();
        FileChannel toChannel = new FileOutputStream(to).getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.close();
        toChannel.close();
    }

    public static void main(String[] args) throws IOException {
        String from = "/Users/fangkun/workspace/smarter-user-service/smarter-user-service-provider/src/main/java/test.data";
        String to = "/Users/fangkun/workspace/smarter-user-service/smarter-user-service-provider/src/main/java/test1.data";
        // transferToDemo(from,to);
        transferFromDemo(from, to);
    }
}