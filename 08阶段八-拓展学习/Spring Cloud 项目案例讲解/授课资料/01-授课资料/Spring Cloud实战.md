# Spring Cloud 实战

# 简介

为初次接触Spring Cloud的同学准备的系列教程，从应用场景、编码实战等方面介绍其中的各个组件。
Spring Cloud是一套完整的微服务解决方案，是一系列不同功能的微服务框架的集合。
Spring Cloud基于Spring Boot，简化了分布式系统的开发，集成了服务发现、配置管理、消息总线、负载均衡、断路器、数据监控等各种服务治理能力。比如sleuth提供了全链路追踪能力，Netflix套件提供了hystrix熔断器、zuul网关等众多的治理组件。config组件提供了动态配置能力，bus组件支持使用RabbitMQ、kafka、Activemq等消息队列，实现分布式服务之间的事件通信。

![Spring Cloud高可用架构](https://upload-images.jianshu.io/upload_images/7528671-e61706cbe083c51b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 基础组件1-Eureka server
这个是微服务的通讯录(注册中心)，既然是微服务，那么在调用别的微服务的时候肯定需要其他微服务的地址、端口等信息，而这些信息都有Eureka Server来管理。
而Eureka Server的启动比较简单，作为一个Spring boot类型的项目来启动。
对于习惯了Dubbo开发的同学来说，在使用Spring Cloud时遇到的第一个不习惯的地方就是，注册中心Eureka不是一个像Zookeeper那样独立运行的中间件，而是可以用Springboot来启动运行，有点类似于嵌入式的tomcat，这是Spring Cloud体系的一大特点，除了注册中心还有网关Zuul也是类似的启动方式。

## 基本的server
1.  引入pom文件
```xml
    	<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
```
2. 增加启动类注解
```java
@EnableEurekaServer
@SpringBootApplication
public class PlatformEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatformEurekaApplication.class, args);
    }
}
```
3. 配置yml文件
- 默认yml
```yml
server:
  port: 7001 #(eureka 默认端口为：8761)

spring:
  application:
    name: platform-eureka #服务名

eureka:
  instance:
    #服务失效时间，Eureka多长时间没收到服务的renew操作，就剔除该服务，默认90秒
    leaseExpirationDurationInSeconds: 15
    ip-address: ${spring.cloud.client.ip-address}
    hostname: ${eureka.instance.ip-address}
    instanceId: ${eureka.instance.ip-address}:${server.port}
    preferIpAddress: true     #将IP注册到Eureka Server上
  client:
    #是否注册自身到eureka服务器，因为当前这个应用就是eureka服务器，没必要注册自身，所以这里是false
    registerWithEureka: false
    fetchRegistry: false      #表示是否从eureka服务器获取注册信息
    serviceUrl:
      #是设置eureka服务器所在的地址，查询服务和注册服务都需要依赖这个地址（注意：地址最后面的 /eureka/ 这个是固定值）
      defaultZone: http://${eureka.instance.ip-address}:${server.port}/eureka/
  server:
    #设为false，关闭自我保护，开发测试环境需要频繁启动注册实例，需要关闭自我保护功能，以免请求跑到旧实例中，生成环境需要开启自我保护功能
    enableSelfPreservation: false
    #eureka server清理无效节点的时间间隔，默认60000毫秒，即60秒
    eviction-interval-timer-in-ms: 5000
    # 续期时间，即扫描失效服务的间隔时间（缺省为60*1000ms）
    eureka.server.evictionIntervalTimerInMs: 20000
```
- dev.yml
```yml
eureka:
  instance:
    ip-address: 127.0.0.1
```

## 增加监控 

​	可以对每个服务增加安全监控，利用形如http://localhost:7001/Actuator 的访问方式进行访问。

1. 修改pom
```    xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
2. 修改yml
```yml
# Spring Cloud应用监控与管理Actuator
management:
  endpoints:
    enabled-by-default: true
    web:
      exposure:
        include: "*"
```
## 增加安全认证
1. 引入安全模块的pom
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```
2. 修改yml文件
```yml
spring:
    security:
      basic:
        enabled: true # 开启基于HTTP basic的认证
      user:
        name: admin  # 配置登录的账号是admin
        password: 123456 #配置登录的密码是 123456
```

3. 增加一个java类配置登录模式

```java
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 关闭csrf
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // 开启认证
    }
}
```



# 基础组件2-服务提供者

服务提供者要作为Eureka的客户端在注册中心注册为服务提供者，这里重点是注册自己的名字和服务地址。
1. 引入pom
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```
2. 默认yml
```yml
server:
  port: ${server.port}

spring:
  application:
    name: platform-user

eureka:
  instance:
    hostname: ${eureka.instance.ip-address}
    instanceId: ${eureka.instance.ip-address}:${server.port}
    prefer-ip-address: true
    registry.default-open-for-traffic-count: ${eureka.instance.registry.default-open-for-traffic-count}
    registry.expected-number-of-renews-per-min: ${eureka.instance.registry.expected-number-of-renews-per-min}
  client:
    enabled: true
    serviceUrl:
      defaultZone: ${eureka.client.serviceUrl.defaultZone}

```
3. dev.yml
```yml
server:
  port: 7081

spring:
  profiles: dev 
eureka:
  instance:
    ip-address: 127.0.0.1
    registry.default-open-for-traffic-count: 1                          
    registry.expected-number-of-renews-per-min: 1     
  client:
    serviceUrl:
      defaultZone: http://admin:123456@127.0.0.1:7001/eureka/

management:
  endpoints:
    enabled-by-default: true
    web:
      exposure:
        include: "*"
```
4. 增加注解
```java
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.itcast.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
```
注意:
>从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient 可省略。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。

# 基础组件3-服务消费者
通过注册中心查找自己需要的服务地址，就想提供名字查电话一样。
与服务提供者的编写方式基本一致。为了后面增加网关支持的方便，这里面的服务消费者本身也是服务提供者。

# 基础组件4-Feign
在消费者中使用，简化Http API的调用，使消费者调用服务提供者就想调用本地接口一样方便。
Spring Cloud对原生Feign进行了整合。

1. 引入pom
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```
2. 增加注解

  ```Java
  @EnableFeignClients
  ```

3. 按照服务提供者对应服务协议编写对应接口(不用实现)
```java
@FeignClient(name = "PLATFORM-USER")
public interface UserService {
    /***
     * 用户信息查询
     */
    @GetMapping(value = "/user/search")
    public String searchUser(@RequestParam("userName") String userName);
}
```
4. 像本地方法一样调用
```java
    @Autowired
    private UserService userService;

   // 业务代码
   strJson = userService.searchUser(orderInfo.getUserName());

```
# 基础组件5-Hystrix
Feign中已经支持Hystrix，当服务提供者出现故障时，Hystrix会自动切换到备胎方案。
1. 增加fallback参数

```java
@FeignClient(name = "PLATFORM-USER", fallback = UserServiceFallbackImpl.class)
```



2. 实现备胎代码

```java
@Component
//@Service
public class UserServiceFallbackImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceFallbackImpl.class);

    @Override
    public String searchUser(String userName){
        LOGGER.error("用户信息查询接口调用异常:searchUser");
        return JsonUtils.toText(ResponseUtils.failure("调用用户信息查询接口服务异常！"));
    }
}
```
# 基础组件6-Zuul
## 内容简介
[Zuul](https://github.com/Netflix/zuul)网关的功能和工作机制、结合代码介绍如何使用Zuul构建一个简单的网关、介绍Zuul的路由配置方式、了解Filter工作原理并实现一些扩展功能。

## Zuul网关简介
Zuul是Spring Cloud全家桶中的微服务API网关。
所有从设备或网站来的请求都会经过Zuul到达后端的Netflix应用程序。作为一个边界性质的应用程序，Zuul提供了动态路由、监控、弹性负载和安全功能。Zuul底层利用各种filter实现如下功能：

- 认证和安全 识别每个需要认证的资源，拒绝不符合要求的请求。
- 性能监测 在服务边界追踪并统计数据，提供精确的生产视图。
- 动态路由 根据需要将请求动态路由到后端集群。
- 压力测试 逐渐增加对集群的流量以了解其性能。
- 负载卸载 预先为每种类型的请求分配容量，当请求超过容量时自动丢弃。
- 静态资源处理 直接在边界返回某些响应。

## 编写一个Zuul网关
1、新建一个zuul-demo模块，在依赖项处添加【Cloud Discovery->Eureka Discovery和Cloud Rouing->Zuul】。

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
```



2、修改入口类，增加EnableZuulProxy注解
```java
@SpringBootApplication
@EnableZuulProxy
public class ZuulDemoApplication { 
    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication .class, args);
    }
}
```
3. 修改appliation.yml
```xml
server:
  port: 7000
