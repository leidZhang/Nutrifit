package api.common;

public class Result {
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "-1";

    private String message;
    private String code;
    private Object data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMessage(message);
        return result;
    }
}
