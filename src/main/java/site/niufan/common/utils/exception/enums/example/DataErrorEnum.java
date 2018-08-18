package site.niufan.common.utils.exception.enums.example;

import site.niufan.common.exception.category.DataException;
import site.niufan.common.exception.error.Error;
import site.niufan.common.exception.error.ErrorFormat;
import site.niufan.common.exception.error.category.DataError;


/**
 * @author Fan Niu
 * @since 2018/7/17
 */
public enum DataErrorEnum implements Error<String, String, DataException> {

    DATA_ERROR("1", "成功")
    ;

    private final Error<String, String, DataException> error;

    DataErrorEnum(String code, String message) {
        this.error = new DataError<>(code, message);
    }

    @Override
    public String getCode() {
        return error.getCode();
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }

    @Override
    public Class<DataException> getType() {
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
