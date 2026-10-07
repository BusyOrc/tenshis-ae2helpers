package rearth.ae2helpers;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_AUTO_IMPORT = BUILDER
            .comment("Whether the auto import of scheduled crafting results is enabled. Also configurable via crafting menu UI.")
            .define("enableAutoImport", true);
    
    static final ModConfigSpec SPEC = BUILDER.build();

}
