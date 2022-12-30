package com.dewen.service;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

public interface IThumbnailsService {

    /**
     * 指定大小缩放 若图片横比width小，高比height小，放大
     * 若图片横比width小，高比height大，高缩小到height，图片比例不变
     * 若图片横比width大，高比height小，横缩小到width，图片比例不变
     * 若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
     *
     * @param resource 源文件路径
     * @param width    宽
     * @param height   高
     * @param toFile   生成文件路径
     */
    String changeSize(MultipartFile resource, int width, int height, String toFile);

    /**
     * 指定比例缩放 scale(),参数小于1,缩小;大于1,放大
     *
     * @param resource 源文件路径
     * @param scale    指定比例
     * @param toFile   生成文件路径
     */
    String changeScale(MultipartFile resource, double scale, String toFile);

    /**
     * 添加水印 watermark(位置,水印,透明度)
     *
     * @param resource  源文件路径
     * @param center    水印位置
     * @param watermark 水印文件
     * @param opacity   水印透明度
     * @param toFile    生成文件路径
     */
    String watermark(MultipartFile resource, Positions center, MultipartFile watermark, float opacity, String toFile);

    /**
     * 图片旋转 rotate(度数),顺时针旋转
     *
     * @param resource 源文件路径
     * @param rotate   旋转度数
     * @param toFile   生成文件路径
     */
    String rotate(MultipartFile resource, double rotate, String toFile);

    /**
     * 图片裁剪 sourceRegion()有多种构造方法，示例使用的是sourceRegion(裁剪位置,宽,高)
     *
     * @param resource 源文件路径
     * @param center   裁剪位置
     * @param width    裁剪区域宽
     * @param height   裁剪区域高
     * @param toFile   生成文件路径
     */
    String region(MultipartFile resource, Positions center, int width, int height, String toFile);
}

