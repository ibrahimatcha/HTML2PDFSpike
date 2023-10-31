import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static com.microsoft.playwright.Page.*;

public class PlaywrightPDF {

    public static void main(String[] args) throws IOException, InterruptedException {
        playwrightSeparateOptions();
        playwrightInlineOptions();
    }

    public static void playwrightSeparateOptions() throws IOException {
        try (Playwright playwright = Playwright.create()) {
            Page page;
            PdfOptions options = new PdfOptions();
            options.setFormat("A4");
            options.setPath(Path.of("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/playwright1.pdf"));
            options.setPrintBackground(true);
            String content = Jsoup.parse(new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html"), "UTF-8").toString();
            try (Browser browser = playwright.chromium().launch()) {
                page = browser.newPage();
                page.setContent(content);
                page.pdf(options);
            }
        }
    }

    public static void playwrightInlineOptions() throws IOException {
        try (Playwright playwright = Playwright.create()) {
            Page page;
            Path path = Path.of("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/playwright2.pdf");
            String content = Jsoup.parse(new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html"), "UTF-8").toString();
            try (Browser browser = playwright.chromium().launch()) {
                page = browser.newPage();
                page.setContent(content);
                page.pdf(new PdfOptions()
                        .setFormat("A4")
                        .setPath(path)
                        .setPrintBackground(true)
                );
            }
        }
    }
}
