package platinpython.rgbblocks.data;

import java.util.Collections;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGatherer {
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = new ExistingFileHelper(Collections.emptyList(), Collections.emptySet(), false);

		if (event.includeClient()) {
			generator.addProvider(new ModLanguageProvider(generator));
			generator.addProvider(new ModItemModelProvider(generator, helper));
			generator.addProvider(new ModBlockStateProvider(generator, helper));
		}
		if (event.includeServer()) {
			generator.addProvider(new ModRecipeProvider(generator));
			generator.addProvider(new ModLootTableProvider(generator));
		}
	}
}
