package reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConfigLoader {

    public static void main(String[] args) {
//        Path GAME_CONFIG_PATH = Path.of("resources/game-properties.cfg");
        GameConfig gameConfig = createConfigObject(GameConfig.class, Paths.get("src/main/resources/game-properties.cfg"));
        System.out.println(gameConfig);
    }


    @SneakyThrows
    private static <T> T createConfigObject(Class<T> clazz, Path filePath){
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Scanner scanner = new Scanner(filePath);

        T configInstance = (T) constructor.newInstance();
        while (scanner.hasNext()){
            String[] nameValuePair = scanner.nextLine().split("=");
            String name = nameValuePair[0];
            String value = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField(name);
            }catch (Exception e){
                System.out.print("Exception e " + e);
                continue;
            }
            field.setAccessible(true);

            Object objectValue = field.getType().isArray() ? parseArray(field.getType().getComponentType(), value)
                    : parseValue(field.getType(), value);
            field.set(configInstance, objectValue);
        }
        return configInstance;
    }

    private static Object parseArray(Class<?> arrayElementType, String value){
        String[] values = value.split(",");
        Object array = Array.newInstance(arrayElementType, values.length);
        for(int i=0; i<values.length; i++){
            Array.set(array, i, parseValue(arrayElementType, values[i]));
        }
        return array;
    }

    private static Object parseValue(Class<?> type, String value){
        if(type.equals(int.class))
            return Integer.parseInt(value);
        else if(type.equals(short.class))
            return Short.parseShort(value);
        else if(type.equals(long.class))
            return Long.parseLong(value);
        else if(type.equals(double.class))
            return Double.parseDouble(value);
        else if(type.equals(float.class))
            return Float.parseFloat(value);
        else if(type.equals(String.class))
            return value;

        throw new RuntimeException(String.format("Type %s not supported", type.getTypeName()));
    }

}
