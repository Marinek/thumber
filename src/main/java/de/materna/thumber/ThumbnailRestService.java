package de.materna.thumber;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.materna.thumber.service.ThumbnailService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ThumbnailRestService {

    @Autowired
    private ThumbnailService thumbnailService;

    @PostMapping("/convert")
    public BufferedImage convertToPNG(@RequestParam("image") MultipartFile file) throws IOException {
        log.debug("Convertig: " + file.getOriginalFilename());
        return thumbnailService.convert(file);
    }
}