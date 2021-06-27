package cn.shuangbofu.testmybatisdynamic.dao.base.annotation;

import cn.shuangbofu.testmybatisdynamic.dao.base.BaseMapper;
import cn.shuangbofu.testmybatisdynamic.dao.base.table.BaseTable;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Dao {
    Class<? extends BaseTable> table();

    Class<? extends BaseMapper<?>> mapper();
}
