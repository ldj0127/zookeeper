package org.example;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @Author Dajie
 * @create 2020/9/5 9:44
 */
public class CustomSerializer implements ZkSerializer {

    private String charset = "UTF-8";

    public CustomSerializer(){

    }

    public CustomSerializer(String charset){
        this.charset = charset;
    }
    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try{
            byte[] bytes = String.valueOf(data).getBytes(charset);
            return bytes;
        }catch (UnsupportedEncodingException e){
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String result=null;
        try {
            result = new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
        return result;
    }
}
