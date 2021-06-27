package cn.shuangbofu.testmybatisdynamic.trash;

import org.mybatis.dynamic.sql.util.Buildable;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;

public abstract class WhereBuilder<T> extends AbstractWhereDSL<WhereBuilder<?>> implements Buildable<T> {
}
