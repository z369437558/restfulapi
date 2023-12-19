package com.langfeiyes.rest.controller;

import com.langfeiyes.rest.domain.MyRequestBody;
import com.langfeiyes.rest.domain.recipe;
import com.langfeiyes.rest.domain.user;
import com.langfeiyes.rest.util.JsonResult;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController  // @Controller  + @ResponseBody
@RequestMapping()
public class userController {
    List<user> list=new ArrayList<user>(){{
        add(new user("TaroYamada","たろー","PaSSwd4TY","僕は元気です"));
    }};
    @RequestMapping(path="/users/{user_id}", method=RequestMethod.GET)
    @ResponseBody
    public JsonResult list(@RequestHeader(value = "Authorization", required = false) String authorization ,@PathVariable String user_id){
        user user1=null;
        byte[] base64Data = Base64.getDecoder().decode(authorization);
        String str = new String(base64Data, StandardCharsets.UTF_8);
        String header_user_id = str.split(":")[0];
        String header_password = str.split(":")[1];
        boolean flag=false;

        for (int index = list.size()-1;index>=0;index--){
            user user = list.get(index);
            if (user.getUser_id().equals(user_id)){
                if (!user.getPassword().equals(header_password)){
                    return new JsonResult(401,"Authentication Failed",null,null,null);
                }

                user1 = user;
                if (user1.getNickname()==null){
                    user1.setNickname(user_id);
                }
                flag=true;
                break;
            }
        }
        if (flag){
            return new JsonResult(200,"User details by user_id",user1,null);
        }
        else{
            return new JsonResult(404,"No User found",null,null);
        }

    }
    @RequestMapping(path="/signup", method=RequestMethod.POST)
    @ResponseBody

    public JsonResult signup(@RequestBody MyRequestBody requestBody){
        String user_id = requestBody.getUser_id();
        String password = requestBody.getPassword();
        String nickname = requestBody.getNickname();
        String comment = requestBody.getComment();
        if (user_id==null || password==null){
            return new JsonResult(400,"Account creation failed",null,"required user_id and password");
        }
        if (user_id.length()<6 || user_id.length()>20){
            return new JsonResult(400,"Account creation failed",null,"user_id's length should be 6-20");
        }
        if (password.length()<8 || password.length()>20){
            return new JsonResult(400,"Account creation failed",null,"password's length should be 8-20");
        }
        if (!user_id.matches("^[a-zA-Z0-9]+$")){
            return new JsonResult(400,"Account creation failed",null,"user_id's pattern is wrong");
        }
//        Pattern pattern = Pattern.compile("^[\\p{Alnum}\\p{Punct}]+$");
//        Matcher matcher = pattern.matcher(user_id);
        if (!password.matches("^[!-~]+$")){
            return new JsonResult(400,"Account creation failed",null,"password's pattern is wrong");
        }
        for (int index = list.size()-1;index>=0;index--){
            user user = list.get(index);
            if (user.getUser_id().equals(user_id)){
                return new JsonResult(400,"Account creation failed",null,"already same user_id is used");
            }
        }
        user user = new user(user_id,nickname,password,comment);
        list.add(user);
        if (nickname==null){
            nickname = user_id;
        }
        user user1 = new user(user_id,nickname);
        return new JsonResult(200,"Account successfully created",user1,null);
//        return new user(user_id,nickname,password,comment);
    }
    @PatchMapping("/users/{user_id}")
    public JsonResult update(@RequestHeader(value = "Authorization", required = false) String authorization , @RequestBody MyRequestBody requestBody, @PathVariable String user_id){
//        user.setName(user.getName() + "_update");
        byte[] base64Data = Base64.getDecoder().decode(authorization);
        String str = new String(base64Data, StandardCharsets.UTF_8);
        String header_user_id = str.split(":")[0];
        String header_password = str.split(":")[1];
        user user1=null;
        boolean flag=false;
        for (int index = list.size()-1;index>=0;index--){
            user user = list.get(index);
            if (user.getUser_id().equals(user_id)){
                if (!user.getPassword().equals(header_password)){
                    return new JsonResult(401,"Authentication Failed",null,null,null);
                }
                if (requestBody.getNickname()!=null && requestBody.getNickname().isEmpty()){
                    user.setNickname(user_id);
                }
                else {
                    user.setNickname(requestBody.getNickname());
                }
                if (requestBody.getComment()!=null && requestBody.getComment().isEmpty()){
                    user.setComment("");
                }
                else {
                    user.setComment(requestBody.getComment());
                }
                user1 = user;
                flag=true;
                break;
            }
        }
        if (!user_id.equals(header_user_id) ){
            return new JsonResult(403,"No Permission for Update",null,null,null);
        }
        if (requestBody.getNickname()==null && requestBody.getComment()==null){
            return new JsonResult(400,"User updation failed",null,"required nickname or comment");
        }
        if (requestBody.getNickname()!=null && requestBody.getNickname().length()>30){
            return new JsonResult(400,"User updation failed",null,"Nickname's length should within 30");
        }
        if (requestBody.getComment()!=null && requestBody.getComment().length()>100){
            return new JsonResult(400,"User updation failed",null,"comment's length should within 100");
        }
        if (flag){
            return new JsonResult(200,"User Successfully updated",null,null,new recipe(user1.getNickname(),user1.getComment()));
        }
        else{
            return new JsonResult(404,"No user found",null,null,null);
        }
    }
    @PostMapping("/close")
    public JsonResult delete(@RequestHeader(value = "Authorization", required = false) String authorization){
        boolean flag = false;
        byte[] base64Data = Base64.getDecoder().decode(authorization);
        String str = new String(base64Data, StandardCharsets.UTF_8);
        String header_user_id = str.split(":")[0];
        String header_password = str.split(":")[1];
        for (int index = list.size()-1;index>=0;index--){
            user user = list.get(index);
            if (user.getUser_id().equals(header_user_id)){
                if (!user.getPassword().equals(header_password)){
                    return new JsonResult(401,"Authentication Failed",null,null,null);
                }
                list.remove(index);
                flag=true;
                break;
            }
        }
        if (flag){
            return new JsonResult(200,"Account and user successfully deleted",null,null);
        }
        else{
            return new JsonResult(404,"account not found",null,null);
        }
    }
//    @GetMapping("/test")
//    public user hello(){
//        return new user("123", "dafei", "18","1212");
//    }
}

