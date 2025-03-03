package org.springapp.model;


public class HelloWorld {
  private String version;
  public HelloWorld (){
    this.version = "1.0";
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getMESSAGE() {
      return "HELLO WORLD!";
  }

}
