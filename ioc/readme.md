
- @Autowired是根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Qualifier。另外可以使用其它注解，@ Resource ：等同于@Qualifier，@Inject：等同于@ Autowired。

- @Service用于注解业务层组件（我们通常定义的service层就用这个）

- @Controller用于注解控制层组件（如struts中的action）

- @Repository用于注解数据访问组件，即DAO组件

- @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行注解。

#### 装配注解主要有：@Autowired、@Qualifier、@Resource，它们的特点是：
> 1、@Resource默认是按照名称来装配注入的，只有当找不到与名称匹配的bean才会按照类型来装配注入；

> 2、@Autowired默认是按照类型装配注入的，如果想按照名称来转配注入，则需要结合@Qualifier一起使用；

> 3、@Resource注解是又J2EE提供，而@Autowired是由spring提供，故减少系统对spring的依赖建议使用@Resource的方式；如果Maven项目是1.5的JRE则需换成更高版本的。

> 4、@Resource和@Autowired都可以书写注解在字段或者该字段的setter方法之上

> 5、@Autowired 可以对成员变量、方法以及构造函数进行注释，而 @Qualifier 的注解对象是成员变量、方法入参、构造函数入参。

> 6、@Qualifier("XXX") 中的 XX是 Bean 的名称，所以 @Autowired 和 @Qualifier 结合使用时，自动注入的策略就从 byType 转变成 byName 了。

> 7、@Autowired 注释进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个，通过属性required可以设置非必要。

> 8、@Resource装配顺序

```markdown

    8.1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
    
    8.2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
    
    8.3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
    
    8.4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；   




