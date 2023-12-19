package com.langfeiyes.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class MyRequestBody {
    String user_id;
    String password;
    String nickname;
    String comment;
    public MyRequestBody(String nickname,String comment){
        this.nickname = nickname;
        this.comment = comment;
    }
}
