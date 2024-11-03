package Memoria;

import Producto.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Memoria {

    private static final String CARPETA_DESTINO = System.getenv("APPDATA") + "\\TPFinal";
    private static final String ARCHIVO_JSON = CARPETA_DESTINO + "\\productos.json";

    private final ObjectMapper mapper;

    public Memoria() {
        this.mapper = new ObjectMapper();
        this.mapper.registerSubtypes(Producto.class);
        this.mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public void verificarArchivo() throws IOException {
        Path carpetaDestino = Paths.get(CARPETA_DESTINO);
        if (!Files.exists(carpetaDestino)) {
            Files.createDirectories(carpetaDestino);
        }

        Path archivo = Paths.get(ARCHIVO_JSON);
        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    public ArrayList<Producto> vaciarArchivo() throws IOException {
        verificarArchivo(); // Asegura que el archivo exista antes de intentar leerlo

        Path archivo = Paths.get(ARCHIVO_JSON);
        byte[] contenido = Files.readAllBytes(archivo);

        if (contenido.length > 0) {
            // Utilizar TypeFactory para obtener la referencia de tipo adecuada
            TypeFactory typeFactory = mapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, Producto.class);
            ArrayList<Producto> productos = mapper.readValue(contenido, collectionType);
            return productos;
        } else {
            return new ArrayList<>();
        }
    }

    public void guardarArchivo(ArrayList<Producto> productos) throws IOException {
        verificarArchivo(); // Asegura que el archivo exista antes de intentar escribir
        Path archivo = Paths.get(ARCHIVO_JSON);
        mapper.writeValue(Files.newBufferedWriter(archivo), productos);
    }
}
