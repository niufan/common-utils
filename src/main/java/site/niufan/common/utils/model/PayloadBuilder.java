package site.niufan.common.utils.model;

import site.niufan.common.exception.category.Failure;
import site.niufan.common.exception.category.Success;
import site.niufan.common.exception.message.Message;
import site.niufan.common.exception.message.enums.DefaultIntegerFailureMessage;
import site.niufan.common.exception.message.enums.DefaultIntegerSuccessMessage;
import site.niufan.common.exception.message.enums.DefaultStringFailureMessage;
import site.niufan.common.exception.message.enums.DefaultStringSuccessMessage;
import site.niufan.common.utils.function.*;

/**
 * @author Fan Niu
 * @since 2018/8/20
 */
public class PayloadBuilder<C, M, D, S, F>  {

    private final site.niufan.common.exception.message.Message<C, M, S> success;
    private final site.niufan.common.exception.message.Message<C, M, F> failure;

    private PayloadBuilder(Message<C, M, S> success, Message<C, M, F> failure) {
        this.success = success;
        this.failure = failure;
    }

    public static <DT> PayloadBuilder<String, String, DT, Success, Failure> string() {
        return builder(DefaultStringSuccessMessage.SUCCESS, DefaultStringFailureMessage.FAILURE);
    }

    public static <DT> PayloadBuilder<Integer, String, DT, Success, Failure> integer() {
        return builder(DefaultIntegerSuccessMessage.SUCCESS, DefaultIntegerFailureMessage.FAILURE);
    }

    public static <CT, MT, DT, ST, FT> PayloadBuilder<CT, MT, DT, ST, FT> builder(Message<CT, MT, ST> success, Message<CT, MT, FT> failure) {
        return new PayloadBuilder<>(success, failure);
    }

    public Payload<C, M, D> success() {
        return success((D) null);
    }

    public Payload<C, M, D> success(Function0<D> function) {
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

    public Payload<C, M, D> success(VoidFunction0 function) {
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

    public Payload<C, M, D> build(Message<C, M, ?> message) {
        return build(message.getCode(), message.getMessage());
    }

    public Payload<C, M, D> build(Message<C, M, ?> message, D data) {
        return build(message.getCode(), message.getMessage(), data);
    }

    public Payload<C, M, D> build(C code, M message) {
        return build(code, message, null);
    }

    public Payload<C, M, D> build(C code, M message, D data) {
        return new Payload<>(code, message, data);
    }

    public Message<C, M, S> getSuccess() {
        return success;
    }

    public Message<C, M, F> getFailure() {
        return failure;
    }


}
