package org.bytedeco.javacv.entity;

import java.io.Serializable;

import org.bytedeco.javacpp.opencv_core.Rect;


public class RectEntity implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -3146346822669652297L;

  private String x;
  private String y;
  private String height;
  private String width;

  /* Constructor */
  public RectEntity(Rect rect) {
    this.x = String.valueOf(rect.x());
    this.y = String.valueOf(rect.y());
    this.height = String.valueOf(rect.height());
    this.width = String.valueOf(rect.width());
  }


  /* Getter, setter */
  public void setX(String val) {
    this.x = val;
  }
  public String getX() {
    return this.x;
  }

  public void setY(String val) {
    this.y = val;
  }
  public String getY() {
    return this.y;
  }

  public void setHeight(String val) {
    this.height = val;
  }
  public String getHeight() {
    return this.height;
  }

  public void setWidth(String val) {
    this.width = val;
  }
  public String getWidth() {
    return this.width;
  }

}
