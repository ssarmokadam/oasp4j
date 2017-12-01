package io.oasp.module.service.common.impl.header;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import io.oasp.module.service.common.api.config.ServiceConfig;
import io.oasp.module.service.common.api.header.ServiceHeaderContext;
import io.oasp.module.service.common.api.header.ServiceHeaderCustomizer;

/**
 * @author ssarmoka
 *
 */
public class ServiceHeaderCustomizerJwt implements ServiceHeaderCustomizer {

  /**
   *
   * The constructor.
   */
  public ServiceHeaderCustomizerJwt() {
    super();
  }

  @Override
  public void addHeaders(ServiceHeaderContext<?> context) {

    String auth = context.getConfig().getChildValue(ServiceConfig.KEY_SEGMENT_AUTH);
    if (!"jwt".equals(auth)) {
      return;
    }
    SecurityContext securityContext = SecurityContextHolder.getContext();
    if (securityContext == null) {
      return;
    }
    // Authentication authentication = securityContext.getAuthentication();
    // if (authentication == null) {
    // return;
    // }
    // Object details = authentication.getDetails();
    // if (!(details instanceof Map)) {
    // return;
    // }
    // Map<?, ?> map = (Map<?, ?>) details;
    // Object authorizationHeader = map.get("Authorization");
    // if (authorizationHeader == null) {
    // return;
    // }
    // context.setHeader("Authorization", authorizationHeader + "");

    String authorizationHeader = context.getConfig().getChildValue("Authorization");
    String contentType = context.getConfig().getChildValue("Content-Type");
    context.setHeader("Authorization", authorizationHeader + "");
    context.setHeader("Content-Type", contentType);
  }

}
