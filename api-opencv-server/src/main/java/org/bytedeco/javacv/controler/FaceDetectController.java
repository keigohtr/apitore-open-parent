package org.bytedeco.javacv.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.entity.RectEntity;
import org.bytedeco.javacv.service.FaceDetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author Keigo Hattori
 */
@RestController
@RequestMapping(value = "/opencv")
public class FaceDetectController {

  @Autowired
  FaceDetectService faceDetectService;

  @Bean
  BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
    return new BufferedImageHttpMessageConverter();
  }

  @RequestMapping(value="/detect/faces", method=RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Object> detectFaces(@RequestParam("file") Part file) throws IOException {
    Map<String, Object> map = new HashMap<String, Object>();
    Java2DFrameConverter converter1 = new Java2DFrameConverter();
    Frame frame = converter1.convert(ImageIO.read(file.getInputStream()));
    OpenCVFrameConverter.ToMat converter2 = new OpenCVFrameConverter.ToMat();
    Mat source = converter2.convert(frame);
    RectVector faces = faceDetectService.detectFaces(source);
    long len = faces.size();
    List<RectEntity> rects = new ArrayList<RectEntity>();
    for (long i=0; i<len; i++) {
      Rect rect = faces.get(i);
      rects.add(new RectEntity(rect));
    }
    map.put("rects", rects);
    return map;
  }

}
