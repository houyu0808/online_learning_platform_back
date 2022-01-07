package com.houyu.online_learning_platform.utils.loginStatus;

public class LoginStatusUtils {

    public static LoginDTO loginResponse(String status){
        LoginDTO loginDTO = new LoginDTO();
        switch (status){
            case "1":
                loginDTO.setCode(500);
                loginDTO.setMessage("密码错误!请重新输入!");
                return  loginDTO;
            case "2":
                loginDTO.setCode(500);
                loginDTO.setMessage("用户名不存在!请重新输入!");
                return  loginDTO;
            default:
                loginDTO.setCode(200);
                loginDTO.setResult(status);
                loginDTO.setMessage("登陆成功");
                return  loginDTO;
        }
    }
}
