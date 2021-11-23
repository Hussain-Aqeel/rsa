package com.hussain;

import java.math.BigInteger;
import java.util.ArrayList;

public class ListsInit {
  private final ArrayList<BigInteger> listOfEncryptedChars;
  private final ArrayList<byte[]> listToEncodeToBase64;
  private final ArrayList<String> valuesIn64base;
  private final ArrayList<byte[]> byteValueOf64BaseCharacters;
  private final ArrayList<BigInteger> decoded64BaseCharacters;
  private final ArrayList<Character> charsOfTheMessage;

  public ListsInit() {
    listOfEncryptedChars = new ArrayList<>();
    listToEncodeToBase64 = new ArrayList<>();
    valuesIn64base = new ArrayList<>();
    byteValueOf64BaseCharacters = new ArrayList<>();
    decoded64BaseCharacters = new ArrayList<>();
    charsOfTheMessage = new ArrayList<>();
  }

  public ArrayList<BigInteger> getListOfEncryptedChars() {
    return listOfEncryptedChars;
  }

  public ArrayList<byte[]> getListToEncodeToBase64() {
    return listToEncodeToBase64;
  }

  public ArrayList<String> getValuesIn64base() {
    return valuesIn64base;
  }

  public ArrayList<byte[]> getByteValueOf64BaseCharacters() {
    return byteValueOf64BaseCharacters;
  }

  public ArrayList<BigInteger> getDecoded64BaseCharacters() {
    return decoded64BaseCharacters;
  }

  public ArrayList<Character> getCharsOfTheMessage() {
    return charsOfTheMessage;
  }
}
