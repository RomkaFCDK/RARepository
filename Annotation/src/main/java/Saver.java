import java.lang.reflect.Method;

public class Saver {
    public static void main(String[] args) throws Exception{
    TextContainer tc = new TextContainer();
        Method[] methods = tc.getClass().getDeclaredMethods();
        for (Method method:methods) {
        if (method.isAnnotationPresent(SaveMeth.class)){
            method.invoke(tc);
            System.out.println("Text saved in: " + tc.getSaveToPath() );
        }
        }
    }
}
