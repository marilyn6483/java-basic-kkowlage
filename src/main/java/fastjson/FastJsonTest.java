package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import fastjson.entity.UserInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {
    public static void main(String[] args) {
//        1.对象转json字符串
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("JiangSu Nj");
        userInfo.setAge(20);
        userInfo.setCareer("Artist");
        userInfo.setName("Mary Yong");
        userInfo.setCreateDate(LocalDateTime.now());

        String jsonStr = JSON.toJSONString(userInfo, SerializerFeature.WriteDateUseDateFormat);
//  使用格式化参数之前      {"address":"Jiang Su Nj","age":20,"career":"Artist","createDate":"2022-02-22T22:09:34.055","name":"Mary Yong"}

        System.out.println(jsonStr);
        System.out.println("---------------------------");

//        2. json字符串转Java对象
        UserInfo userInfo1 = JSON.parseObject("{\"address\":\"Jiang Su SZ\",\"age\":20,\"career\":\"Engineer\",\"name\":\"David \"}", UserInfo.class);
        System.out.println(userInfo1);

        System.out.println("-------------------------------");

        System.out.println(LocalDateTime.now());

        System.out.println("-------------------------------");

//        3. 将List集合转换成JSON字符串
        List<UserInfo> list = new ArrayList<>();
        list.add(userInfo);
        list.add(userInfo1);
        String jsonList = JSON.toJSONString(list);
        System.out.println(jsonList);
//        返回值
// [{"address":"JiangSu Nj","age":20,"career":"Artist","createDate":"2022-02-22T22:17:47.843","name":"Mary Yong"},
// {"address":"Jiang Su SZ","age":20,"career":"Engineer","name":"David "}]
    }

}
