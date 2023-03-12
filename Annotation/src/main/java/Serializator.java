import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Serializator {
    public static void serialization(Object obj, String filename) throws IOException, IllegalAccessException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    dos.writeUTF(field.getName());
                    dos.writeUTF(field.getType().getName());
                    dos.writeUTF(String.valueOf(field.get(obj)));
                }
            }
        }
    }


    public static void deserialization(Object obj, String filename) throws IOException, IllegalAccessException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis)) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    String nameField = dis.readUTF();
                    if (nameField.equals(field.getName())) {
                        String typeField = dis.readUTF();
                        if (!typeField.equals(field.getType().getName())) {
                            throw new IllegalStateException("Type field error");
                        }
                        String valueField = dis.readUTF();
                        field.set(obj, parsingValue(field.getType(), valueField));
                    } else {
                        throw new IllegalStateException("Name field error");
                    }
                }
            }
        }
     }
        private static Object parsingValue (Class < ? > typeField, String valueField){
            if (typeField == boolean.class) {
                return Boolean.parseBoolean(valueField);
            } else if (typeField == short.class) {
                return Short.parseShort(valueField);
            } else if (typeField == byte.class) {
                return Byte.parseByte(valueField);
            } else if (typeField == int.class) {
                return Integer.parseInt(valueField);
            } else if (typeField == long.class) {
                return Long.parseLong(valueField);
            } else if (typeField == float.class) {
                return Float.parseFloat(valueField);
            } else if (typeField == double.class) {
                return Double.parseDouble(valueField);
            } else if (typeField == char.class) {
                return valueField.charAt(0);
            } else if (typeField == String.class) {
                return valueField;
            } else {
                throw new IllegalArgumentException("Error field type" + typeField.getName());
            }

        }
    }
