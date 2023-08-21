package Backend;
import java.io.IOException;
import java.nio.file.*;

public class ArchiveManager {
    private Path path;
    private String newDirectoryName;
    private Path newDirectoryPath;

    public ArchiveManager(String filePath){
        path = Paths.get(filePath);
        newDirectoryName = path.getFileName().toString() + "_Renomeados";
        newDirectoryPath = path.resolveSibling(newDirectoryName);
    }

    public void fileCopier(){
        try {
            // Garantindo que o diretório destino existe
            if (!Files.exists(newDirectoryPath)) {
                Files.createDirectories(newDirectoryPath);
            }

            // Use a API Stream para listar todos os arquivos.
            Files.list(path)
                    .filter(Files::isRegularFile)
                    .forEach(sourceFile -> {
                        // Construindo o caminho de destino para o arquivo
                        Path targetFile = newDirectoryPath.resolve(sourceFile.getFileName());

                        try {
                            // Copiando o arquivo de origem para o destino
                            Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Arquivo copiado: " + targetFile.getFileName());
                        } catch (IOException e) {
                            System.err.println("Erro ao copiar arquivo: " + e.getMessage());
                        }
                    });

            System.out.println("Todos os arquivos foram copiados com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao copiar diretório: " + e.getMessage());
        }
    }

    public void fileRenamer(){

    }

}
