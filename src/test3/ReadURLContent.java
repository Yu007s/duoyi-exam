package test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取URL内容（串行化版本）
 */
public class ReadURLContent {
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
     * 从一组URL中读取包含特定格式数据的字段，并返回这些字段的总和
     * @param urls 要读取的URL数组
     * @return URL内容中指定字段的总和
     */
    public static int sumDataFromURLs(String[] urls) {
        int sum = 0;
        Pattern pattern = Pattern.compile("data\\s*:\\s*(\\d+)");

        for (String url : urls) {
            String urlContent = readURL(url);
            Matcher matcher = pattern.matcher(urlContent);
            if (matcher.find()) {
                sum += Integer.parseInt(matcher.group(1));
            }
        }

        return sum;
    }


    public static void main(String[] args) {
        // 构建URL数组
        String[] urls = new String[10];
        for (int i = 1; i <= 10; i++) {
            urls[i - 1] = "http://dy-public.oss-cn-shenzhen.aliyuncs.com/interviewTestData/" + i + ".txt";
        }

        // 计算URL内容中data字段的总和
        int totalSum = sumDataFromURLs(urls);
        System.out.println("10个文件中data字段的总和为：" + totalSum);
    }
}
