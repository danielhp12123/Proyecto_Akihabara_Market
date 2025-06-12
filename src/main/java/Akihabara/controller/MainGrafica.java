package Akihabara.controller;

import java.awt.EventQueue;

import Akihabara.view.InterfazGrafica;
/**
 * Clase principal que ejecuta la aplicación usando la interfaz grafica de gestión de productos del
 * inventario y gestion de los clientes de la app.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class MainGrafica {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica frame = new InterfazGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
