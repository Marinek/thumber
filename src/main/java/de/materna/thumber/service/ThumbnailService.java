package de.materna.thumber.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ThumbnailService {

    @Autowired
    private List<ThumbnailConverter> converterList;
    
    @Autowired
    private NoPreviewImage noPreviewImg;
    
    public BufferedImage convert(MultipartFile file) {
        BufferedImage image = null;
        try {
            image = this.convertInternal(file);
        } catch (IOException e) {
            log.error("Error while converting multipart file!", e);
        }        

        if(image == null) {
            image =  noPreviewImg.noPreviewImage();
        }

        return image;
    }

    private BufferedImage convertInternal(MultipartFile file) throws IOException {
        if(file == null) {
            return null;
        }

        if(file.getSize() == 0) {
            return null;
        }

        for(ThumbnailConverter converter : converterList ) {
            if(converter.canConvert(MimeType.valueOf(file.getContentType()))) {
                return converter.doConvert(file.getInputStream());
            }
        }

        return null;
    }
}
