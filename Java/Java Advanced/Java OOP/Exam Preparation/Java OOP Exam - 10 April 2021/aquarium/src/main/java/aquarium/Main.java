package aquarium;

import aquarium.core.ControllerImpl;
import aquarium.core.Engine;
import aquarium.core.EngineImpl;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.classes.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.classes.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.classes.FreshwaterFish;
import aquarium.entities.fish.classes.SaltwaterFish;
import aquarium.repositories.classes.DecorationRepository;


public class Main {
    public static void main(String[] args) {
        ControllerImpl controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();

//        Aquarium aquarium = new SaltwaterAquarium("Aqa");
//
//        Fish nemo = new SaltwaterFish("Nemo", "Fish", 10);
//        Fish doli = new SaltwaterFish("Dolly", "Fish", 10);
//
//        aquarium.addFish(nemo);
//        aquarium.addFish(doli);
//
//        Decoration plant = new Plant();
//        Decoration rose = new Plant();
//
//        aquarium.addDecoration(plant);
//        aquarium.addDecoration(rose);
//
//        System.out.println(aquarium.getInfo());

    }
}
