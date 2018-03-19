###### 切点函数可以定位到准确的横切逻辑位置，在前面的示例中我们只使用过execution(* com.zhangguo.Spring052.aop02.Math.*(..))，execution就是一个切点函数，但该函数只什么方法一级，如果我们要织入的范围是类或某个注解则execution就不那么好用了，其实一共有9个切点函数，有不同的针对性。

###### @AspectJ使用AspectJ专门的切点表达式描述切面，Spring所支持的AspectJ表达式可分为四类:

> 方法切点函数：通过描述目标类方法信息定义连接点。

> 方法参数切点函数：通过描述目标类方法入参信息定义连接点。

> 目标类切点函数：通过描述目标类类型信息定义连接点。

> 代理类切点函数：通过描述代理类信息定义连接点。

###### 常见的AspectJ表达式函数：
- execution()：满足匹配模式字符串的所有目标类方法的连接点
- @annotation()：任何标注了指定注解的目标方法链接点
- args()：目标类方法运行时参数的类型指定连接点
- @args()：目标类方法参数中是否有指定特定注解的连接点
- within()：匹配指定的包的所有连接点
- target()：匹配指定目标类的所有方法
- @within()：匹配目标对象拥有指定注解的类的所有方法
- @target()：匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解
- this()：匹配当前AOP代理对象类型的所有执行方法

###### 最常用的是：execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)切点函数，可以满足多数需求。


### 常用注解配置
```markdown

      //切点
      @Pointcut("execution(* com.dao.aop.proxy.Animals.*(..))")
      public void pointcut(){
      }
      
      //前置通知
      @Before("pointcut()")
      public void before(JoinPoint jp){
          System.out.println(jp.getSignature().getName());
          System.out.println("----------前置通知----------");
      }
      
      //最终通知
      @After("pointcut()")
      public void after(JoinPoint jp){
          System.out.println("----------最终通知----------");
      }
      
      //环绕通知
      @Around("execution(* execution(* com.dao.aop.proxy.Animals.*(..))")
      public Object around(ProceedingJoinPoint pjp) throws Throwable{
          System.out.println(pjp.getSignature().getName());
          System.out.println("----------环绕前置----------");
          Object result=pjp.proceed();
          System.out.println("----------环绕后置----------");
          return result;
      }
      
      //返回结果通知
      @AfterReturning(pointcut="execution(* com.dao.aop.proxy.Animals.*(..))",returning="result")
      public void afterReturning(JoinPoint jp,Object result){
          System.out.println(jp.getSignature().getName());
          System.out.println("结果是："+result);
          System.out.println("----------返回结果----------");
      }
      
      //异常后通知
      @AfterThrowing(pointcut="execution(* com.dao.aop.proxy.Animals.*(..))",throwing="exp")
      public void afterThrowing(JoinPoint jp,Exception exp){
          System.out.println(jp.getSignature().getName());
          System.out.println("异常消息："+exp.getMessage());
          System.out.println("----------异常通知----------");
      }

```
