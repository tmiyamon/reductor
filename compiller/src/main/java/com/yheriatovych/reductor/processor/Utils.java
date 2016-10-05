package com.yheriatovych.reductor.processor;

import com.google.auto.common.MoreTypes;
import com.yheriatovych.reductor.Reducer;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    interface Func1<T, R> {
        R call(T value);
    }

    public static DeclaredType getReducerSuperInterface(DeclaredType reducerType) {
        List<? extends TypeMirror> supertypes = MoreTypes.asTypeElement(reducerType).getInterfaces();

        for (TypeMirror supertype : supertypes) {
            boolean isReducer = MoreTypes.isTypeOf(Reducer.class, supertype);
            if (isReducer) {
                return MoreTypes.asDeclared(supertype);
            }
        }
        return null;
    }

    public static String join(String delimiter, List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            String part = strings.get(i);
            if (i != 0) stringBuilder.append(delimiter);
            stringBuilder.append(part);
        }
        return stringBuilder.toString();
    }

    public static <T, R> List<R> map(List<T> list, Func1<T, R> function) {
        List<R> result = new ArrayList<R>(list.size());
        for (T t : list) {
            result.add(function.call(t));
        }
        return result;
    }
}