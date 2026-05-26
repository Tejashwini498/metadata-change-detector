import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public class MetadataChangeDetector {

    public static void main(String[] args) throws Exception {
        String oldContent = new String(Files.readAllBytes(Paths.get("old_metadata.json")));
        String newContent = new String(Files.readAllBytes(Paths.get("new_metadata.json")));

        JSONObject oldJson = new JSONObject(oldContent);
        JSONObject newJson = new JSONObject(newContent);

        compareFields(oldJson.getJSONObject("fields"), newJson.getJSONObject("fields"));
        compareObjects(oldJson.getJSONArray("objects"), newJson.getJSONArray("objects"));
    }

    static void compareFields(JSONObject oldF, JSONObject newF) {
        System.out.println("\n--- FIELD CHANGES ---");
        for (String key : newF.keySet())
            if (!oldF.has(key))
                System.out.println("➕ ADDED field: " + key + " (type: " + newF.getString(key) + ")");
        for (String key : oldF.keySet())
            if (!newF.has(key))
                System.out.println("➖ REMOVED field: " + key + " (was type: " + oldF.getString(key) + ")");
        for (String key : oldF.keySet())
            if (newF.has(key) && !oldF.getString(key).equals(newF.getString(key)))
                System.out.println("🔄 TYPE CHANGED: " + key + " from '" + oldF.getString(key) + "' to '" + newF.getString(key) + "'");
    }

    static void compareObjects(JSONArray oldArr, JSONArray newArr) {
        System.out.println("\n--- OBJECT CHANGES ---");
        java.util.List<Object> oldList = oldArr.toList();
        java.util.List<Object> newList = newArr.toList();
        for (Object obj : newList)
            if (!oldList.contains(obj))
                System.out.println("➕ ADDED object: " + obj);
        for (Object obj : oldList)
            if (!newList.contains(obj))
                System.out.println("➖ REMOVED object: " + obj);
    }
}