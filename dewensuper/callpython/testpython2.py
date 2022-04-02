# -*- coding: utf-8 -*-
# import smtplib
# from email.mime.text import MIMEText

# 邮件服务器地址
mail_host = 'smtp.qq.com'
# 邮件服务器用户名
mail_user = 'xxx@qq.com'
# 邮件服务器登录密码(有些是授权码)
mail_pwd = 'xxx'
# 邮件发送者
sender = 'xxx@qq.com'

def sendEmail(receiver, subject, content):
    """
    发送邮件
    :param receiver: 接收者
    :param subject: 邮件主题
    :param content: 邮件内容
    :return:
    """
    # message = MIMEText(content, 'plain', 'utf-8')
    # 邮件主题
    # message['Subject'] = subject
    # 发送方
    # message['From'] = sender
    # 接收方
    # message['To'] = receiver

    # try:
        # 连接到服务器
    #     smtp = smtplib.SMTP_SSL(mail_host)
    #     # 登录服务器
    #     smtp.login(mail_user, mail_pwd)
        # 发送
    #     smtp.sendmail(sender, receiver, message.as_string())
        # 推出
    #      smtp.quit()
    #     return 'send email success ...'
    # except smtplib.SMTPException as e:
    #     print('error', e)
    return '收到参数:receiver:' + receiver


if __name__ == '__main__':
    # 邮件接受者
    receiver = 'xxx@qq.com'
    # 邮件主题
    subject = '打个招呼'
    # 邮件内容
    content = '我是犬小哈'
    sendEmail(receiver, subject, content)