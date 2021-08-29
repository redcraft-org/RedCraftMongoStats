package org.redcraft.redcraftplugintemplate.models.database;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "rct_player_preferences") // Change table name prefix
public class PlayerPreferences extends DatabaseModel {

  @Id
  @Column(name = "id", unique = true)
  public UUID uuid;

  // Add fields if necessary

  public PlayerPreferences(UUID uuid) {
    this.uuid = uuid;
  }
}
