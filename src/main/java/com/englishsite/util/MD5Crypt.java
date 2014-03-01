package com.englishsite.util;


/**
 * MD5加密工具类
 * @author wangkui
 *
 */
public final class MD5Crypt {

  public static void main(String argv[]){
      String salt="";
	  try {
		  RandomStr.setCharset("a-zA-Z0-9");
		  RandomStr.setLength("8");
		  RandomStr.generateRandomObject();
		  salt=RandomStr.getRandom();
	  } catch (Exception e) {
    	  e.printStackTrace();
	 }
	  System.out.println(salt);
  }
  private static final String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  private static final String itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  static public String genCryptPasswordFromMingWen(String password){
      String salt="";
	  try {
		  RandomStr.setCharset("a-zA-Z0-9");
		  RandomStr.setLength("8");
		  RandomStr.generateRandomObject();
		  salt=RandomStr.getRandom();
	  } catch (Exception e) {
    	  e.printStackTrace();
	 }
	  String result=crypt(PPTools.md5(password),salt);
	  if(result.startsWith("$1$")){
		  result=result.substring(3);
	  }
	  return result;
  }
  

  private static final String to64(long v, int size){
    StringBuffer result = new StringBuffer();
    while (--size >= 0){
    	result.append(itoa64.charAt((int) (v & 0x3f)));
    	v >>>= 6;
    }
    return result.toString();
  }

  private static final void clearbits(byte bits[]){
    for (int i = 0; i < bits.length; i++){
    	bits[i] = 0;
    }
  }

  private static final int bytes2u(byte inp){
    return (int) inp & 0xff;
  }


  public static final String crypt(String password){
    StringBuffer salt = new StringBuffer();
    java.util.Random randgen = new java.util.Random();
    while (salt.length() < 8){
      int index = (int) (randgen.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.substring(index, index+1));
    }
    return MD5Crypt.crypt(password, salt.toString());
  }

  public static final String crypt(String password, String salt){
    String magic = "$1$";
    byte finalState[];
    MD5 ctx, ctx1;
    long l;
    if (salt.startsWith(magic)){
    	salt = salt.substring(magic.length());
    }
    if (salt.indexOf('$') != -1){
    	salt = salt.substring(0, salt.indexOf('$'));
    }
    if (salt.length() > 8){
    	salt = salt.substring(0, 8);
    }
    ctx = new MD5();
    ctx.Update(password);
    ctx.Update(magic);
    ctx.Update(salt);

    ctx1 = new MD5();
    ctx1.Update(password);
    ctx1.Update(salt);
    ctx1.Update(password);
    finalState = ctx1.Final();

    for (int pl = password.length(); pl > 0; pl -= 16){
    	ctx.Update(finalState, pl > 16? 16 : pl);
    }
    clearbits(finalState);
    for (int i = password.length(); i != 0; i >>>=1){
    	if ((i & 1) != 0){
  	    ctx.Update(finalState, 1);
	    }else{ctx.Update(password.getBytes(), 1);}
    }
    finalState = ctx.Final();

    for (int i = 0; i < 1000; i++){
    	ctx1 = new MD5();
    	if ((i & 1) != 0){
  	    ctx1.Update(password);
	    }else{
  	    ctx1.Update(finalState, 16);
	    }
    	if ((i % 3) != 0) {ctx1.Update(salt);}
    	if ((i % 7) != 0) {ctx1.Update(password);}
    	if ((i & 1) != 0){
  	    ctx1.Update(finalState, 16);
	    }else{
  	    ctx1.Update(password);
	    }
	    finalState = ctx1.Final();
    }

    StringBuffer result = new StringBuffer();
    result.append(magic);
    result.append(salt);
    result.append("$");
    l = (bytes2u(finalState[0]) << 16) | (bytes2u(finalState[6]) << 8) | bytes2u(finalState[12]);
    result.append(to64(l, 4));
    l = (bytes2u(finalState[1]) << 16) | (bytes2u(finalState[7]) << 8) | bytes2u(finalState[13]);
    result.append(to64(l, 4));
    l = (bytes2u(finalState[2]) << 16) | (bytes2u(finalState[8]) << 8) | bytes2u(finalState[14]);
    result.append(to64(l, 4));
    l = (bytes2u(finalState[3]) << 16) | (bytes2u(finalState[9]) << 8) | bytes2u(finalState[15]);
    result.append(to64(l, 4));
    l = (bytes2u(finalState[4]) << 16) | (bytes2u(finalState[10]) << 8) | bytes2u(finalState[5]);
    result.append(to64(l, 4));
    l = bytes2u(finalState[11]);
    result.append(to64(l, 2));
    clearbits(finalState);
    return result.toString();
  }

}
