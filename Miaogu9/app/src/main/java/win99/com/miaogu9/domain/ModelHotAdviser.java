package win99.com.miaogu9.domain;

import java.io.Serializable;

/**
 * @author sanshu
 * @data 16/9/17 下午8:18
 * @ToDo ${TODO}
 */

public class ModelHotAdviser implements Serializable{
    /**
     * q_content : 请老师看下劲胜精密后期走势
     * worker_name : 叶弋
     * worker_group : 毅财经
     * q_type : 300083
     * a_time : 1473404751000
     * q_id : 6894a0f7-a037-4739-93cf-6766143a588e
     * worker_title : 投资顾问
     * q_time : 1473394113000
     * a_content : 劲胜精密前期放量跌破年线，中期趋势又走坏的现象，而近日上攻60日线受阻，放量明显。正所谓量在价先，后市如果有效突破60日线，或许会展开一波行情。
     */

    private String q_content;
    private String worker_name;
    private String worker_group;
    private String q_type;
    private long   a_time;
    private String q_id;
    private String worker_title;
    private long   q_time;
    private String a_content;

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_group() {
        return worker_group;
    }

    public void setWorker_group(String worker_group) {
        this.worker_group = worker_group;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }

    public long getA_time() {
        return a_time;
    }

    public void setA_time(long a_time) {
        this.a_time = a_time;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getWorker_title() {
        return worker_title;
    }

    public void setWorker_title(String worker_title) {
        this.worker_title = worker_title;
    }

    public long getQ_time() {
        return q_time;
    }

    public void setQ_time(long q_time) {
        this.q_time = q_time;
    }

    public String getA_content() {
        return a_content;
    }

    public void setA_content(String a_content) {
        this.a_content = a_content;
    }




}
