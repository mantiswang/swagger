package cn.net.leadu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ywang on 2017/6/13.
 */
@Api(tags = {
    "hello 接口"
}, produces = "application/json")
@RestController
@RequestMapping("hello")
public class HelloController {

  @ApiOperation(value = "获取helloWorld", notes = "简单请求")
  @RequestMapping(value = "/", method = RequestMethod.GET)
  String home() {
    return "HELLO WORLD";
  }

  @ApiOperation(value = "获得A+B", notes = "根据url的firstName和url的lastName获得请求参数的字符串相加，RestFul风格的请求")
  @ApiImplicitParams(
      {
          @ApiImplicitParam(name = "firstName", value = "姓", required = true, dataType = "String", paramType = "path"),
          @ApiImplicitParam(name = "lastName", value = "名", required = true, dataType = "String", paramType = "path")
      }
      )
  @RequestMapping(value = "/{firstName}/to/{lastName}", method = RequestMethod.GET)
  String append(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
    return firstName + "  " + lastName;
  }
}
