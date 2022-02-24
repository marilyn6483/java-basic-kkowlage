package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.UserInfo;
import org.junit.Test;
import response.ResponseBean;

import java.time.LocalDateTime;
import java.util.List;

public class JacksonTest {

    @Test
    public void jsonStr2JavaBeanTest() throws JsonProcessingException {

//        String jsonStr = "//  {\"flag\":false,\"data\":{\"name\":\"Bree\",\"age\":40,\"address\":null,\"createDate\":\"2022-02-24 20:30:13\"},\"errorMessage\":\"Success\"}\n";
        String jsonStr = "{\"name\":\"Bree\",\"age\":40,\"address\":null,\"createDate\":\"2022-02-24 20:40:41\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        UserInfo userInfo = objectMapper.readValue(jsonStr, UserInfo.class);
        System.out.println(userInfo);
//        UserInfo(name=Bree, age=40, address=null, createDate=2022-02-24T20:40:41)
//

    }

    /**
     * json字符串转换为List对象
     */
    @Test
    public void jsonStr2JavaBeanListTest() throws JsonProcessingException {
        String jsonStr = "    [" +
                "{\"name\":\"Bree\",\"age\":40,\"address\":null,\"createDate\":\"2022-02-24 20:46:09\"}," +
                "{\"name\":\"Susan\",\"age\":40,\"address\":null,\"createDate\":\"2022-02-24 20:46:09\"}]\n";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        List<UserInfo> userInfoList = objectMapper.readValue(jsonStr, new TypeReference<List<UserInfo>>() {
        });
        System.out.println(userInfoList);
//        [UserInfo(name=Bree, age=40, address=null, createDate=2022-02-24T20:46:09), UserInfo(name=Susan, age=40, address=null, createDate=2022-02-24T20:46:09)]

    }

    @Test
    public void responseBean2JsonStringTest() throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Bree");
        userInfo.setAge(40);
        userInfo.setCreateDate(LocalDateTime.now());
        ResponseBean<UserInfo> responseBean = new ResponseBean();

        responseBean.setFlag(Boolean.FALSE);
        responseBean.setErrorMessage("Success");
        responseBean.setData(userInfo);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String str = objectMapper.writeValueAsString(responseBean);
        System.out.println(str);

//  {"flag":false,"data":{"name":"Bree","age":40,"address":null,"createDate":"2022-02-24 20:30:13"},"errorMessage":"Success"}


    }
}
