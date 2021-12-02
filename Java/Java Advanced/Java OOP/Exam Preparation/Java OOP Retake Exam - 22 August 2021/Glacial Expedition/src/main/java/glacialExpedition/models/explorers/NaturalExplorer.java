package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double initialEnergy = 60;

    public NaturalExplorer(String name) {
        super(name, initialEnergy);
    }

    @Override
    public void search() {
        if (this.getEnergy() -  15 < 0) {
            this.setEnergy(0);
        } else {
            this.setEnergy(this.getEnergy() - 15);
        }
    }
}
