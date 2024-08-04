package com.ghost.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取验证码返回
 *
 * @author yaols
 */
@Data
@AllArgsConstructor
public class CaptchaResult {

    /**
     * 验证码id
     */
    private String captchaId;

    /**
     * 验证码的值
     */
    private String code;

    /**
     * 图片验证码的base64值
     */
    private String imageData;

}
