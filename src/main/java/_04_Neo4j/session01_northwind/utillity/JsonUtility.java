package _04_Neo4j.session01_northwind.utillity;

import java.util.Map;

import org.neo4j.driver.types.Node;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtility {

	private static final Gson GSON = new GsonBuilder().create();

	private JsonUtility() {
	}

	public static <T> T convertNodeToPOJO(Node node, Class<T> className) {
		return convertJsonToObject(convertMapToJson(convertNodeToMap(node)), className);
	}

	public static Map<String, Object> convertNodeToMap(Node node) {
		return node.asMap();
	}

	public static String convertMapToJson(Map<String, Object> map) {
		return GSON.toJson(map);
	}
	
	public static Map<String, Object> convertJsonToMap(String json) {
		return GSON.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
	}

	public static <T> T convertJsonToObject(String json, Class<T> className) {
		return GSON.fromJson(json, className);
	}

	public static String convertObjectToJson(Object obj) {
		return GSON.toJson(obj);
	}

}
