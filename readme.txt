这个类同上一章中出现的Netty简易封装服务器代码类似，不一样的是这里使用了多个ChannelHandler，在这里一一介绍：

HttpRequestDecoder，用于解码request
HttpResponseEncoder，用于编码response
aggregator，消息聚合器（重要）。为什么能有FullHttpRequest这个东西，就是因为有他，HttpObjectAggregator，如果没有他，就不会有那个消息是FullHttpRequest的那段Channel，同样也不会有FullHttpResponse。
如果我们将z'h
HttpObjectAggregator(512 * 1024)的参数含义是消息合并的数据大小，如此代表聚合的消息内容长度不超过512kb。
添加我们自己的处理接口
 