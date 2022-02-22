package fastjson.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserInfo {
    private String name;
    private int age;
    private String address;
    private String career;
    private LocalDateTime createDate;
}
