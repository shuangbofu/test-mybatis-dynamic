package cn.shuangbofu.testmybatisdynamic.trash;

import java.io.Serializable;
import java.util.function.Function;

public interface PropertyFunc<T, R> extends Function<T, R>, Serializable {
 
}