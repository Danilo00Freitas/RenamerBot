package Backend;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ArchiveManager {
    private Path path;
    private String newDirectoryName;
    private Path newDirectoryPath;
    private String time;

    public ArchiveManager(String filePath){
        path = Paths.get(filePath);
        newDirectoryName = path.getFileName().toString() + "_Renomeados";
        newDirectoryPath = path.resolveSibling(newDirectoryName);
    }

    public void fileCopier(String fileName){
        try {
            // Garantindo que o diretório destino existe
            if (!Files.exists(newDirectoryPath)) {
                Files.createDirectories(newDirectoryPath);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SS");
            // Use a API Stream para listar todos os arquivos.
            Files.list(path)
                    .filter(Files::isRegularFile)
                    .forEach(sourceFile -> {
                        // Construindo o caminho de destino para o arquivo

                        String newFileName = fileName + LocalTime.now().format(formatter).replace(":","") + ".csv";
                        Path renamedFile = newDirectoryPath.resolve(newFileName);

                        try {
                            // Copiando o arquivo de origem para o destino
                            Files.copy(sourceFile, renamedFile, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Arquivo copiado: " + renamedFile.getFileName());
                            Thread.sleep(200);
                        } catch (IOException e) {
                            System.err.println("Erro ao copiar arquivo: " + e.getMessage());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });

            System.out.println("Todos os arquivos foram copiados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao copiar diretório: " + e.getMessage());
        }
    }
}
