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
        String[] urls = new String[10];
        for (int i = 1; i <= 10; i++) {
            urls[i - 1] = "http://dy-public.oss-cn-shenzhen.aliyuncs.com/interviewTestData/" + i + ".txt";
        }

        int totalSum = sumDataFromURLs(urls);
        System.out.println("10个文件中data字段的总和为：" + totalSum);
    }


}
