package site.niufan.common.utils.exception.enums.example;

import site.niufan.common.exception.category.BusinessException;
import site.niufan.common.exception.error.Error;
import site.niufan.common.exception.error.ErrorFormat;
import site.niufan.common.exception.error.category.BusinessError;

/**
 * @author Fan Niu
 * @since 2018/7/20
 */
public enum BusinessErrorEnum implements Error<Integer, String, BusinessException> {

    BUSINESS_ERROR(1, "成功")
    ;

    private final Error<Integer, String, BusinessException> error;

    BusinessErrorEnum(Integer code, String message) {
        this.error = new BusinessError<>(code, message);
    }

    @Override
    public Integer getCode() {
        return error.getCode();
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }

    @Override
    public Class<BusinessException> getType() {
        return error.getType();
    }

    @Override
    public boolean equals(Error error) {
        return this.error.equals(error);
    }

    @Override
    public String toString() {
        return ErrorFormat.format(this);
    }

}
