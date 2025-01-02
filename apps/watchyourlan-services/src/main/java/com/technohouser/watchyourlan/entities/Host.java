package com.technohouser.watchyourlan.entities;

import com.technohouser.watchyourlan.entities.converter.StringToDateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

/** Entity class representing a Host on the network. */
@Setter
@Getter
@Entity
@Table(name = "now", schema = "main")
public class Host {

  /** Unique identifier for the Host. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false, unique = true)
  private Integer id;

  /** Name of the Host. Cannot be blank. */
  @Column(name = "NAME", nullable = false)
  @NotBlank
  private String name;

  /** IP address of the Host. Must follow the IPv4 format. */
  @Column(name = "IP")
  @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")
  private String ip;

  /** MAC address of the Host. Must follow the MAC address format. */
  @Column(name = "MAC")
  @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
  private String mac;

  /** Hardware information of the Host. */
  @Column(name = "HW")
  private String hw;

  /** Date associated with the Host. */
  @Column(name = "DATE")
  @Convert(converter = StringToDateConverter.class)
  private Date date;

  /** Indicates if the Host is known. */
  @Column(name = "KNOWN", columnDefinition = "INTEGER default 0")
  @Convert(converter = NumericBooleanConverter.class)
  private Boolean known;

  /** Indicates if the Host is currently active. */
  @Column(name = "NOW", columnDefinition = "INTEGER default 0")
  @Convert(converter = NumericBooleanConverter.class)
  private Boolean now;
}
