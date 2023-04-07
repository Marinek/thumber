package de.materna.thumber.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class NoPreviewImage {
    
    public BufferedImage noPreviewImage() {
        var imgFile = new ClassPathResource("no-image-icon-23485.png");

        try {
            return ImageIO.read(imgFile.getInputStream());
        } catch (IOException e) {
            log.error("Error while loading default image!", e);
        }

        return null;
    }
}
