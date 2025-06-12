package Akihabara.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.*;

import Akihabara.config.ConfigLoader;

/**
 * Servicio para interactuar con un modelo de lenguaje grande (LLM) para generar
 * descripciones y sugerir categorías de productos otaku.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class LlmService {

	private static final String API_KEY = ConfigLoader.getApiKey();
	private static final String API_URL = ConfigLoader.getApiUrl();

	/**
	 * Genera una descripción breve para un producto otaku usando el modelo de
	 * lenguaje.
	 *
	 * @param nombre    El nombre del producto.
	 * @param categoria La categoría del producto.
	 * @return Una descripción breve del producto en español.
	 */
	public static String generarDescripcionProducto(String nombre, String categoria) {
		String prompt = String.format("Eres un redactor para una tienda otaku. "
				+ "Haz una descripción breve (máximo 2 líneas) para el producto '%s' de la categoría '%s', "
				+ "sin hashtags ni emojis y en español.", nombre, categoria);
		return llamarLlm(prompt);
	}

	/**
	 * Sugiere la categoría más adecuada para un nuevo producto otaku.
	 *
	 * @param nombreNuevoProducto El nombre del nuevo producto.
	 * @return La categoría sugerida como una palabra simple, sin explicaciones.
	 */
	public static String sugerirCategoriaProducto(String nombreNuevoProducto) {

		String prompt = String.format(
				"Dime solo la categoría más adecuada para un producto otaku llamado '%s' de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro. "
						+ "Devuelve solo la palabra con la categoría, sin explicaciones.",
				nombreNuevoProducto);
		return llamarLlm(prompt);
	}

	/**
	 * Realiza la llamada HTTP al modelo de lenguaje con un prompt dado. Método
	 * privado usado internamente.
	 *
	 * @param prompt El texto para enviar al modelo de lenguaje.
	 * @return La respuesta generada por el modelo, o mensaje de error si falla.
	 */
	private static String llamarLlm(String prompt) {
		try {
			JsonObject requestBody = new JsonObject();
			requestBody.addProperty("model", "mistralai/mistral-7b-instruct:free");

			JsonArray messages = new JsonArray();
			JsonObject userMessage = new JsonObject();
			userMessage.addProperty("role", "user");
			userMessage.addProperty("content", prompt);
			messages.add(userMessage);

			requestBody.add("messages", messages);

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
					.header("Authorization", "Bearer " + API_KEY).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString())).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				throw new RuntimeException("Error al conectar con OpenRouter. Código HTTP: " + response.statusCode());
			}

			JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
			JsonArray choices = jsonResponse.getAsJsonArray("choices");
			if (choices.size() == 0) {
				throw new RuntimeException("Respuesta vacía del modelo.");
			}

			JsonObject message = choices.get(0).getAsJsonObject().getAsJsonObject("message");
			return message.get("content").getAsString().trim();
		} catch (Exception e) {
			return "Error al comunicarse con el LLM: " + e.getMessage();
		}
	}
}
