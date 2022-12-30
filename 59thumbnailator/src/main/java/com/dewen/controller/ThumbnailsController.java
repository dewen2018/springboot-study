package com.dewen.controller;

import com.dewen.service.IThumbnailsService;
import io.swagger.annotations.ApiImplicitParam;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class ThumbnailsController {
    //文件存储路径
    private final String TO_FILE = "D:\\codes\\code2\\springboot-study\\59thumbnailator\\Thumbnails\\";
    @Resource
    private IThumbnailsService thumbnailsService;

    // @ApiOperation("多文件上传")
    // @PostMapping("/upload/batch")
    // @ApiImplicitParam(name = "files", value = "上传附件", dataType = "java.io.File", required = true)
    // @ApiImplicitParam(name = "files", value = "上传附件", dataTypeClass = MultipartFile.class, required = true)
    // public void editCardAvatar(@RequestPart("files") MultipartFile[] files) {
    // }

    /**
     * 指定大小缩放
     *
     * @param files
     * @param width
     * @param height
     * @return
     */
    // @PostMapping("/changeSize")
    // @ApiImplicitParam(name = "files", value = "上传附件", dataTypeClass = MultipartFile.class, required = true)
    // public String changeSize(@RequestPart("files") MultipartFile[] files, int width, int height) {
    //     MultipartFile resource = files[0];
    //     return thumbnailsService.changeSize(resource, width, height, TO_FILE);
    // }
    @PostMapping("/changeSize")
    @ApiImplicitParam(name = "resource", value = "上传附件", dataTypeClass = MultipartFile.class, required = true)
    public String changeSize(@RequestPart("resource") MultipartFile resource, int width, int height) {
        return thumbnailsService.changeSize(resource, width, height, TO_FILE);
    }

    /**
     * 指定比例缩放
     *
     * @param resource
     * @param scale
     * @return
     */
    @GetMapping("/changeScale")
    public String changeScale(MultipartFile resource, double scale) {
        return thumbnailsService.changeScale(resource, scale, TO_FILE);
    }

    /**
     * 添加水印 watermark(位置,水印,透明度)
     *
     * @param resource
     * @param p
     * @param shuiyin
     * @param opacity
     * @return
     */
    @GetMapping("/watermark")
    public String watermark(MultipartFile resource, Positions p, MultipartFile shuiyin, float opacity) {
        return thumbnailsService.watermark(resource, Positions.CENTER, shuiyin, opacity, TO_FILE);
    }

    /**
     * 图片旋转 rotate(度数),顺时针旋转
     *
     * @param resource
     * @param rotate
     * @return
     */
    @GetMapping("/rotate")
    public String rotate(MultipartFile resource, double rotate) {
        return thumbnailsService.rotate(resource, rotate, TO_FILE);
    }

    /**
     * 图片裁剪
     *
     * @param resource
     * @param p
     * @param width
     * @param height
     * @return
     */
    @GetMapping("/region")
    public String region(MultipartFile resource, Positions p, int width, int height) {
        return thumbnailsService.region(resource, Positions.CENTER, width, height, TO_FILE);
    }
}