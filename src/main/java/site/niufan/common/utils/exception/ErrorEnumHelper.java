package site.niufan.common.utils.exception;

import site.niufan.common.exception.error.Error;

import java.util.Collection;

/**
 * 枚举错误接口
 * @author Fan Niu
 * @since 2018/7/17
 */
public interface ErrorEnumHelper<Code, E extends Error> {

    default E codeOf(Code code) {
        if (code == null) {
            throw new java.lang.IllegalArgumentException("Error code must be not null.");
        }
        E result = null;
        for (E temp: errors()) {
            if (temp.getCode().equals(code)) {
                result = temp;
                break;
            }
        }
        if (result == null) {
            throw new java.lang.IllegalArgumentException(String.format("Error code[%s] not match [%s].", code, Error.class));
        }
        return result;
    }

    Collection<E> errors();
}
