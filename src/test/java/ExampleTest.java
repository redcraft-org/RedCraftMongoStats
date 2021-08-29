import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import be.seeseemelk.mockbukkit.WorldMock;
import junit.framework.*;

public class ExampleTest extends TestCase {
    World testWorld;

	protected void setUp() {
        testWorld = new WorldMock(Material.STONE, 64);
    }

    /**
     * This is a dummy test
     * it should not end up in plugin releases
     */
    public void testExample() {
        List<Chunk> expectedNearbyChunks = new ArrayList<Chunk>();

        expectedNearbyChunks.add(testWorld.getChunkAt(0, 0));

        assertEquals(expectedNearbyChunks.size(), 1);
    }
}
