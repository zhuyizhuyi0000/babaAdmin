package cn.stylefeng.guns.modular.business.exception;

import cn.stylefeng.guns.core.consts.ProjectConstants;
import cn.stylefeng.roses.kernel.rule.constants.RuleConstants;
import cn.stylefeng.roses.kernel.rule.exception.AbstractExceptionEnum;
import lombok.Getter;

/** *车辆异常枚举   */
@Getter
public enum CarExceptionEnum implements AbstractExceptionEnum {

    /** *车辆不存在   */
    CAR_NOT_EXISTED(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE + ProjectConstants.BUSINESS_EXCEPTION_STEP_CODE + "01", "车辆不存在");

    /**
     * 错误编码
     */
    private final String errorCode;

    /**
     * 提示用户信息
     */
    private final String userTip;

    CarExceptionEnum(String errorCode, String userTip) {
        this.errorCode = errorCode;
        this.userTip = userTip;
    }
}
