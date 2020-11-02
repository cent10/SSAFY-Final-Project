package com.activityx.allei.controller;

import java.util.ArrayList;
import java.util.List;

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
        logger.debug("User DB test : createUser - 호출");
        final BasicResponse result = new BasicResponse();
        if (service.joinUser(user)) {
            result.status = true;
        } else {
            result.status = false;
            result.msg = "유저 생성에 실패하였습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 수정", response = BasicResponse.class)
    @PutMapping("modify")
    public ResponseEntity<BasicResponse> modifyUser(@RequestBody User user) {
        logger.debug("User DB test : modifyUser - 호출");
        final BasicResponse result = new BasicResponse();
        if (service.modifyUser(user)) {
            result.status = true;
        } else {
            result.status = false;
            result.msg = "유저 정보 수정에 실패하였습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 삭제", response = BasicResponse.class)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<BasicResponse> deleteUser(@PathVariable int id) {
        logger.debug("User DB test : deleteUser - 호출");
        final BasicResponse result = new BasicResponse();
        if (service.deleteUser(id)) {
            result.status = true;
        } else {
            result.status = false;
            result.msg = "유저 정보 삭제에 실패하였습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "모든 유저 정보 조회", response = BasicResponse.class)
    @GetMapping("all")
    public ResponseEntity<BasicResponse> readAllUser() {
        logger.debug("User DB test : readAllUser - 호출");
        final BasicResponse result = new BasicResponse();
        List<User> data = service.findAll();
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 ID로 정보 조회", response = BasicResponse.class)
    @GetMapping("findById/{id}")
    public ResponseEntity<BasicResponse> readUserById(@PathVariable int id) {
        logger.debug("User DB test : readUserById - 호출");
        final BasicResponse result = new BasicResponse();
        User data = service.findById(id);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 Name으로 정보 조회", response = BasicResponse.class)
    @GetMapping("findByName/{name}")
    public ResponseEntity<BasicResponse> readUserByName(@PathVariable String name) {
        logger.debug("User DB test : readUserByName - 호출");
        final BasicResponse result = new BasicResponse();
        User data = service.findByName(name);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 Name으로 list 조회", response = BasicResponse.class)
    @GetMapping("findlistByName/{name}")
    public ResponseEntity<BasicResponse> readUserListByName(@PathVariable String name) {
        logger.debug("User DB test : readUserListByName - 호출");
        final BasicResponse result = new BasicResponse();
        List<User> data = service.findlistByName(name);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 Email로 정보 조회", response = BasicResponse.class)
    @GetMapping("findByEmail/{email}")
    public ResponseEntity<BasicResponse> readUserByEmail(@PathVariable String email) {
        logger.debug("User DB test : readUserByEmail - 호출");
        final BasicResponse result = new BasicResponse();
        User data = service.findByName(email);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 Email로 list 조회", response = BasicResponse.class)
    @GetMapping("findlistByEmail/{email}")
    public ResponseEntity<BasicResponse> readUserListByEmail(@PathVariable String email) {
        logger.debug("User DB test : readUserListByEmail - 호출");
        final BasicResponse result = new BasicResponse();
        List<User> data = service.findlistByName(email);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 key값으로 정보 조회", response = BasicResponse.class)
    @GetMapping("findByKey/{key}")
    public ResponseEntity<BasicResponse> readUserByKey(@PathVariable long key) {
        logger.debug("User DB test : readUserByKey - 호출");
        final BasicResponse result = new BasicResponse();
        User data = service.findByKey(key);
        if (data != null) {
            result.status = true;
            result.data = data;
        } else {
            result.status = false;
            result.msg = "유저 정보가 없습니다.";
        }
        return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

}
