package com.springboot.user.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.entity.user.UserEntity;
import com.springboot.common.service.RedisService;
import com.springboot.common.service.UserService;
import com.springboot.common.utils.HttpResult;


@Controller
public class UserController {
	
	@Autowired
	private UserService userserviceImpl;
	
//	@Reference
//    private RedisService redisServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/api/finduser/{username}", method = RequestMethod.GET)
	public String getUserInfoByUserid(@PathVariable("username") String username) {
		List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername(username);
		String s ="";
		Iterator it = userlist.iterator();
		while(it.hasNext()) {
		  s =s+  it.next().toString()+"<br>";
		}		
		return s;	
	} 
	
	
	@ResponseBody
	@RequestMapping("/api/adduser")
	public String addUser() {
		UserEntity user = new UserEntity();
		user.setUserid("009");
		user.setUsername("zhangsan");	
		userserviceImpl.addUser(user);
        List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername("");
		String s ="";
		Iterator it = userlist.iterator();
		while(it.hasNext()) {
		  s =s+  it.next().toString()+"<br>";
		}
		return s;	
	}
	

    @CrossOrigin
	@ResponseBody
	@RequestMapping(value="/api/register", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> register(@RequestBody Map registerInfo) {
		System.out.println("username_register:"+registerInfo.get("username")+":::password_register:"+registerInfo.get("password"));
		UserEntity user = new UserEntity();
		user.setUsername((String)registerInfo.get("username"));
		user.setPassword((String)registerInfo.get("password"));	
		userserviceImpl.addUser(user);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		return map;
		//return new ResponseEntity<>("66", HttpStatus.OK);		
//	    JSONObject jsonObj = JSONObject.getObject(user);	
//		redisServiceImpl.set("user",user);
//		return redisServiceImpl.get("user").toString();		
	} 
	
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/api/deleteUser/{username}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> deleteUser(@PathVariable String username) {
		System.out.println("username_deleteUser:"+username);
		userserviceImpl.deleteUserByUsername(username);
		List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userlist", userlist);
		return map;		
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/api/userlist", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getUserlist() {
		List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userlist", userlist);
		return map;		
	}
	
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/api/checkUsernameExist/{username}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> checkUsernameExist(@PathVariable String username) {
		System.out.println("username_checkUsernameExist:"+username);
		List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername(username);
    	String status="0";
		if(userlist.size()>0){
			status="1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		return map;		
	}
    
    
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="/api/login", produces = { "application/json;charset=UTF-8" })
    public Map<String, Object> doLogin(
    		@RequestParam("username") String username_input,
            @RequestParam("password") String password_input) {
    	System.out.println("username_doLogin:"+username_input+",password_doLogin:"+password_input);
    	List<UserEntity> userlist = userserviceImpl.findUserInfoByUsername(username_input);
    	String status="0";
		Iterator it = userlist.iterator();
		if(userlist.size()>0) {
			if("".equals(((UserEntity) userlist.get(0)).getPassword())
				||((UserEntity) userlist.get(0)).getPassword()==null
				||password_input.equals(((UserEntity) userlist.get(0)).getPassword())) {
				System.out.println(((UserEntity) userlist.get(0)).toString()+"::::"+((UserEntity) userlist.get(0)).getPassword());
				status="1";
			}
		}
    	//return new ResponseEntity<>("66", HttpStatus.OK);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("status", status);
    	return map;	
    }
    
    
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="/user/findUser", produces = { "application/json;charset=UTF-8" })
    public Map<String, Object> findUser(@RequestBody Map userInfo) {
    	System.out.println("username_findUser:"+userInfo.get("username")+":::password_findUser:"+userInfo.get("password"));
    	UserEntity user = new UserEntity();
		user.setUsername((String)userInfo.get("username"));
		user.setPassword((String)userInfo.get("password"));	
		List<UserEntity> userlist = userserviceImpl.findUser(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userlist", userlist);
		return map;	
    	
    }
    
    @CrossOrigin
	@ResponseBody
	@RequestMapping(value="/user/update", produces = { "application/json;charset=UTF-8" })
	public HttpResult updateUser(@RequestBody Map userInfo) {
		System.out.println("username_updateUser:"+userInfo.get("username")+":::password_updateUser:"+userInfo.get("password"));
		
		UserEntity user = new UserEntity();
		user.setUsername((String)userInfo.get("username"));
		user.setPassword((String)userInfo.get("password"));	
				
		return HttpResult.ok(userserviceImpl.updateUser(user));
	} 
    
    

}
