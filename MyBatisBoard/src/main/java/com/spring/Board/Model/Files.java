package com.spring.Board.Model;

import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter, setter
@Builder
public class Files { // dto
	private int file_id;
	private int board_id;
	private String file_original_name;
	private String file_name;
	private String file_path;
	private String file_type;
	private byte[] file_data;
	
}

