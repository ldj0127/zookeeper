package org.example;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @Author Dajie
 * @create 2020/9/5 9:59
 */
public class UserSerializer implements ZkSerializer {
    private String charset = "UTF-8";

    public UserSerializer(){}

    public UserSerializer(String charset){
        this.charset = charset;
    }

    /**
     * 序列化
     * @param data user
     * @return
     * @throws ZkMarshallingError
     */
    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        String jsonString = JSON.toJSONString(data);
        System.out.println("jsonString="+jsonString);
        try{
            byte[] bytes = String.valueOf(jsonString).getBytes(charset);
            return bytes;
        }catch (UnsupportedEncodingException e){
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws ZkMarshallingError
     */
    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        User user=null;
        try {
            String   result = new String(bytes,charset);
            System.out.println(result);
            user = JSON.parseObject(result, User.class);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
        return user;
    }
}
