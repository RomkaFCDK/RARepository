import java.lang.reflect.Method;

public class Annotation {
    @Test(a=2,b=5)
    public void testMethod(int a,int b){
        int c = a + b;
        System.out.println(c);
    }

    public static void main(String[] args) throws Exception{
        Annotation myExample = new Annotation();
        Method[] methods = myExample.getClass().getDeclaredMethods();

        for (Method method:methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                int a = annotation.a();
                int b = annotation.b();
                method.invoke(myExample,a,b);
            }
            }
        }
    }

