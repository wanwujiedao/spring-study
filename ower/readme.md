# spring 实现机制

***

## Spring框架的事件机制

- 事件本身（生产者） 

- 消费事件者

- 事件管理（订阅中心）

> Spring 事件本身从 ApplicationEvent 派生，事件消费者为 ApplicationListener<T extends ApplicationEvent>，事件管理中心为 ApplicationEventMulticaster 它负责管理监听者等。

> Spring当广播一个事件时，它首先去查找该事件的监听者，然后再去遍历监听者调用其 onApplicationEvent(Application evnet) 接口，将事件传给监听者。

> 最后当我们调用 getBean（）的时候，实际上经过 refresh()的 bean 注册，已经被缓存到 map 里面，直接出 map 里面取出实例化即可。

