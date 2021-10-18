package base.climateapp.root.root.model.roomdatabase;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import base.climateapp.root.root.model.entity.Day;

public class Converters {
    //EXMAPLES !!!!!!
    @TypeConverter
    public static List<Day> fromString(String value) {
        Type listType = new TypeToken<List<Day>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Day> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
