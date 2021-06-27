package cn.shuangbofu.testmybatisdynamic.trash;

import lombok.Getter;
import org.mybatis.dynamic.sql.util.StringUtilities;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static <T> String getFieldName(PropertyFunc<T, ?> func) {
        try {
            // 通过获取对象方法，判断是否存在该方法
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            // 利用jdk的SerializedLambda 解析方法引用
            java.lang.invoke.SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String getter = serializedLambda.getImplMethodName();
            return resolveFieldName(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static String resolveFieldName(String getMethodName) {
        if (getMethodName.startsWith("get")) {
            getMethodName = getMethodName.substring(3);
        } else if (getMethodName.startsWith("is")) {
            getMethodName = getMethodName.substring(2);
        }
        // 小写第一个字母
        return firstToLowerCase(getMethodName);
    }

    public static String resolveGetter(Field field) {
        Class<?> type = field.getType();
        String suffix = "get";
        if (type.isAssignableFrom(Boolean.class) && type.isPrimitive()) {
            suffix = "is";
        }
        return StringUtilities.toCamelCase(suffix + "_" + field.getName());
    }

    private static String firstToLowerCase(String param) {
        if (param == null || param.length() == 0) {
            return "";
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }

    public static String UnderlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */
    public static String HumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//偏移量，第i个下划线的位置是 当前的位置+ 偏移量（i-1）,第一个下划线偏移量是0
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    @Getter
    class Test {
        boolean a;
        Boolean b;
    }

//    static Map<String, String> columnMappings(Class<? extends BaseModel> clazz) {
//        Field[] fields = clazz.getDeclaredFields();
//        Map<String, String> columnMappings = new HashMap<>();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            Column annotation = field.getAnnotation(Column.class);
//            String fieldName = field.getName();
//            if (annotation != null) {
//                String mappingName = annotation.name();
//                columnMappings.put(fieldName, mappingName);
//            } else {
//                columnMappings.put(fieldName, HumpToUnderline(fieldName));
//            }
//        }
//        return columnMappings;
//    }
//
//    static String getTableName(Class<? extends BaseModel> clazz) {
//        String tableName = HumpToUnderline(clazz.getSimpleName());
//        TableName annotation = clazz.getAnnotation(TableName.class);
//        if(annotation!=null) {
//            tableName = annotation.value();
//        }
//        return tableName;
//    }
}