spring:
  application:
    name: zuul-demo
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
  instance:
    prefer-ip-address: true  
    #将IP注册到Eureka Server上，而如果不配置就是机器的主机名。
```
4、启动Eureka Server、orderservice和 Zuul，在浏览器中输入http://localhost:7000/orderservice/order/pay 获取返回结果。

从上面的例子中的地址可以看出来默认Zuul的路由方式是：http://ZUULHOST:ZUULPORT/serviceId/**。

如果启动多个orderservice可以发现Zuul里面还内置了Ribbon的负载均衡功能。

## Filter工作原理
### Zuul中的Filter
Zuul是围绕一系列Filter展开的，这些Filter在整个HTTP请求过程中执行一连串的操作。
Zuul Filter有以下几个特征：
Type：用以表示路由过程中的阶段（内置包含PRE、ROUTING、POST和ERROR）
Execution Order：表示相同Type的Filter的执行顺序
Criteria：执行条件
Action：执行体

### Zuul请求生命周期
一图胜千言，下面通过官方的一张图来了解Zuul请求的生命周期。
![image.png](https://upload-images.jianshu.io/upload_images/7528671-9b45589b3ad4469e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 自定义一个Filter实现token验证
1. 添加一个AuthZuulFilter 
```java
public class AuthZuulFilter extends ZuulFilter {
    // 日志输出器
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthZuulFilter.class);

    @Value("${server.port}")
    private String serverPort;

    //四种类型：pre,routing,error,post
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    //控制过滤器生效不生效，可以在里面写一串逻辑来控制
    @Override
    public boolean shouldFilter() {        
        return true;
    }

    //执行过滤逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //从请求头中获取token信息
        String userToken = request.getHeader("userToken");
        LOGGER.debug("网关端口：" + serverPort);
        if (StringUtils.isEmpty(userToken)) {
            //设置为false就不会继续执行服务代码
            ctx.setSendZuulResponse(false);
            //设置状态码
            ctx.setResponseStatusCode(401);
            //设置相应信息
            ctx.setResponseBody("userToken is null");
            return null;
        }
        return null;
    }
}
```
2. 修改启动程序，添加Filter注入：
```java
    @Bean
    public AuthZuulFilter authZuulFilter() {
        AuthZuulFilter filter = new AuthZuulFilter();
        return filter;
    }
