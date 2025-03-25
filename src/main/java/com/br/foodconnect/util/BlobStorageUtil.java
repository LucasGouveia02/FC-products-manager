package com.br.foodconnect.util;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class BlobStorageUtil {

    Dotenv dotenv = Dotenv.configure()
            .directory("env/local")
            .filename("env.conf")
            .load();

    String containerNamePath = dotenv.get("CONTAINER_NAME_PATH");
    String blobEndpointPath = dotenv.get("BLOB_ENDPOINT_PATH");

    String containerName = new String(Files.readAllBytes(Paths.get(containerNamePath)));
    String blobEndpoint = new String(Files.readAllBytes(Paths.get(blobEndpointPath)));

    public BlobStorageUtil() throws IOException {
    }

    public String uploadImage(MultipartFile file) throws Exception {
        String blobName = file.getOriginalFilename();
        BlobClient blobClient = new BlobClientBuilder()
                .connectionString(blobEndpoint)
                .containerName(containerName)
                .blobName(blobName)
                .buildClient();

        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return blobClient.getBlobUrl();
    }

    public void deleteImage(String imageUrl) {
        String blobName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        BlobClient blobClient = new BlobClientBuilder()
                .connectionString(blobEndpoint)
                .containerName(containerName)
                .blobName(blobName)
                .buildClient();

        blobClient.delete();
    }
}

