package win99.com.miaogu9.domain;

/**
 * @author sanshu
 * @data 16/9/28 下午4:13
 * @ToDo ${TODO}
 */

public class RegisterInfo {

    /**
     * mobileId : 1234567890
     * mobile : 13777777777
     * state : 0000
     * message : 成功
     */

    private String mobileId;
    private String mobile;
    private String state;
    private String message;

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
