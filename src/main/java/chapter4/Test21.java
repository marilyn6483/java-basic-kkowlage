package chapter4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 消息队列
 * */
@Slf4j(topic = "c.Test21")
public class Test21 {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 5; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
                log.debug("获取到->{}", message);

            }

        }, "消費者").start();
    }
}

@Slf4j(topic = "c.MessageQueue")
class MessageQueue{

    private LinkedList<Message> messageList = new LinkedList<>();

    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }


    public void put(Message message) {
        synchronized (messageList) {
            while (messageList.size() == capacity) {
                try {
                    log.debug("队列已满，生产者在等待");
                    messageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageList.addLast(message);
//            capacity++;
            log.debug("已生产消息 -> {}", message);
            messageList.notify();
        }

    }

    /**
     * 取消息
     * */
    public Message take() {
        synchronized (messageList) {
            while (messageList.isEmpty()) {
                try {
                    log.debug("队列为空，消费者在等待");
                    messageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = messageList.removeFirst();
//            capacity--;
            log.debug("已消费消息 -> {}", message);
            messageList.notifyAll();
            return message;
        }


//        return null;
    }
}

class Message {

    private int id;

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    private Object value;


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