```
## 路由配置
Zuul提供了一套简单且强大路由配置策略，利用路由配置我们可以完成对微服务和URL更精确的控制
1. 重写指定微服务的访问路径：
```yml
zuul:
  routes:
    platform-order: /orderservice/**
```
这表示将rest-demo微服务的地址映射到/rest/**路径。
2. 忽略指定微服务：
```yml
zuul:
  ignored-services: xxx-service
```
使用“*”可忽略所有微服务，多个指定微服务以半角逗号分隔。

3、忽略所有微服务，只路由指定微服务：

```yml
zuul:
  ignored-services: *
  routes:
    platform-order: /orderservice/**
```
4、路由别名：

```yml
zuul:
  routes:
    platform-order: #路由别名，无其他意义，与例1效果一致
      service-id: platform-order
      path: /orderservice/**
```
5、指定path和URL

```yml
zuul:
  routes:
    platform-order:
      url: http://localhost:8000/
      path: /orderservice/** 
```
此例将http://ZUULHOST:ZUULPORT/rest/映射到http://localhost:8000/，同时由于并非用service-id定位服务，所以也无法使用负载均衡功能。

## 限流

```yml
#服务限流
zuul:
  ratelimit:
    enabled: true
    repository: REDIS         #使用redis存储，一定要大写
    policies:
      platform-order:         #针对platform-order服务限流
       limit: 10              #多少个请求（次数）
       refreshInterval: 60     #测试客户端如果60秒内请求超过10次，服务端就抛出429异常（秒）
       type:
         - ORIGIN             #可选 限流方式: url通过请求路径区分  origin通过客户端IP地址区分  user是通过登录用户名进行区分，也包括匿名用户

```

