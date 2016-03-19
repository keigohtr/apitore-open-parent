package org.bytedeco.javacv.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


/**
 * @author Keigo Hattori
 */
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FaceDetectService {
  @Value("${classifierFile:classpath:/haarcascade_frontalface_default.xml}")
  File classifierFile;

  CascadeClassifier classifier;

  public RectVector detectFaces(Mat source) {
    RectVector faces = new RectVector();
    classifier.detectMultiScale(source, faces);
    return faces;
  }

  @PostConstruct
  void init() throws IOException {
    // 分類器の読み込み
    this.classifier = new CascadeClassifier(classifierFile.toPath().toString());
  }
}
