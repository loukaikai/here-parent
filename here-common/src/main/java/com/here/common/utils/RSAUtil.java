package com.here.common.utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName RSAUtil.java
 * @Description TODO
 * @createTime 2023年03月12日 11:20:00
 */
public class RSAUtil {

    public static boolean verify(String data, byte[] publicKeyBytes, String sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());

        byte[] signBytes = Base64.getDecoder().decode(sign);
        return signature.verify(signBytes);
    }
}
