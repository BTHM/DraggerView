package win99.com.miaogu9.domain;

/**
 * @author sanshu
 * @data 16/9/28 下午3:27
 * @ToDo ${TODO}
 */

public class AccountInfo {
    /**
     * state : 0000
     * message : 成功
     * memberId : 10000
     */

    private String state;
    private String message;
    private String memberId;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
