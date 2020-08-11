package com.gxs.springcloud.Controller;

import com.gxs.springcloud.entities.DeptEntity;
import com.gxs.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GongXings
 * @createTime 30 15:07
 * @description
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/findById/{deptNo}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hystrix_findById")
    //HystrixCommand是对单个方法处理熔断，如果统一处理可以用自定义的方法实现Fallbackfactory<DeptClientService>接口
    //服务熔断处理代码在服务端，服务降级处理代码在客户端
    public DeptEntity findById(@PathVariable("deptNo") Long deptNo) {
        DeptEntity deptEntity = deptService.findById(deptNo);
        if(null==deptEntity){
            throw new RuntimeException("未找到deptNo对应信息");
        }
        return  deptEntity;
    }

    public DeptEntity hystrix_findById(Long deptNo) {
        return  new DeptEntity().setDeptNo(deptNo).setDeptName("未找到该deptNo对应信息")
                 .setDbSource("Mysql中无此数据");
    }







//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
//    public boolean addDept(@RequestBody DeptEntity deptEntity) {
//        return   deptService.addDept(deptEntity);
//    }
//    @RequestMapping(value = "/dept/findById/{deptNo}",method = RequestMethod.GET)
//    public DeptEntity findById(Long deptNo) {
//        return  deptService.findById(deptNo);
//    }
//
//    @RequestMapping(value = "/dept/findAll",method = RequestMethod.GET)
//    public List<DeptEntity> findAll() {
//        return deptService.findAll();
//    }
//
//    /**
//     * 增加自己服务描述的接口
//     * @return
//     */
//    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
//    public Object discovery(){
//        List<String> list = discoveryClient.getServices();
//        System.out.println("总共有多少个微服务"+list.size());
//
//        //查询STUDY-SPRINGCLOUD-DEPT 服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("STUDY-SPRINGCLOUD-DEPT");
//
//        //打印STUDY-SPRINGCLOUD-DEPT服务信息
//        for (ServiceInstance element :instances){
//            System.out.println(element.getServiceId());
//            System.out.println(element.getHost());
//            System.out.println(element.getPort());
//            System.out.println(element.getUri());
//        }
//        return this.discoveryClient;
//
//    }

}