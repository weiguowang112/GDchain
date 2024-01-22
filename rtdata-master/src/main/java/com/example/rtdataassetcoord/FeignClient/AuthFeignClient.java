package com.example.rtdataassetcoord.FeignClient;



import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test",url = "http://localhost:8080/api/rtdataassetcoord/auth")
public interface AuthFeignClient {

    @GetMapping({"user"})
    R<User> UserAuth();
}
