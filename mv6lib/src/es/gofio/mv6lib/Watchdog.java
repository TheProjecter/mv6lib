package es.gofio.mv6lib;

import java.util.Vector;

public class Watchdog extends java.lang.Thread {
    private static Watchdog INSTANCE = null;
    private Vector<String> queue = null;
 
    // Private constructor suppresses 
    private Watchdog() {}
 
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciaci—n mœltiple 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new Watchdog();
        }
    }
 
    public static Watchdog getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    
    public void setQueueOfPages(Vector<String> queue) {
    	this.queue = queue;
    }
    
    @Override
    public void run() {
    	while(true) {
    		try {
        		System.out.println("hola");
    			sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}

