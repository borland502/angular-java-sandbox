package com.technohouser.watchyourlan.utils.xdg;

import static com.technohouser.watchyourlan.utils.xdg.Paths.ensureOwnerPath;
import static com.technohouser.watchyourlan.utils.xdg.XdgBaseDirs.XdgPropertyDefaults.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * This class mirrors part of @code{dev.dirs.BaseDirs} for Linux, and will use the XDG Spec on both
 * windows and MacOS. This behavior still works on Windows in Powershell, but not the Command Prompt
 * as PowerShell translates '\' to '/'. In particular this class exists within Spring and makes use
 * of the 15 or so ways that properties are resolved.
 *
 * @see <a href="https://docs.spring.io/spring-boot/reference/features/external-config.html">Spring
 *     External Config</a>
 * @see <a href="https://specifications.freedesktop.org/basedir-spec/basedir-spec-latest.html">XDG
 *     Base Directory Specification</a>
 * @see <a href="https://github.com/dirs-dev/directories-jvm">directories-jvm</a>
 */
@Log4j2
@Component
public class XdgBaseDirs implements EnvironmentAware {

  @Getter
  public enum XdgPropertyDefaults {
    XDG_CONFIG_HOME("XDG_CONFIG_HOME", ".config"),
    XDG_DATA_HOME("XDG_DATA_HOME", ".local/share"),
    XDG_CACHE_HOME("XDG_CACHE_HOME", ".cache"),
    XDG_RUNTIME_DIR("XDG_RUNTIME_DIR", null),
    XDG_CONFIG_DIRS("XDG_CONFIG_DIRS", "/etc/xdg"),
    XDG_BIN_HOME("XDG_BIN_HOME", ".local/bin"),
    XDG_LIB_HOME("XDG_LIB_HOME", ".local/lib"),
    XDG_STATE_HOME("XDG_STATE_HOME", ".local/state");

    private final String propertyName;
    private final String defaultValue;

    XdgPropertyDefaults(String propertyName, String defaultValue) {
      this.propertyName = propertyName;
      this.defaultValue = defaultValue;
    }
  }

  /** The Spring Environment already parses properties from */
  private Environment environment;

  @Bean
  public Resource xdgConfigHome() throws IOException {
    return xdgPath(XDG_CONFIG_HOME);
  }

  @Bean
  public Resource xdgDataHome() throws IOException {
    return xdgPath(XDG_DATA_HOME);
  }

  @Bean
  public Resource xdgCacheHome() throws IOException {
    return xdgPath(XDG_CACHE_HOME);
  }

  @Bean
  public Resource xdgRuntimeDir() throws IOException {
    return xdgPath(XDG_RUNTIME_DIR);
  }

  @Bean
  public Resource xdgConfigDirs() throws IOException {
    return xdgPath(XDG_CONFIG_DIRS);
  }

  @Bean
  public Resource xdgBinHome() throws IOException {
    return xdgPath(XDG_BIN_HOME);
  }

  @Bean
  public Resource xdgLibHome() throws IOException {
    return xdgPath(XDG_LIB_HOME);
  }

  @Bean
  public Resource xdgStateHome() throws IOException {
    return xdgPath(XDG_STATE_HOME);
  }

  private Resource xdgPath(XdgPropertyDefaults property) throws IOException {

    String xdgEnvProp = environment.getProperty(property.getPropertyName());

    if (Objects.isNull(xdgEnvProp)) {
      String userHome = environment.getRequiredProperty("user.home");
      log.warn(
          "The property {} is not set, using the default value of {}",
          property.getPropertyName(),
          property.getDefaultValue());
      Path xdgHome = Path.of(userHome, property.getDefaultValue());
      ensureOwnerPath(xdgHome);
      return new PathResource(xdgHome);
    }

    // Return the path unaltered if the environment variable is set
    return new PathResource(xdgEnvProp);
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
