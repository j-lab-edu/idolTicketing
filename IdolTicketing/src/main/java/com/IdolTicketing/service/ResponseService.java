package com.IdolTicketing.service;

import com.IdolTicketing.response.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
