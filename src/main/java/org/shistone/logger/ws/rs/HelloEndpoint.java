package org.shistone.logger.ws.rs;

import org.shistone.logger.configuration.annotation.MyLogger;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author François Robert
 */
@RestController("hello")
public class HelloEndpoint {

  @MyLogger
  private Logger logger;

  @MyLogger(name = "access")
  private Logger accessLogger;

  @GetMapping
  public String greeting(
      HttpServletRequest httpServletRequest
      , @RequestParam(defaultValue = "cher visiteur") String name
  ) {
    logger.info("paramètre name : " + name);
    accessLogger.info(httpServletRequest.getRemoteHost() + "[" + httpServletRequest.getUserPrincipal() + "]");
    return "Bonjour " + name;
  }
}
