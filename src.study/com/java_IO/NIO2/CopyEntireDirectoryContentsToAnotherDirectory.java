package com.java_IO.NIO2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyEntireDirectoryContentsToAnotherDirectory {

	public static void main(String[] args){
		
		// To use the visitor do the following
		Path sourcePath = Paths.get("/home/sabaja/Scrivania/Varie");
		Path targetPath = Paths.get("/home/sabaja/Scrivania/VarieCopy");
		try {
			Files.walkFileTree(sourcePath, new CopyFileVisitor(targetPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class CopyFileVisitor extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;
    public CopyFileVisitor(Path targetPath) {
        this.targetPath = targetPath;
    }
    
    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
    final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
        Files.createDirectories(targetPath.resolve(sourcePath
                    .relativize(dir)));
        }
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFile(final Path file,
    final BasicFileAttributes attrs) throws IOException {
    Files.copy(file,
        targetPath.resolve(sourcePath.relativize(file)));
    return FileVisitResult.CONTINUE;
    }
}