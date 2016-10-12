package win99.com.miaogu.view;

/**
 * @author sanshu
 * @data 16/10/9 上午10:43
 * @ToDo ${TODO}
 */

import win99.com.miaogu.util.LogUtil;

/**
 * @author sanshu
 * @data 16/10/9 上午10:39
 * @ToDo ${TODO}
 */
public class Person {

    public Person() {
        show(); // 第四调用（它会调用子类覆写过的 show() 方法）
    }

    public void show() {
        LogUtil.dd("Person show");
    }

    {   // 第三调用
        LogUtil.dd("Person's no static block");
    }

    static { // 第一调用
        LogUtil.dd("Person's static block");
    }
}



