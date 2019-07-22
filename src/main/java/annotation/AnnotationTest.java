package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

    @Two
    public String getAll() {
        return "hello world !";
    }
    public static void main(String[] args) throws NoSuchMethodException {
        Class<AnnotationTest> x = AnnotationTest.class;
        Method methd = x.getDeclaredMethod("getAll", new Class[]{});
        Annotation[] anns = methd.getDeclaredAnnotations(); /**声明的注解*/

        Annotation a = null;
        Annotation a2 = null;
        for (Annotation ann : anns) {
            if (ann.annotationType() == Base.class) {
                a = ann;
            }
            if (ann.annotationType() == Base2.class) {
                a2 = ann;
            }
        }
        if(a==null){
            for (Annotation ann : anns) { /**演示只有2层的，多层需要递归处理*/
                if (!(ann.annotationType()!= null&& ann.annotationType().getName().startsWith("java.lang.annotation"))) {
                    Class<? extends Annotation> annParent = ann.annotationType();
                    Annotation[]  anns2 = annParent.getDeclaredAnnotations();
                    for (Annotation ann2 : anns2) {
                        if (ann2.annotationType() == Base.class) {
                            a = ann2;
                        }
                        if (ann.annotationType() == Base2.class) {
                            a2 = ann;
                        }
                    }
                }
            }
        }

        if(a==null){
            System.out.println("没找到注解");
        }else{
            Base res = (Base)a;
            System.out.println(res.value());
        }

    }
}
