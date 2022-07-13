demo
    共享redis
demo
    非共享redis

    Client 端无法直连 Redis 校验 ticket，取出账号id。
    Client 端无法与 Server 端共用一套会话，需要自行维护子会话。
    由于不是一套会话，所以无法“一次注销，全端下线”，需要额外编SsoClientController 写代码完成单点注销。


模式一：采用共享 Cookie 来做到前端 Token 的共享，从而达到后端的 Session 会话共享。
模式二：采用 URL 重定向，以 ticket 码为授权中介，做到多个系统间的会话传播。
模式三：采用 Http 请求主动查询会话，做到 Client 端与 Server 端的会话同步。



http://localhost:9001

没登录，点击登录
http://localhost:9001/sso/login 调用 server【http://127.0.0.1:9000/sso/auth】
重定向到【登陆界面】
    http://127.0.0.1:9000/sso/auth?redirect=http://localhost:9001/sso/login?back=http%3A%2F%2Flocalhost%3A9001%2F

登陆
http://127.0.0.1:9000/sso/doLogin

登陆成功之后刷新
302:
    http://127.0.0.1:9000/sso/auth?redirect=http://127.0.0.1:9001/sso/login?back=http%3A%2F%2F127.0.0.1%3A9001%2F
302:
    http://127.0.0.1:9001/sso/login?back=http%3A%2F%2F127.0.0.1%3A9001%2F&ticket=udWwTToHH1doD7LmMzxfbWPL0sZOdkqiQy0aWhWRUBHQuQSZtiJ2F95PK0NYbwYN

http://localhost:9001/


http://127.0.0.1:9000/sso/checkTicket?ticket=ZDwSSe0qaOTux0mtl6Yj1AYEpAvtOFjOvG3vh4VhxIjC62hT2sI3GO6bfbHbhE9M&ssoLogoutCall=http://localhost:9001/sso/logoutCall

http://127.0.0.1:9000/sso/checkTicket