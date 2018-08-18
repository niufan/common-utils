package site.niufan.common.utils.function;

/**
 * @author Fan Niu
 * @since 2018/8/18
 */
@FunctionalInterface
public interface Function1<T1, R> {

    R apply(T1 t1);
}
