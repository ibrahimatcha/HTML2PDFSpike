import com.ironsoftware.ironpdf.PdfDocument;
import com.ironsoftware.ironpdf.render.ChromePdfRenderOptions;
import com.ironsoftware.ironpdf.render.PaperSize;

import java.io.IOException;

public class IronPDF {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread.sleep(1000);
        ChromePdfRenderOptions options = new ChromePdfRenderOptions();
        options.setPaperSize(PaperSize.A4);
        options.setMarginBottom(0);
        options.setMarginTop(0);
        options.setMarginLeft(0);
        options.setMarginRight(0);
        options.setPrintHtmlBackgrounds(true);
        PdfDocument doc = PdfDocument.renderUrlAsPdf("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html", options);
        doc.saveAs("src/main/resources/ironpdf.pdf");
    }
}
