package com.langfeiyes.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class user {
    private String user_id;
    private String nickname;
    @JsonIgnore
    private String password;
    private String comment;
    public  user(String user_id,String nickname){
        this.user_id=user_id;
        this.nickname=nickname;
    }
    public  user(String user_id,String nickname,String comment){
        this.user_id=user_id;
        this.nickname=nickname;
        this.comment=comment;
    }
//    public user(String nickname,String comment){
//        this.nickname=nickname;
//        this.comment=comment;
//    }
}