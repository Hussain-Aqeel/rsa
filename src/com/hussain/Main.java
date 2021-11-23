package com.hussain;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RSA rsa = new RSA(
            BigInteger.valueOf(48527L),
            BigInteger.valueOf(2981573674497294413L));

        String secret = "hey this is the encrypted " +
            "message" +
            " message 78993";

        String encrypted = rsa.encryption(secret);

        System.out.println("the encryption is: " + encrypted);

        System.out.println("the decryption is:" + rsa.decryption(encrypted));
    }


}
