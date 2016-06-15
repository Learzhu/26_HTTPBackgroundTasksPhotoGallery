package com.learzhu.photogallery.a26_httpbackgroundtasksphotogallery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Learzhu
 * @version $Rev$
 * @time 2016/6/15 23:48
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$  23:48
 * @updateDes ${TODO}
 */
public class FlickrFetchr {
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while ((byteRead = inputStream.read()) > 0) {
                out.write(buffer, 0, byteRead);
            }
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
        }
    }

    public String getUrl(String uplSpec) throws IOException {
        return new String(getUrlBytes(uplSpec));
    }
}
