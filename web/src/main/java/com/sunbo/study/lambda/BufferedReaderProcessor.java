package com.sunbo.study.lambda;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author sunboyan
 * Date 2018/2/7
 * Description 函数式接口
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    /**
     * 读取文件方法
     * @param br
     * @return
     * @throws IOException
     */
    String process(BufferedReader br) throws IOException;
}
