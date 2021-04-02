package com.example.sentinel.common.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.sentinel.common.CommonResult;

/**
 * @ClassName: CustomBlockHandler
 * @Description: 自定义限流处理
 * @Author: yongchen
 * @Date: 2021/4/2 14:57
 **/
public class CustomBlockHandler {
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(null, "自定义限流信息", 200);
    }
}
