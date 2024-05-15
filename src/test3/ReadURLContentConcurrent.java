package test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取URL内容（并行化版本）
 */
public class ReadURLContentConcurrent {
    /**
     * 从给定的URL中读取文本内容并返回
     * @param urlString 要读取的URL字符串
     * @return URL内容的字符串表示形式
     */
    public static String readURL(String urlString) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    /**
     * 并行从多个URL中读取数据并计算特定字段的总和
     * @param urls 要读取的URL数组
     * @return URL内容中指定字段的总和
     */
    public static int sumDataFromURLs(String[] urls) {
        AtomicInteger sum = new AtomicInteger(0);
        Pattern pattern = Pattern.compile("data\\s*:\\s*(\\d+)");
        ExecutorService executor = Executors.newFixedThreadPool(urls.length);

        for (String url : urls) {
            executor.submit(() -> {
                String urlContent = readURL(url);
                Matcher matcher = pattern.matcher(urlContent);
                if (matcher.find()) {
                    sum.addAndGet(Integer.parseInt(matcher.group(1)));
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // 等待所有线程执行完毕
        }

        return sum.get();
    }

    public static void main(String[] args) {
        // 构建URL数组
        String[] urls = new String[10];
        for (int i = 1; i <= 10; i++) {
            urls[i - 1] = "http://dy-public.oss-cn-shenzhen.aliyuncs.com/interviewTestData/" + i + ".txt";
        }

        // 计算URL内容中data字段的总和（并行化）
        int totalSum = sumDataFromURLs(urls);
        System.out.println("10个文件中data字段的总和为：" + totalSum);
    }
}
