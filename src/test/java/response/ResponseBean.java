package response;

import lombok.Data;

/**
 * 服务端返回的数据对象
 */
@Data
public class ResponseBean<T> {
    // 服务器是否异常的标志位
    private Boolean flag;

    // 服务器响应数据
    private T data;

    private String errorMessage;

}
