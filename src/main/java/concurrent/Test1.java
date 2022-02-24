package concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j(topic="Test1")
public class Test1 {

    public static void main(String[] args) {
        for (int i=0; i < 5; i++) {
            new People1().start();
        }
        try {
            Thread.sleep(1000);
//            log.debug("信箱准备好了: {}", MailBoxes.getIds());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1s之后邮递员开始派信了
        Set<Integer> ids = MailBoxes.getIds();
        log.debug("所有信箱编号是： {}", ids);
        log.debug("信箱：{}", MailBoxes.getBoxes());
        for (Integer id: MailBoxes.getIds()) {
            log.debug("创建邮递员线程，送的信id是：{}", id);
            new PostMan1(id, "信件"+id).start();
        }
    }
}

@Slf4j(topic = "people")
class People1 extends Thread {

    @Override
    public void run() {
        //收信
        GuardObject guardObject = MailBoxes.createGuardedObject();
        // 每个居民线程的邮箱
        log.debug("我的信箱是: {}", guardObject.getId());
        // 从邮箱中拿信，最多等待5s，没有信就不等了
        Object mail = guardObject.get(5000);
//        log.debug("开始收信 id:{}", guardObject.getId());
        log.debug("收到信 id:{}, 内容:{}", guardObject.getId(), mail);
    }
}

@Slf4j(topic = "postman")
class PostMan1 extends Thread{
    private int id;
    private String mail;
    public PostMan1(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardObject guardObject  = MailBoxes.getGuardedObject(id);
        log.debug("开始派信 id:{}, 内容:{}", guardObject.getId(), mail);
        guardObject.put(mail);
    }
}

class MailBoxes {
    public static Map<Integer, GuardObject> getBoxes() {
        return boxes;
    }
    private static int id = 1;

    // 线程安全的map
    private static Map<Integer, GuardObject> boxes = new Hashtable<>();

    private static synchronized int generateId() {
        return id++;
    }

    public static GuardObject createGuardedObject() {
//        int id = generateId();
        GuardObject go = new GuardObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static synchronized GuardObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }

}

/**
 * 设计模式之保护性暂停
 * */
class GuardObject{

    public int getId() {
        return id;
    }

    private int id;

    public GuardObject(int id) {
        this.id = id;
    }

    private Object response;

    private final Object lock = new Object();

    // 1.获取结果
    // 1.1 增加超时功能的获取结果方法，传入超时参数timeout
    public Object get(long timeout) {
        synchronized (this) {
            //记录开始时间
            long start = System.currentTimeMillis();
            long passedTime = 0;
//            long waitTime = 0;
            while (response == null) {
                long waitTime = timeout - passedTime;;
                try {
                    if (passedTime >= timeout) {
                        break;
                    }

                    this.wait(waitTime);
                    // 已经等待的时间

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //经历时间
                passedTime = System.currentTimeMillis() - start;

            }
            return this.response != null? this.response: null;
        }
    }

    // 产生结果
    public void put(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            // 唤醒等到线程
            this.notifyAll();
        }
    }
}
