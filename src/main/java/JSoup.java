import com.lowagie.text.DocumentException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class JSoup {

    public static void main(String[] args) throws IOException, DocumentException {
        flyingSaucer();
        openHTMLToPDF();
    }

    public static void flyingSaucer() throws IOException, DocumentException {
        File input = new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html");
        File output = new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/flyingsaucer.pdf");
        Document document = Jsoup.parse(input, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        String inputHTML = document.html();

        try (OutputStream outputStream = new FileOutputStream(output)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(inputHTML);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    public static void openHTMLToPDF() throws IOException {
        File input = new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html");
        String output = "/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/openhtml.pdf";
        Document document = Jsoup.parse(input, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (OutputStream os = new FileOutputStream(output)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(output);
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(document), "/");
            builder.run();
        }
    }
}
