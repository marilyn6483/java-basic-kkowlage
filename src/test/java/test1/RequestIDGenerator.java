package test1;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestIDGenerator {

    private static final RequestIDGenerator INSTANCE = new RequestIDGenerator();

    private static final short SEQ_UPPER_LIMIT = 999;

    private short sequence = -1;

    private RequestIDGenerator() {

    }

    // 使用共享变量的方式
    private short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }

        return sequence;
    }

    // 使用传参的方式
    private int nextSequence(int sequence) {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }

        return sequence;
    }

    public String nextID() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("000");
        int sequenceNo = nextSequence();
        System.out.println("sequenceNo -> " + sequenceNo);

        return "0049" + timestamp + df.format(sequenceNo);

    }

    public static RequestIDGenerator getInstance () {
        return INSTANCE;
    }

    public static void main(String[] args) {
        RequestIDGenerator generator = RequestIDGenerator.INSTANCE;
        System.out.println(generator);
        System.out.println(generator.nextID());
        System.out.println(generator.nextID());
        System.out.println(generator.nextID());
        System.out.println(generator.nextID());
    }


}
