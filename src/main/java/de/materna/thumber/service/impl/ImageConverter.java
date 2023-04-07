package de.materna.thumber.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import de.materna.thumber.service.ThumbnailConverter;

@Component
public class ImageConverter implements ThumbnailConverter {

    @Override
    public boolean canConvert(MimeType mimeType) {
        return MimeType.valueOf("image/*").isCompatibleWith(mimeType);
    }

    @Override
    public BufferedImage doConvert(InputStream image) throws IOException {
        if(image == null) {
            return null;
        }
        
        Image scaledInstance = ImageIO.read(image).getScaledInstance(200, 200, BufferedImage.SCALE_SMOOTH);

        BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        img.createGraphics().drawImage(scaledInstance, 0, 0, null);

        return img;
    }

}
