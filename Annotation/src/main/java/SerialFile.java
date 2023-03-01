public class SerialFile {
    @Save
    private String string;
    @Save
    private int d;
    private double e;

    public SerialFile() {

    }

    public SerialFile(String string,int d,double e) {
        this.string = string;
        this.d = d;
        this.e = e;
    }

    public static void main(String[] args) {
        SerialFile sf = new SerialFile("Hello",3,7.7);
        Serializator.serialization(sf,"myfile.txt");
        SerialFile sf2 = (SerialFile) Serializator.deserialization(SerialFile.class, "myfile.txt");
        System.out.println(sf2.string);
        System.out.println(sf2.d);
        System.out.println(sf2.e);
    }
}
