package com.dewen.service.impl;

import com.dewen.service.IThumbnailsService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

@Service
public class IThumbnailsServiceImpl implements IThumbnailsService {

    @Override
    public String changeSize(MultipartFile resource, int width, int height, String toFile) {
        try {
            Thumbnails.of(resource.getInputStream()).size(width, height).outputFormat("jpg").toFile(toFile + resource.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "changeSize";
    }

    @Override
    public String changeScale(MultipartFile resource, double scale, String toFile) {
        try {
            Thumbnails.of(resource.getInputStream()).scale(scale).toFile(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "changeScale";
    }

    @Override
    public String watermark(MultipartFile resource, Positions center, MultipartFile watermark, float opacity, String toFile) {
        try {
            Thumbnails.of(resource.getInputStream()).scale(1).watermark(center, ImageIO.read(watermark.getInputStream()), opacity).toFile(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "watermark";
    }

    @Override
    public String rotate(MultipartFile resource, double rotate, String toFile) {
        try {
            Thumbnails.of(resource.getInputStream()).scale(1).rotate(rotate).toFile(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "rotate";
    }

    @Override
    public String region(MultipartFile resource, Positions center, int width, int height, String toFile) {
        try {
            Thumbnails.of(resource.getInputStream()).scale(1).sourceRegion(center, width, height).toFile(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "region";
    }

}

