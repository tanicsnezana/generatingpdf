import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {
    @Autowired
    PdfGeneratorUtil pdfGenaratorUtil = new PdfGeneratorUtil();

    public void export(Map data) {
        // PdfGeneratorUtil pdfGeneratorUtil = new PdfGeneratorUtil();
        try {
            pdfGenaratorUtil.createPdf("greeting", data);
        }
        catch (Exception e){
            e.printStackTrace();
            // System.out.println(e.printStackTrace());
        }
    }

    public static void main(String [] args) {
        Map<String,String> data = new HashMap<String,String>();
        data.put("name","James");
        Main m = new Main();
        m.export(data);
    }
}
