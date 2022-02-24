package entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    private String name;
    private Integer age;
    private String address;

    private LocalDateTime createDate;
}
