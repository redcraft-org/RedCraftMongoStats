package org.redcraft.redcraftmongostats.stats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.redcraft.redcraftmongostats.Config;

public class StatsSyncManager {
    private MongoCollection<Document> collection;

    private static StatsSyncManager instance = new StatsSyncManager();

    public static StatsSyncManager getInstance() {
        return instance;
    }

    public StatsSyncManager() {
        MongoDatabase database = MongoClients.create(Config.mongoUri).getDatabase(Config.mongoDatabase);
        collection = database.getCollection(Config.mongoCollection);
    }

    public void syncPlayerStats(Player player) throws IOException {
        String rawStats = getPlayerJsonContents(player, "stats");
        String rawAdvancements = getPlayerJsonContents(player, "advancements");
        mergeAndSavePlayerStats(player, rawStats, rawAdvancements);
    }

    public void mergeAndSavePlayerStats(Player player, String rawStats, String rawAdvancements) {
        Document document = new Document();

        Document stats = Document.parse(rawStats);
        Document advancements = Document.parse(rawAdvancements);

        document.put("_id", player.getUniqueId().toString());
        document.put("stats", stats);
        document.put("advancements", advancements);
        this.savePlayerStats(document);
    }

    public void savePlayerStats(Document stats) {
        Bson filter = Filters.eq("_id", stats.get("_id"));
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        collection.replaceOne(filter, stats, options);
    }

    public String getPlayerJsonContents(Player player, String category) throws IOException {
        String worldDirectories = Bukkit.getServer().getWorldContainer().getAbsolutePath();
        String mainWorld = Bukkit.getServer().getWorlds().get(0).getName();
        String playerFileName = player.getUniqueId().toString() + ".json";
        Path path = Paths.get(worldDirectories, mainWorld, category, playerFileName);
        return Files.readString(path);
    }
}
