package cn.itheima.consumer.web;

import cn.itheima.consumer.client.UserClient;
import cn.itheima.consumer.pojo.User;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
//@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private RibbonLoadBalancerClient client;


    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        return  userClient.queryById(id);
    }
    //    @GetMapping("{id}")
//    public User queryById(@PathVariable("id") Long id){
//        System.out.println("123");
//        // 根据服务id获取实例
//         //List<ServiceInstance> instances= discoveryClient.getInstances("user-service");
//
//        // 从实例当中去除ip和端口
////        ServiceInstance instance = instances.get(0);
////        ServiceInstance instance= client.choose("user-service");
////        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
//        String url="http://user-service/user/"+id;
//        User user=restTemplate.getForObject(url,User.class);
//        System.out.println(user);
//        return user;
//    }

//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2500")
//    })
//    @HystrixCommand(
//            commandProperties = {
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//            }
//    )
//    public String queryById(@PathVariable("id") Long id) {
//        if (id % 2 == 0) {
//            throw new RuntimeException("");
//        }
//        System.out.println("123");
//        String url = "http://user-service/user/" + id;
//        String user = restTemplate.getForObject(url, String.class);
//        System.out.println(user);
//        return user;
//    }

    public String queryByIdFallback(Long id) {
        return "不好意思，服务器太拥挤了！";
    }

    public String defaultFallback() {
        return "不好意思，服务器太拥挤了！";
    }
}
