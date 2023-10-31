import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.options.PDFOptions;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Jvppeteer {

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

        ArrayList<String> arrayList = new ArrayList<>();

        LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");
        Browser browser = Puppeteer.launch(options);
        Page page = browser.newPage();
        String content = Jsoup.parse(new File("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/test.html"), "UTF-8").toString();
        page.setContent(content);
        PDFOptions pdfOptions = new PDFOptions();
        pdfOptions.setFormat("A4");
        pdfOptions.setPrintBackground(true);
        pdfOptions.setPath("/Users/ibrahim.atcha/IdeaProjects/HTML2PDFSpike/src/main/resources/jvppeteer.pdf");
        page.pdf(pdfOptions);
        page.close();
        browser.close();
    }
}
