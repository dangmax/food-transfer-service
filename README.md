# food-transfer-service
2nd homework: food-transfer-service

1 增加了eureka和hystrix使用
2 在food-order-service服务中增加了unit-test

问题：
1 配置了rabbitMQ，但是send message有报错：dispatcher has no subscribers，还没查出来是哪儿的问题

2 make_order的页面，先通过thymeleaf的动态属性拿到了后台的对象显示到page上，但是通过form在往controller里面传值的时候，因为是传到list的对象里面，还没找到传值的方法

3 hystrix的callback方法是不是在方法成功执行的时候也会调用到？debug的时候，好像是调用到了
