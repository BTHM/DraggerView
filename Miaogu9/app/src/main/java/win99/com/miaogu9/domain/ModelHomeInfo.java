package win99.com.miaogu9.domain;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by pangweiwei on 16/7/21.
 */

public class ModelHomeInfo implements Serializable {
    public String title;
    public String url;
    public String date;

    public ModelHomeInfo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public ModelHomeInfo(String title, String url, String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    protected ModelHomeInfo(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.date = in.readString();
    }

}
