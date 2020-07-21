package xyz.ddxiong.travel.admin.utils;


import java.util.ResourceBundle;

/**
 * 作用: 解析配置文件,根据配置文件中的类全限定名,创建类对象
 * 创建Bean对象的时机:
 *      当程序调用BeanFactory.getBean方法时才会创建对象,并且每调用一次就会创建一个Bean对象
 *      创建的Bean对象是多实例的
 * bean对象: java类对象
 */
public class BeanFactory {
    private static ResourceBundle bundle;
    static {
        // 解析配置文件,得到类的全限定名
        bundle = ResourceBundle.getBundle("beans");
    }

    /**
     * 根据传入的唯一标识,获取类的全限定名,通过反射创建类对象
     * @param id
     * @return
     */
    public static Object getBean(String id){
        Object object = null;
        try {
            // 获取需要创建的类的全限定名
            String className = bundle.getString(id);
            object = Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
