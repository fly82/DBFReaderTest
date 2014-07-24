import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBFRead {
    private static String str;

    public static void main(String... args) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("ma.dbf");
            DBFReader reader = new DBFReader(inputStream);
            int numberOfFields = reader.getFieldCount();
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                System.out.print(field.getName() + " | ");
            }
            System.out.println();

            Object[] rowObject;
            while ((rowObject = reader.nextRecord()) != null) {
                for (int i = 0; i < rowObject.length; i++) {
                    str = rowObject[i] != null ?
                            new String(rowObject[i].toString().getBytes("ISO-8859-1"), "866") : "   ";
                    System.out.print(str);
                    if (i==0) for (int j = 0; j < 6 - str.length(); j++) System.out.print(" ");
                    if (i > 7) for (int j = 0; j < 7 - str.length(); j++) System.out.print(" ");
                    System.out.print("|");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (inputStream != null) inputStream.close();
        }
    }
}
