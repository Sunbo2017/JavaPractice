package com.sunbo.test.lambda;

import com.sunbo.study.lambda.BufferedReaderProcessor;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author sunboyan
 * Date 2018/2/7
 * Description 函数式编程实例
 */
public class LambdaExampleTest {
    /**
     * 定义lambda表达式执行行为
     * @param p
     * @return
     * @throws IOException
     */
    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\person.txt"))){
            return p.process(br);
        }
    }

    @Test
    public void useExamples() throws IOException{
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(oneLine);
        System.out.println(twoLine);
    }
}
