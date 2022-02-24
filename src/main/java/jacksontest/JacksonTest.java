package jacksontest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fastjson.entity.UserInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JacksonTest {
    public static void main(String[] args) throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Bree");
        userInfo.setAge(40);
        userInfo.setCreateDate(LocalDateTime.now());
        userInfo.setCareer("Housewife");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            String userStr = objectMapper.writeValueAsString(userInfo);
            System.out.println(userStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("Susan");
        userInfo1.setAge(40);
        userInfo1.setCreateDate(LocalDateTime.now());
        userInfo1.setCareer("Housewife");

        // 集合转换成json字符串
        List<UserInfo> list = new ArrayList<>();
        list.add(userInfo);
        list.add(userInfo1);
        String userListStr = objectMapper.writeValueAsString(list);
        System.out.println(userListStr);

    }
}
