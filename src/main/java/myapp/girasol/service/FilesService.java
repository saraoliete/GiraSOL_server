/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp.girasol.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import myapp.girasol.entity.FileEntity;
import myapp.girasol.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sara
 */
@Service
@Transactional
public class FilesService {
    
    private final Path root = Paths.get("uploads");
    
    @Autowired
    FileRepository oFileRepository;
    
    public Page<FileEntity> paginas(Pageable pageable){
        
        return oFileRepository.findAll(pageable);
    }

    public Stream<Path> loadAll() throws IOException{
        
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
        
    }
}
