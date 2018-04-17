import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

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

        // first name
        data.put("givenName", "Snezana");

        // last name
        data.put("name", "Tanic");

        // street
        data.put("street", "Borska");

        // street number
        data.put("streetNumber", "36a");

        // postal code
        data.put("postalCode", "11090");

        // town
        data.put("town", "Belgrade");

        // contract duration
        data.put("contractDuration", "3");

        // start date of contract
        data.put("startDate", "6/12/2018");

        // bundle type
        data.put("bundleType", "Premium");

        // public transport
        data.put("publicTransport", "Public transport");

        // e-car
        data.put("eCar", "E - car");

        // wallbox
        data.put("wallbox", "Charger");

        // parking
        data.put("parking", "Parking");

        // complementary mobility carsharing
        data.put("complementaryMobilityCarsharing", "Something");

        // complementary mobility bikesharing
        data.put("complementaryMobilityBikesharing", "Something2");

        // complementary mobility taxi
        data.put("complementaryMobilityTaxi", "Something3");

        // start date minus one
        data.put("startDateMinusOne", "5/12/2018");

        // careless
        data.put("careless", "Careless");

        // monthly price
        data.put("monthlyPrice", "100CHF");

        // price wallbox
        data.put("priceWallbox", "20CHF");

        // payment mode
        data.put("paymentMode", "True");

        // system date
        data.put("systemDate", "10/12/2018");

        Main m = new Main();
        m.export(data);
    }
}
