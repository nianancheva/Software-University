package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{
    private static final double initialEnergy = 100;

    public AnimalExplorer(String name) {
        super(name, initialEnergy);
    }
}
