package thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数据共享通道
 * ArrayBlockingQueue   数组  有界
 * ListBlockingQueue    链表 无界
 **/
public class BlockingQueueDemo {

    /**
     * 如何让程序在队列有消息时 处理
     * 按时循环监控:浪费时间
     * 使用put和offer()存入数据,put()时,若已经满了则等待,offer()会返回false
     * 从队列获取元素:poll()和take()，若队列为空,poll()会返回null,take()一直等待
     * **/

    public static void main(String[] args) {
        ArrayBlockingQueue queue;
    }
}
