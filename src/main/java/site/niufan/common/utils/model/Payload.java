package site.niufan.common.utils.model;

import site.niufan.common.exception.error.AbstractError;
import site.niufan.common.exception.error.Error;
import site.niufan.common.utils.function.*;
import site.niufan.common.utils.json.JsonUtils;

/**
 * Rest 请求，通用返回体
 * @author Fan Niu
 * @since 2018/8/8
 */
public class Payload<Code, Message, Data> {

    private Code code;
    private Message message;
    private Data data;

    private Payload(Code code, Message message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public static class PayloadBuilder<C, M, D>  {

        private static final String STRING_SUCCESS_CODE = "1";
        private static final Integer INTEGER_SUCCESS_CODE = 1;
        private static final String SUCCESS_MESSAGE = "成功";
        private static final String STRING_FAILURE_CODE = "0";
        private static final Integer INTEGER_FAILURE_CODE = 0;
        private static final String FAILURE_MESSAGE = "失败";

        private final site.niufan.common.exception.error.Error<C, M, D> success;
        private final site.niufan.common.exception.error.Error<C, M, D> failure;

        public PayloadBuilder(Error<C, M, D> success, Error<C, M, D> failure) {
            this.success = success;
            this.failure = failure;
        }

        public static <DT> PayloadBuilder<String, String, DT> string() {
            return builder(new AbstractError<String, String, DT>(STRING_SUCCESS_CODE, SUCCESS_MESSAGE) {}
                    , new AbstractError<String, String, DT>(STRING_FAILURE_CODE, FAILURE_MESSAGE) {});
        }

        public static <DT> PayloadBuilder<String, String, DT> string(Class<DT> dtClass) {
            return builder(new AbstractError<String, String, DT>(STRING_SUCCESS_CODE, SUCCESS_MESSAGE) {}
                    , new AbstractError<String, String, DT>(STRING_FAILURE_CODE, FAILURE_MESSAGE) {});
        }

        public static <DT> PayloadBuilder<Integer, String, DT> integer() {
            return builder(new AbstractError<Integer, String, DT>(INTEGER_SUCCESS_CODE, SUCCESS_MESSAGE) {}
                    , new AbstractError<Integer, String, DT>(INTEGER_FAILURE_CODE, FAILURE_MESSAGE) {});
        }

        public static <CT, MT, DT> PayloadBuilder<CT, MT, DT> builder(Error<CT, MT, DT> success, Error<CT, MT, DT> failure) {
            return new PayloadBuilder<>(success, failure);
        }

        public Payload<C, M, D> success() {
            return success((D) null);
        }

        public <P> Payload<C, M, D> success(Function0<D> function) {
            return success(function.apply());
        }

        public <P> Payload<C, M, D> success(Function1<P, D> function, P argument) {
            return success(function.apply(argument));
        }

        public <P, P2> Payload<C, M, D> success(Function2<P, P2, D> function, P p, P2 p2) {
            return success(function.apply(p, p2));
        }

        public <P, P2, P3> Payload<C, M, D> success(Function3<P, P2, P3, D> function, P p, P2 p2, P3 p3) {
            return success(function.apply(p, p2, p3));
        }

        public <P, P2, P3, P4> Payload<C, M, D> success(Function4<P, P2, P3, P4, D> function, P p, P2 p2, P3 p3, P4 p4) {
            return success(function.apply(p, p2, p3, p4));
        }

        public <P> Payload<C, M, D> success(VoidFunction0 function) {
            function.apply();
            return success();
        }

        public <P> Payload<C, M, D> success(VoidFunction1<P> function, P p) {
            function.apply(p);
            return success();
        }

        public <P, P2> Payload<C, M, D> success(VoidFunction2<P, P2> function, P p, P2 p2) {
            function.apply(p, p2);
            return success();
        }

        public <P, P2, P3> Payload<C, M, D> success(VoidFunction3<P, P2, P3> function, P p, P2 p2, P3 p3) {
            function.apply(p, p2, p3);
            return success();
        }

        public <P, P2, P3, P4> Payload<C, M, D> success(VoidFunction4<P, P2, P3, P4> function, P p, P2 p2, P3 p3, P4 p4) {
            function.apply(p, p2, p3, p4);
            return success();
        }

        public Payload<C, M, D> success(D data) {
            return build(success, data);
        }

        public Payload<C, M, D> failure() {
            return failure(null);
        }

        public Payload<C, M, D> failure(D data) {
            return build(failure, data);
        }

        public Payload<C, M, D> build(site.niufan.common.exception.error.Error<C, M, D> error) {
            return build(error.getCode(), error.getMessage());
        }

        public Payload<C, M, D> build(site.niufan.common.exception.error.Error<C, M, D> error, D data) {
            return build(error.getCode(), error.getMessage(), data);
        }

        public Payload<C, M, D> build(C code, M message) {
            return build(code, message, null);
        }

        public Payload<C, M, D> build(C code, M message, D data) {
            return new Payload<>(code, message, data);
        }

        public Error<C, M, D> getSuccess() {
            return success;
        }

        public Error<C, M, D> getFailure() {
            return failure;
        }
    }
}
