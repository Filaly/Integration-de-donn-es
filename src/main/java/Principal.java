
import extractors.ExtractorExcel;

import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws Exception {

        ExtractorExcel ex = new ExtractorExcel();

        ex.connection();

        ex.disconnection();
    }
}
