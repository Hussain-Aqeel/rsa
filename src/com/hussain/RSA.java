package com.hussain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import static com.hussain.ECM.src.LenstrasFactorization.lenstraFactoriz;

public class RSA {
  private final ListsInit lists = new ListsInit();
  private final Base64.Encoder encoder = Base64.getEncoder();
  private final Base64.Decoder decoder = Base64.getDecoder();

  /*
   p is the first prime
   q is the second prime
   n = p * q
   phi is Euler's phi function Φ(n)
   e is the public key (an acronym for encryption)
   d is the private key (an acronym for decryption)
   */
  private final BigInteger n;
  private final BigInteger e;
  private final BigInteger d;

  public RSA(BigInteger e, BigInteger n) {
    this.n = n;
    this.e = e;
    BigInteger p = lenstraFactoriz(n, 200, 100);
    BigInteger q = n.divide(p);
    BigInteger phi = p.subtract(BigInteger.valueOf(1))
        .multiply(q.subtract(BigInteger.valueOf(1)));
    this.d = e.modInverse(phi);
  }

  public String encryption(String message){
    byte[] bytesOfMessage = getBytesOfString(message);
    encryptEachCharacterInAList(bytesOfMessage,
                                lists.getListOfEncryptedChars());

    getByteArraysForEachEncryptedChar(lists.getListToEncodeToBase64(),
                                      lists.getListOfEncryptedChars());
    return encodeToBase64(lists.getListToEncodeToBase64());
  }

  // we attain the ASCII code of each character in the
  // message
  private static byte[] getBytesOfString(String string) {
    return string.getBytes();
  }

  // we use the encrypt method on each individual
  // character
  private void encryptEachCharacterInAList(byte[] bytesOfMessage, ArrayList<BigInteger> listOfEncryptedChars) {
    for (byte byteCode: bytesOfMessage) {
      listOfEncryptedChars
          .add(encrypt(BigInteger
              .valueOf(byteCode), this.e, this.n));
    }
  }

  // a new list to store the arrays of bytes to each
  // Encrypted value to encode it to base64
  private void getByteArraysForEachEncryptedChar(ArrayList<byte[]> listOfByteArrays,
                                                 ArrayList<BigInteger> encryptedChars){
    for (BigInteger listOfEncryptedChar : encryptedChars) {
      listOfByteArrays
          .add(listOfEncryptedChar.toByteArray());
    }
  }

  private String encodeToBase64(ArrayList<byte[]> charactersToEncode) {
    String encodedMessage = "";
    for (byte[] character: charactersToEncode) {
      encodedMessage += encoder.encodeToString(character);
      lists.getValuesIn64base().add(encoder.encodeToString(character));
    }
    return encodedMessage;
  }

  public String decryption(String secret) {
    ArrayList<byte[]> bytesOfChars = getBytesOf64BaseChars();
    decode64Chars(bytesOfChars);
    getEachCharAndStoreToList(lists.getDecoded64BaseCharacters());
    return decryptedChars();
  }

  private String decryptedChars(){
    String decryptedChars = "";
    for (Character c: lists.getCharsOfTheMessage()) {
      decryptedChars += c;
    }
    return decryptedChars;
  }

  private void getEachCharAndStoreToList(ArrayList<BigInteger> decodedChars){
    for (BigInteger value: decodedChars) {
      lists.getCharsOfTheMessage().add((char) decrypt(value
          , d, n).intValueExact());
    }
  }

  private void decode64Chars(ArrayList<byte[]> bytesOfChars){
    for (byte[] element: bytesOfChars) {
      lists.getDecoded64BaseCharacters().add(new BigInteger(element));
    }
  }

  private ArrayList<byte[]> getBytesOf64BaseChars() {
    for (String value : lists.getValuesIn64base()) {
      lists.getByteValueOf64BaseCharacters().add(decoder.decode(value.getBytes()));
    }
    return lists.getByteValueOf64BaseCharacters();
  }

  static BigInteger encrypt(BigInteger secret,
                            BigInteger publicKey, BigInteger n){
    return secret.modPow(publicKey, n);
  }

  // OVERRIDE
  static BigInteger decrypt(BigInteger secret,
                            BigInteger privateKey,
                            BigInteger n){
    return secret.modPow(privateKey, n);
  }

}
