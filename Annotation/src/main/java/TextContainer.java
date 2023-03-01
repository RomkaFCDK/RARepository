import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface SaveTo{
    String path();
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface SaveMeth {

}

@SaveTo(path="C:\\Users\\Роман\\OneDrive\\Рабочий стол\\Documents\\JavaLectures\\file.txt")
class TextContainer {
    String text = "Java the best";

    @SaveMeth
    public void save() throws IOException {
        File file = new File(getSaveToPath());
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(file))) {
            wr.write(text);
        }
    }

    String getSaveToPath() throws IOException {
        SaveTo annotation = getClass().getAnnotation(SaveTo.class);
        if (annotation == null) {
            throw new IOException("SaveTo failed!");
        }
        return annotation.path();
    }
}
