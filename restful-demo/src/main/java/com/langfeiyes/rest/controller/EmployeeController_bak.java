//package com.langfeiyes.rest.controller;
//
//import com.langfeiyes.rest.domain.user;
//import com.langfeiyes.rest.util.JsonResult;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Arrays;
//import java.util.List;
//
//
///**
// * 员工对外接口声明类
// * 员工controller层
// */
////@Controller
//public class userController_bak {
//
//
//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "ok";
//    }
//
//
//    /**
//     * 获取所有的员工
//     * 1>请求路径---确认资源---员工----/users
//     * 2>请求方法---查询--GET
//     * 3>请求参数---无
//     * 4>请求响应---多个员工---List<user>----json
//     */
//   @RequestMapping(value = "/users", method = RequestMethod.GET)
//    @ResponseBody
//    public List<user> list(){
//        //查询mysql数据库，得到员工列表信息
//        //假装查询数据库得到list集合
//        List<user> list = Arrays.asList(new user(1L, "dafei", 18), new user(2L, "xiaofei", 17));
//        return list;
//    }
//
//
//    /**
//     * 查询所有员工接口： /users    GET
//     * 查询单个员工接口： /users    GET
//     * 上面2个接口映射路径与请求方法完全相同， springmvc 认为是同一个接口，不能同时存在，此时怎么办？
//     *
//     * 方案1：使用多级路径方式   比如：  /users/detail
//     *
//     * 方案2：使用参数路径方式   比如： /users/{id}
//     *   将请求参数作为路径一部分，进行url区分。
//     *
//     *   参数路径： /users/{id}     其中 {id}   参数占位符
//     *
//     *   客户端访问：   http://localhost:80/users/1     1就是id参数值
//     *
//     *   注意点：
//     *   接口要获取参数路径中参数必须使用注释@PathVariable ，目的是让springmvc参数解析器从路径中解析出参数并进行赋值。
//     *   如果参数路径中的占位符名称与请求映射方法形式参数名称不一致时，必须明确的指定映射
//     *   "/users/{eid}" ----->@PathVariable("eid") Long id
//     *
//     *
//     */
//
//
//    /**
//     * 获取某个的员工
//     * 1>请求路径---确认资源---员工----/users
//     * 2>请求方法---查询--GET
//     * 3>请求参数---id
//     * 4>请求响应---一个员工---user----json
//     */
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public user detail(@PathVariable Long id){
//        return new user(id, "dafei", 18);
//    }
//
//
//
//
//   /* @RequestMapping(value = "/users/{id}/{name}", method = RequestMethod.GET)
//    @ResponseBody
//    public user detail(@PathVariable Long id, @PathVariable String name){
//        return new user(id, name, 18);
//    }*/
//
//
//
//
///*
//    @RequestMapping(value = "/users/{id}/{name}/{age}", method = RequestMethod.GET)
//    @ResponseBody
//    public user info(@PathVariable Long id,@PathVariable String name,@PathVariable int age){
//        return new user(id, name, age);
//    }
//
//
//
//    @RequestMapping(value = "/users/{id}/{name}/{age}", method = RequestMethod.GET)
//    @ResponseBody
//    public user info(user user){
//        return user;
//    }*/
//
//
//
//
//
//
//   /* @RequestMapping(value = "/users", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonResult list(){
//        //查询mysql数据库，得到员工列表信息
//        //假装查询数据库得到list集合
//        List<user> list = Arrays.asList(new user(1L, "dafei", 18), new user(2L, "xiaofei", 17));
//        return JsonResult.success(list);
//    }*/
//
//
//    /**
//     * 新增一个员工
//     *
//     * 1>请求路径--确认资源--员工---/users
//     * 2>请求方法--添加---POST
//     * 3>请求参数--员工相关属性--name，age
//     * 4>请求响应---新增员工对象---user----json
//     *
//     */
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    @ResponseBody
//    public user save(user user){
//        //假装添加数据成功，返回自动增长id值
//        user.setId(1L);
//        return user;
//    }
//
//    /**
//     * 更新一个员工
//     *
//     * 1>请求路径--确认资源--员工---/users
//     * 2>请求方法--更新---PUT
//     * 3>请求参数--员工相关属性--id name，age
//     * 4>请求响应---改之后员工对象---user----json
//     *
//     */
//    @RequestMapping(value = "/users", method = RequestMethod.PUT)
//    @ResponseBody
//    public user update(user user){
//        user.setName(user.getName() + "_update");
//        return user;
//    }
//
//    /**
//     * 删除一个员工
//     *
//     * 1>请求路径--确认资源--员工---/users
//     * 2>请求方法--删除---DELETE
//     * 3>请求参数--员工相关属性--id
//     * 4>请求响应---删除之后状态---JsonResult----json
//     *
//     * JsonResult： 统一的响应返回值
//     *
//     *  {
//     *      code：200,
//     *      msg:"操作成功",
//     *      data:null
//     *  }
//     *
//     *
//     */
//    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
//    @ResponseBody
//    public JsonResult delete(Long id){
//        //假装删除成功
//        return JsonResult.success();
//    }
//
//
//
//
//
//}
