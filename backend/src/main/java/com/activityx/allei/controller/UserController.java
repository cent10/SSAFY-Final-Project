package com.activityx.allei.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.TipDto;
import com.activityx.allei.dto.User;
import com.activityx.allei.service.TipService;
import com.activityx.allei.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @ApiOperation(value = "유저 생성", response = BasicResponse.class)
    @PostMapping("create")
    public ResponseEntity<BasicResponse> createUser(@RequestBody User user) {
        logger.debug("User DB test ; createUser - 호출");
        final BasicResponse result = new BasicResponse();
        if(service.joinUser(user)){
            result.status = true;
        }else{
            result.status = false;
            result.msg = "유저 생성에 실패하였습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }
    
}
