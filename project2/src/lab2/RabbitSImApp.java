package lab2;

import plotter.SimulationPlotter;

public class RabbitSImApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulationPlotter plotter = new SimulationPlotter();
		RabbitModel myModel = new RabbitModel();
		plotter.simulate(myModel);
	}

}
