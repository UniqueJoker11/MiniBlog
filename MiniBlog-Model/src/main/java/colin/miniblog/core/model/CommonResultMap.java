package colin.miniblog.core.model;

/**
 * Created by joker on 16-3-12.
 */
public class CommonResultMap<T> {
    private boolean isSuccess;
    private String msg;
    private T t;

    public CommonResultMap(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public CommonResultMap(boolean isSuccess, String msg, T t) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.t = t;
    }

    public CommonResultMap(boolean isSuccess, T t) {
        this.isSuccess = isSuccess;
        this.t = t;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
