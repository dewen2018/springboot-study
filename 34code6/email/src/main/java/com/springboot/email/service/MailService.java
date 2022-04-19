package com.springboot.email.service;

/**
 * study 2018-10-13
 */
public interface MailService {
    /**
     * 简单邮件
     *
     * @param to
     * @param subject 主题
     * @param content 正文
     */
    public void sendSimpleMail(String to, String subject, String content);

    /**
     * 模板邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 带附件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath 文件路径
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 页面直接显示图片,rscId,不能带中文
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath 图片路径
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
