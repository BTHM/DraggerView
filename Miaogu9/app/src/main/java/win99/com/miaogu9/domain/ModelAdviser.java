package win99.com.miaogu9.domain;

import java.io.Serializable;

/**
 * Created by pangweiwei on 16/7/21.
 */

public class ModelAdviser implements Serializable {
    public String ask;
    public String name;
    public String company;
    public String answer;
    public String time;
    public String url;
    public String qtype;
    public String qid;



    public ModelAdviser(String qid, String qtype, String ask, String name, String company, String answer, String time, String url) {
        this.ask = ask;
        this.name = name;
        this.company = company;
        this.answer = answer;
        this.time = time;
        this.url = url;
        this.qtype = qtype;
        this.qid = qid;
    }
}
