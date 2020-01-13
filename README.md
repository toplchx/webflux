## Webflux什么时候用和怎么用
###一、同步的网络链接方式
传统的网络链接方式是同步的方式，也就是阻塞的方式。
```sequence
title: 同步链接时序
participant 客户端
participant 服务端
participant 业务处理
客户端->服务端: 请求
Note left of 客户端: 客户端等待
服务端->业务处理: 调用处理
业务处理->服务端: 处理返回结果
服务端->客户端: 返回结果

```