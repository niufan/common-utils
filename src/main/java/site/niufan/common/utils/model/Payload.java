package site.niufan.common.utils.model;

import site.niufan.common.utils.json.JsonUtils;

/**
 * Rest 请求，通用返回体
 * @author Fan Niu
 * @since 2018/8/8
 */
public class Payload<Code, Message, Data> {

    private Code code;
    private Message message;
    private Data data;

    Payload(Code code, Message message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
