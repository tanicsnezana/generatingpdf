import java.nio.file.Files;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import org.xhtmlrenderer.util.IOUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaReplacedElementFactory implements ReplacedElementFactory {
    private final ReplacedElementFactory superFactory;

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory) {
        this.superFactory = superFactory;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox, UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");

        if ("div".equals(nodeName) && "media".equals(className)) {
            if (!element.hasAttribute("data-src")) {
                throw new RuntimeException("An elemet with class `media` is missing a `data-src` attribute indicating the media file.");
            }

            try {
                Path path = Paths.get("/images/" + element.getAttribute("data-src"));
                final byte[] bytes = Files.readAllBytes(path);
                final Image image = Image.getInstance(bytes);
                final FSImage fsImage = new ITextFSImage(image);
                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }
                    return new ITextImageElement(fsImage);
                }
            } catch (IOException e) {
                return null;
            } catch (BadElementException exception) {
                return null;
            }
        }
            return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
        }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}