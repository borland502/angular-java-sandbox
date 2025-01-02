package com.technohouser.watchyourlan.utils.xdg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class Paths {

  /**
   * Ensures that a path exists, creating it if necessary.
   *
   * @param path the path to ensure exists
   * @throws IOException if an error occurs creating the path
   */
  public static void ensurePath(Path path) throws IOException {
    File file = path.toFile();

    if (!file.exists()) {
      Files.createDirectories(file.toPath());
    }
  }

  /**
   * Ensures that a path exists and is owned by the current user, creating it if necessary.
   *
   * @param path the path to ensure exists and is owned by the current user
   * @throws IOException if an error occurs creating the path or setting the permissions
   */
  public static void ensureOwnerPath(Path path) throws IOException {
    ensurePath(path);
    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwx------");
    Files.setPosixFilePermissions(path, permissions);
  }
}
