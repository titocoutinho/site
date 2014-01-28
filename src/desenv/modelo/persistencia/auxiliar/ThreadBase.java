package desenv.modelo.persistencia.auxiliar;

public class ThreadBase implements Runnable {

	@Override
	public void run() {
		ParametroRepo pr = new ParametroRepo();
		pr.getCount();
	}

}
