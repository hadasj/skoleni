package cz.i.dto;

import javax.validation.constraints.NotNull;

/**
 * @author jan.hadas@i.cz
 */
public class ObyvatelUpdateDto extends ObyvatelDto {

  @NotNull
  private Integer id;

  @NotNull
  private Integer version;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
