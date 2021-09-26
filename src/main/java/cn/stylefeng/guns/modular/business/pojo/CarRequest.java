package cn.stylefeng.guns.modular.business.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** *车辆管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class CarRequest extends BaseRequest {
    /** * 车辆id  */
    @NotNull(message = "车辆id不能为空",groups = {edit.class,delete.class,detail.class})
    private Long carId;

    /** * 车辆名称  */
    @NotNull(message = "车辆名称不能为空",groups = {add.class,edit.class})
    private String carName;

    /** * 车辆种类  */
    @NotNull(message = "车辆种类不能为空",groups = {add.class,edit.class})
    private Integer carType;

    private String carColor;

    private BigDecimal carPrice;

    private String manufacturer;
}
