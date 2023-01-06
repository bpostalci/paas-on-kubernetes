package tr.com.swe599;

import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

@Service
public class HtmlSyntaxCheckerService {
    public CheckSyntaxDto checkSyntax(String html) {

        Tidy tidy = new Tidy();
        InputStream htmlStream = new ByteArrayInputStream(html.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        OutputStream errorStream = new ByteArrayOutputStream();
        PrintWriter errorWriter = new PrintWriter(errorStream, true);
        tidy.setErrout(errorWriter);

        tidy.parse(htmlStream, outputStream);

        return new CheckSyntaxDto(errorStream.toString());
    }
}
