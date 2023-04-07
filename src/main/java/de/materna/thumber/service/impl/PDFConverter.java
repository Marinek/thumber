package de.materna.thumber.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import de.materna.thumber.service.ThumbnailConverter;

@Component
public class PDFConverter implements ThumbnailConverter {

    @Override
    public boolean canConvert(MimeType mimeType) {
        return mimeType.equalsTypeAndSubtype(MimeType.valueOf("application/pdf"));
    }

    @Override
    public BufferedImage doConvert(InputStream pdfInput) throws IOException {
        PDDocument document = Loader.loadPDF(pdfInput);

        PDFRenderer pdfRenderer = new PDFRenderer(document);
        // Das ist nicht mega Perfekt... Aber soll zeigen, dass man das auch ausbauen kann. Klar. 
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage renderImageWithDPI = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            document.close();
            return renderImageWithDPI;
        }

        return null;
    }
    
}
