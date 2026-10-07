public class ImportCardScreen extends Screen {
    
    private final ItemStack stack;
    private ImportCardConfig currentConfig;
    
    public ImportCardScreen(ItemStack stack) {
        super(Component.translatable("ae2helpers.importcard.screen.title"));
        this.stack = stack;
        this.currentConfig = stack.getOrDefault(ae2helpers.IMPORT_CARD_CONFIG.get(), ImportCardConfig.DEFAULT);
    }
    
    @Override
    protected void init() {
        super.init();
        
        var centerX = this.width / 2;
        var startY = this.height / 2 - 40;
        
        var resultsTooltip = Tooltip.create(Component.translatable("ae2helpers.importcard.resultsonly.tooltip"));
        
        var resultsBox = Checkbox.builder(Component.translatable("ae2helpers.importcard.resultsonly"), font)
                           .pos(centerX - 80, startY)
                           .selected(currentConfig.resultsOnly())
                           .tooltip(resultsTooltip)
                           .onValueChange((box, val) -> updateConfig(val, currentConfig.syncToGrid(), currentConfig.overriddenDirection()))
                           .build();
        this.addRenderableWidget(resultsBox);
        
        var syncTooltip = Tooltip.create(Component.translatable("ae2helpers.importcard.sync.tooltip"));
        
        var syncBox = Checkbox.builder(Component.translatable("ae2helpers.importcard.sync"), font)
                        .pos(centerX - 80, startY + 25)
                        .selected(currentConfig.syncToGrid())
                        .tooltip(syncTooltip)
                        .onValueChange((box, val) -> updateConfig(currentConfig.resultsOnly(), val, currentConfig.overriddenDirection()))
                        .build();
        this.addRenderableWidget(syncBox);
        
        var dirTooltip = Tooltip.create(Component.translatable("ae2helpers.importcard.direction.tooltip"));
        
        var options = new ArrayList<Optional<Direction>>();
        options.add(Optional.empty());
        options.addAll(Arrays.stream(Direction.values()).map(Optional::of).toList());
        
        var dirButton = CycleButton.<Optional<Direction>>builder(opt -> getDirectionName(opt.orElse(null)))
                          .withValues(options)
                          .withTooltip(val -> dirTooltip)
                          .withInitialValue(Optional.ofNullable(currentConfig.overriddenDirection()))
                          .create(centerX - 80, startY + 50, 200, 20, Component.translatable("ae2helpers.importcard.direction"),
                            (btn, val) -> updateConfig(currentConfig.resultsOnly(), currentConfig.syncToGrid(), val.orElse(null)));
        
        this.addRenderableWidget(dirButton);
    }
    
    private Component getDirectionName(Direction dir) {
        if (dir == null) return Component.translatable("ae2helpers.importcard.direction.auto");
        return Component.literal(dir.getName().substring(0, 1).toUpperCase() + dir.getName().substring(1));
    }
    
    // ...existing code...
}
