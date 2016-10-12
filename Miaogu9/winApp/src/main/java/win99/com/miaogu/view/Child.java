package win99.com.miaogu.view;

import win99.com.miaogu.util.LogUtil;

/**
 * @author sanshu
 * @data 16/10/9 上午10:48
 * @ToDo ${TODO}
 */

public class Child extends Person{

        public Child(){
            show(); // 第六调用（它会调用子类的 show() 方法）
        }
        public void show(){
            LogUtil.dd("Child show");
        }
        {   // 第五调用
            LogUtil.dd("Child's no static block");
        }
        static{ // 第二调用
            LogUtil.dd("Child's static block");
        }
        /*public static void main(String[] args){
            new Child();
        }*/
    }
