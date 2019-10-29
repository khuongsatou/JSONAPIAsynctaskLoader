package com.nvk.jsonapi;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class NetWork {
    //gõ: php -S 0.0.0.0:8000 mới dùng được IP dưới
    //public static final String BASE = "http://10.0.3.2:8000/api/";//genny
    //public static final String BASE = "http://10.0.2.2:8000/api/";//android
    //Cho thiết bị thật
    public static final String BASE = "http://192.168.1.11:8000/public/api/";//phone

    public static String connect(String uri,String method){
        //ghép uri : http://yourdommain.com/api/...
        Uri.Builder uriBuiltUpon = Uri.parse(BASE+uri).buildUpon();
        //get tưng link có ?id=1 ,hay get JSON theo id
        //uriBuiltUpon.appendQueryParameter("id","1");
        //xây uri
        Uri uriBuilt = uriBuiltUpon.build();
        //khởi tạo toàn cục để final đóng kết nối
        String json = null;
        HttpURLConnection connection = null;
        try {
            //lấy link đã xây tạo ra 1 URL như thanh search google http://
            URL url = new URL(uriBuilt.toString());
            //mở kết nối
            connection = (HttpURLConnection) url.openConnection();
            //sét truyền POST(khi nhấn vào button) hoặc GET(url)
            connection.setRequestMethod(method);
            //kết nối , nếu url không tìm thấy thì lỗi ở đây thì xem lại url
            connection.connect();

            //từ kết nối thì "đọc nội dung" file cụ thể là nội dung web page
            InputStream inputStream = connection.getInputStream();
            //giải mã các kí tự "\anckdlonro/dfd" -> "android"
            InputStreamReader reader = new InputStreamReader(inputStream);
            //để đọc nhanh hơn
            BufferedReader bufferedReader= new BufferedReader(reader);
            //như string như nó tốt hon
            StringBuilder builder = new StringBuilder();
            String line = null;
            //đọc từng dòng
            while ((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            json = builder.toString();
            //đóng lại
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return json;
    }

}
