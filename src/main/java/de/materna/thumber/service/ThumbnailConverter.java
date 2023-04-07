package de.materna.thumber.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.MimeType;

public interface ThumbnailConverter {
    
    public boolean canConvert(MimeType mimeType);

    public BufferedImage doConvert(InputStream pdfInput) throws IOException;
}
