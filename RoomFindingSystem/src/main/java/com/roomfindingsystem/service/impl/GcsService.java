
package com.roomfindingsystem.service.impl;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class GcsService {
    private final Storage storage;

    public GcsService() {
        // Khởi tạo storage trong constructor
        this.storage = initStorage();
    }

    private Storage initStorage() {
        try {
            // Đọc nội dung của Blob
            Blob blob = StorageOptions.getDefaultInstance().getService()
                    .get("rfs_bucket", "key_json/rfs-test-404421-93404f84139a.json");
            byte[] jsonBytes = blob.getContent();

            // Đọc và cấu hình Storage client với JSON key
            try (InputStream inputStream = new ByteArrayInputStream(jsonBytes)) {
                // Đảm bảo rằng bạn đang đọc ServiceAccountCredentials từ InputStream
                ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(inputStream);

                // Sử dụng credentials để cấu hình StorageOptions
                StorageOptions storageOptions = StorageOptions.newBuilder()
                        .setProjectId("rfs-test-404421")
                        .setCredentials(credentials)
                        .build();

                // Trả về đối tượng Storage đã được khởi tạo
                return storageOptions.getService();
            } catch (IOException e) {
                throw new RuntimeException("Không thể khởi tạo Storage", e);
            }
        } catch (StorageException e) {
            throw new RuntimeException("Không thể đọc Blob", e);
        }
    }

    public void uploadImage(String bucketName, String objectName, byte[] imageBytes) {
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        Blob blob = storage.create(blobInfo, imageBytes);
    }
}
