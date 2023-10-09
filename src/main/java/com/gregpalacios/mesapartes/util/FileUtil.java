package com.gregpalacios.mesapartes.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.gregpalacios.mesapartes.dto.FileDTO;

@Component
public class FileUtil {

	public static String createFile(byte[] archivoBytes, FileDTO fileDTO) throws IOException {
		String fileNameUniq = getFileNameUniq(fileDTO);
		File file = new File(fileDTO.getFilePath() + fileNameUniq);

		try (OutputStream out = new FileOutputStream(file);) {
			out.write(archivoBytes);
			return fileNameUniq;
		}
	}

	public static String createFileFromBase64(FileDTO fileDTO) throws IOException {
		byte[] archivoBytes = Base64.getDecoder().decode(fileDTO.getFileContentBase64());
		String fileNameUniq = getFileNameUniq(fileDTO);
		File file = new File(fileDTO.getFilePath() + fileNameUniq);

		try (OutputStream out = new FileOutputStream(file);) {
			out.write(archivoBytes);
			return fileNameUniq;
		}
	}

	public static String getFileNameUniq(FileDTO fileDTO) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String fileNameUniq = "file-" + timestamp.getTime() + "." + fileDTO.getFileFormat();
		return fileNameUniq;
	}
}
