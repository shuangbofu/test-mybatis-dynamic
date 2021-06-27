package cn.shuangbofu.testmybatisdynamic.trash;

import cn.shuangbofu.testmybatisdynamic.trash.WhereBuilder;

public interface WhereHandler {
//        <R extends AbstractWhereDSL<?>,T extends Buildable<T>> {
//    QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder selectWhere(QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder);
//    UpdateDSL<UpdateModel>.UpdateWhereBuilder updateWhere(UpdateDSL<UpdateModel>.UpdateWhereBuilder builder);
//     <X extends R,T>  X where(X builder);
    WhereBuilder<?> where(WhereBuilder<?> builder);
}