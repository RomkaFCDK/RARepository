import java.io.*;
import java.lang.reflect.Field;

public class Serializator {
    public static void serialization(Object obj, String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    oos.writeObject(field.get(obj));
                }
            }
        }catch(IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialization(Class<?> clazz, String filename){
        Object obj = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
            if(field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                Object thisField = ois.readObject();
                field.set(obj,thisField);
            }
            }
        }catch(IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }
}
